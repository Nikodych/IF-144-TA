package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FacultyEntity {
    String name;
    String description;

    public static FacultyEntity getValidFaculty() {
        return FacultyEntity
                .builder()
                .name("факультет")
                .description("опис факультету")
                .build();
    }

    public static FacultyEntity getFacultyWithInvalidName() {
        return FacultyEntity
                .builder()
                .name("?невалідний факультет")
                .description("опис факультету")
                .build();
    }

    public static FacultyEntity getFacultyWithInvalidDescription() {
        return FacultyEntity
                .builder()
                .name("факультет")
                .description("опиs факультету")
                .build();
    }
}

