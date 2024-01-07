package com.healthcareapp.clinicaldecisionsupportsystemservice.services.implementations;
import com.healthcareapp.clinicaldecisionsupportsystemservice.entities.Report;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.CurrentHealthRequest;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.PatientClinicalRequest;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.PatientClinicalResponse;
import com.healthcareapp.clinicaldecisionsupportsystemservice.models.PatientResponseDTO;
import com.healthcareapp.clinicaldecisionsupportsystemservice.networkmanager.GptFeignClient;
import com.healthcareapp.clinicaldecisionsupportsystemservice.networkmanager.PatientFeignClient;
import com.healthcareapp.clinicaldecisionsupportsystemservice.repositories.ReportRepository;
import com.healthcareapp.clinicaldecisionsupportsystemservice.services.interfaces.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final PatientFeignClient patientFeignClient;
    private final GptFeignClient gptFeignClient;
    public ReportServiceImpl(ReportRepository reportRepository,
                             PatientFeignClient patientFeignClient,
                             GptFeignClient gptFeignClient){
        this.reportRepository = reportRepository;
        this.patientFeignClient = patientFeignClient;
        this.gptFeignClient = gptFeignClient;
    }
    private String getPatientIdFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    @Override
    public Report getReportByPatient(CurrentHealthRequest currentHealthRequest) {
        String patientId = getPatientIdFromToken();

        ResponseEntity<PatientResponseDTO> response = patientFeignClient.findPatientById(patientId);
        PatientResponseDTO patientResponseDTO = response.getBody();

        PatientClinicalRequest patientClinicalRequest = new PatientClinicalRequest();
        assert patientResponseDTO != null;
        patientClinicalRequest.setAge(patientResponseDTO.getAge());
//        patientClinicalRequest.setGender(patientResponseDTO.getGender().toString());

        Double hp = currentHealthRequest.getHighBP();
        Double low = currentHealthRequest.getLowBP();

        // Check for null values before invoking toString()
        String bloodPressureData = (hp != null && low != null) ? hp.toString() + "/" + low.toString() + " mmHg" : null;

        Double heartRate = currentHealthRequest.getHeartRate();

        // Check for null value before invoking toString()
        String beatsperMinuteInString = (heartRate != null) ? heartRate.toString() + " bpm" : null;

        patientClinicalRequest.setBloodPressure(bloodPressureData);
        patientClinicalRequest.setHeartRate(beatsperMinuteInString);
        patientClinicalRequest.setSymptoms(currentHealthRequest.getSymptoms());

        ResponseEntity<PatientClinicalResponse> reportResponse = gptFeignClient
                .findPatientReport(patientClinicalRequest);

        PatientClinicalResponse clinicalResponse = reportResponse.getBody();
        Report report = new Report();
        report.setPatientId(patientId);
        assert clinicalResponse != null;
        report.setDiagnoses(clinicalResponse.getDiagnoses());
        report.setTreatmentPlan(clinicalResponse.getTreatmentPlan());
        report.setMedication(clinicalResponse.getMedication());
        report.setGeneratedTime(clinicalResponse.getGeneratedTime());
        reportRepository.save(report);
        return report;
    }

}