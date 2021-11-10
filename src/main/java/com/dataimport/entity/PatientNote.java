package com.dataimport.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@NoArgsConstructor
public class PatientNote {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Calendar createdDateTime;
    @Column(nullable = false)
    private Calendar lastModifiedDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyUser createdByUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyUser lastModifiedByUserId;

    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private PatientProfile patientId;

    private Integer type;


    public PatientNote(Integer id, CompanyUser createdByUserId, CompanyUser lastModifiedByUserId, PatientProfile patientId, Calendar createdDateTime, Calendar lastModifiedDateTime, String note, Integer type) {
        this.id = id;
        this.createdByUserId = createdByUserId;
        this.lastModifiedByUserId = lastModifiedByUserId;
        this.patientId = patientId;
        this.createdDateTime = createdDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.note = note;
        this.type = type;
    }
}
