package com.dataimport.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class CompanyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false,unique = true)
    private String login;
    @Column(unique = true)
    private String middleName;


    public CompanyUser(Integer id, String firstName, String lastName, String middleName, String login) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.middleName = middleName;
    }

}
