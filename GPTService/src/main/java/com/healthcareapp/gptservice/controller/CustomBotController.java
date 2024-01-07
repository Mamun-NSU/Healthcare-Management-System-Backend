package com.healthcareapp.gptservice.controller;
import com.healthcareapp.gptservice.dto.ChatGPTRequest;
import com.healthcareapp.gptservice.dto.ChatGptResponse;
import com.healthcareapp.gptservice.dto.PatientClinicalRequest;
import com.healthcareapp.gptservice.dto.PatientClinicalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
@RestController
@RequestMapping("/bot")
public class CustomBotController {
    @Value("${openai.model}")
    private String model;
    @Value(("${openai.api.url}"))
    private String apiURL;
    @Autowired
    private RestTemplate template;
    @PostMapping("/chat")
    public ResponseEntity<PatientClinicalResponse> chat(@RequestBody PatientClinicalRequest patientClinicalRequest){
        String query = " Please remember never use new lines and no extra data is required. If there are more than one diagnoses, treatment plan or medication" +
                " just separate them using commas. But only use new line to separate the fields" +
                "  just provide the response";
        String content = "Gender: "
                + patientClinicalRequest.getGender() + ", Age: " + patientClinicalRequest.getAge()
                +", BloodPressure: "+ patientClinicalRequest.getBloodPressure() + ", HeartRate: "
                + patientClinicalRequest.getHeartRate() +", Symptoms: "+ patientClinicalRequest.getSymptoms()
                + ", Please provide diagnoses, treatment plan and medication in this form only \n" +
                "  diagnoses: \n" +
                "  treatmentPlan: \n" +
                "  medication: \n" + query;
        System.out.println(content);
        ChatGPTRequest request=new ChatGPTRequest(model, content);
        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
        PatientClinicalResponse clinicalResponse = getPatientClinicalResponse(chatGptResponse);
        return new ResponseEntity<>(clinicalResponse, HttpStatus.OK);
    }
    private PatientClinicalResponse getPatientClinicalResponse(ChatGptResponse chatGptResponse) {
        PatientClinicalResponse clinicalResponse = new PatientClinicalResponse();
        String responseString = chatGptResponse.getChoices().get(0).getMessage().getContent();
        System.out.println(responseString);
        String[] lines = responseString.split("\n");
        for (String line : lines) {
            if (line.startsWith("diagnoses:")) {
                clinicalResponse.setDiagnoses(line.substring("diagnoses: ".length()).trim());
            } else if (line.startsWith("treatmentPlan:")) {
                clinicalResponse.setTreatmentPlan(line.substring("treatmentPlan: ".length()).trim());
            } else if (line.startsWith("medication:")) {
                clinicalResponse.setMedication(line.substring("medication: ".length()).trim());
            }
        }
        clinicalResponse.setGeneratedTime(LocalDateTime.now());
        return clinicalResponse;
    }
}