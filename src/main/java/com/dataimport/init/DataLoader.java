package com.dataimport.init;

import com.dataimport.entity.CompanyUser;
import com.dataimport.entity.PatientNote;
import com.dataimport.entity.PatientProfile;
import com.dataimport.repository.CompanyUserRepository;
import com.dataimport.repository.PatientNoteRepository;
import com.dataimport.repository.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final PatientProfileRepository patientProfileRepository;
    private final PatientNoteRepository patientNoteRepository;
    private final CompanyUserRepository companyUserRepository;

    public void run(ApplicationArguments args) {

        patientProfileRepository.save(new PatientProfile("viktor", "viktor", "viktor",
                Arrays.asList("111","222"), 200));
        patientProfileRepository.save(new PatientProfile("viktor1", "viktor1", "viktor1",
                Arrays.asList("333"), 210));
        patientProfileRepository.save(new PatientProfile("viktor2", "viktor2", "viktor2",
                Arrays.asList("444"), 200));
//        patientProfileRepository.save(new PatientProfile(1, "viktor3", "viktor3", "viktor3",
//                Arrays.asList("6","7"), 200));


        companyUserRepository.save(new CompanyUser(1, "admin", "admin", "admin", "admin"));
        companyUserRepository.save(new CompanyUser(2, "p.vasya", "p.vasya", "p.vasya", "p.vasya"));
//        companyUserRepository.save(new CompanyUser(3,"admin3","admin3","admin3","admin3"));
//        companyUserRepository.save(new CompanyUser(4,"admin4","admin4","admin4","admin4"));


        patientNoteRepository.save(new PatientNote(1,
                companyUserRepository.findCompanyUserById(1),
                companyUserRepository.findCompanyUserById(2),
                patientProfileRepository.findPatientProfileById(1),
                new GregorianCalendar(2017, Calendar.JULY, 9, 11, 6, 22),
                new GregorianCalendar(2021, Calendar.SEPTEMBER, 16, 12, 2, 24),
                "note before change",
                1));

        patientNoteRepository.save(new PatientNote(2,
                companyUserRepository.findCompanyUserById(1),
                companyUserRepository.findCompanyUserById(2),
                patientProfileRepository.findPatientProfileById(2),
                new GregorianCalendar(2017, Calendar.JULY, 9, 11, 6, 22),
                new GregorianCalendar(2021, Calendar.SEPTEMBER, 16, 12, 2, 24),
                "note before change",
                1));

        patientNoteRepository.save(new PatientNote(3,
                companyUserRepository.findCompanyUserById(1),
                companyUserRepository.findCompanyUserById(2),
                patientProfileRepository.findPatientProfileById(3),
                new GregorianCalendar(2017, Calendar.JULY, 9, 11, 6, 22),
                new GregorianCalendar(2021, Calendar.SEPTEMBER, 16, 12, 2, 24),
                "note before change",
                1));

//        patientNoteRepository.save(new PatientNote(3,"09.11","09.11","???",1));
//        patientNoteRepository.save(new PatientNote(4,"09.11","09.11","???",1));


    }
}
