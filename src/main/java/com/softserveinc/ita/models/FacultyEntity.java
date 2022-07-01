package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@JsonDeserialize(builder = FacultyEntity.FacultyEntityBuilder.class)
public class FacultyEntity {
    @JsonProperty("faculty_id")
    @EqualsAndHashCode.Exclude
    private String id;
    @JsonProperty("faculty_name")
    private String name;
    @JsonProperty("faculty_description")
    private String description;
}

