package com.dataimport.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    @Column(unique = true)
    @ElementCollection
    private List<String> oldClientGuid;
    private Integer statusId;

    public PatientProfile(String firstName, String lastName, String middleName, List<String> oldClientGuid, Integer statusId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.oldClientGuid = oldClientGuid;
        this.statusId = statusId;
    }
}
