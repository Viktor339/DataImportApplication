package com.dataimport.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientData {

    @JsonProperty("data")
    @ElementCollection
    private List<ClientNote> clientNote;
}
