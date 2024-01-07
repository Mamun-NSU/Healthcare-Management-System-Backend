package com.healthcareapp.doctordatamanagementservice.repositories;

import com.healthcareapp.doctordatamanagementservice.entities.Doctor;
import com.healthcareapp.doctordatamanagementservice.enums.Qualifications;
import com.healthcareapp.doctordatamanagementservice.enums.Specialities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    @Query("SELECT d FROM Doctor d WHERE d.specialities IN :specialities AND d.qualifications IN :qualifications")
    List<Doctor> findBySpecialitiesInAndQualificationsIn(@Param("specialities") Set<Specialities> specialities, @Param("qualifications") Set<Qualifications> qualifications);

    @Query("SELECT d FROM Doctor d WHERE d.qualifications IN :qualifications")
    List<Doctor> findByQualificationsIn(@Param("qualifications") Set<Qualifications> qualifications);

    @Query("SELECT d FROM Doctor d WHERE d.specialities IN :specialities")
    List<Doctor> findBySpecialitiesIn(@Param("specialities") Set<Specialities> specialities);
}

