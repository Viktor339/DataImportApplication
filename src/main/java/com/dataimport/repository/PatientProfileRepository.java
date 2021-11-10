package com.dataimport.repository;

import com.dataimport.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile,Integer> {
    PatientProfile findPatientProfileById(Integer id);
}
