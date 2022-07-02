package com.softserveinc.ita.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Data
public class FacultyEntity {
    @JsonProperty(value = "faculty_id", access = JsonProperty.Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    private String id;
    @JsonProperty("faculty_name")
    private String name;
    @JsonProperty("faculty_description")
    private String description;
}

