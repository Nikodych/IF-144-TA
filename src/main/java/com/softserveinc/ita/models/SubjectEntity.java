package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectEntity {
    @JsonProperty("subject_id")
    private String id;
    @JsonProperty("subject_name")
    private String name;
    @JsonProperty("subject_description")
    private String description;

    public SubjectEntity() {
    }

    public SubjectEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
