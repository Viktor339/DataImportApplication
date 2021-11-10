package com.dataimport.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {
    @JsonProperty("agency")
    private String agency;
    @JsonProperty("client_guid")
    private String clientGuid;
    @JsonProperty("auto_inc_number")
    private Integer autoIncNumber;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("client_status")
    private String clientStatus;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("created_date")
    private String createdDate;
}
