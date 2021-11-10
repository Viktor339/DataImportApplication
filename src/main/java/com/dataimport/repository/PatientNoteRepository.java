package com.dataimport.repository;

import com.dataimport.entity.PatientNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientNoteRepository extends JpaRepository<PatientNote, Integer> {
    PatientNote findPatientNoteById(Integer id);
}
