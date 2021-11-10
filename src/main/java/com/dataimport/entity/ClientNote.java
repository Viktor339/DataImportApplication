package com.dataimport.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientNote {

    @JsonProperty("comments")
    private String comments;
    @JsonProperty("notes_id")
    private String notesId;
    @JsonProperty("record_date_timestamp")
    private String recordDateTimestamp;
    @JsonProperty("modified_datetime")
    private String modifiedDatetime;
    @JsonProperty("client_guid")
    private String clientGuId;
    @JsonProperty("datetime")
    private String dateTime;
    @JsonProperty("logged_user")
    private String loggedUser;
    @JsonProperty("note_type")
    private String noteType;

}
