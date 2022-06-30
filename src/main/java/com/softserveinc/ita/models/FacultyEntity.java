package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FacultyEntity {
    private String name;
    private String description;
}

