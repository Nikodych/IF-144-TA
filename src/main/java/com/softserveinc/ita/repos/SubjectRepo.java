package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.SubjectEntity;

public class SubjectRepo {

    public static SubjectEntity getValidSubject() {
        return SubjectEntity
                .builder()
                .name("Новий предмет")
                .description("Опис")
                .build();
    }

    public static SubjectEntity getSubjectWithInvalidName() {
        return SubjectEntity
                .builder()
                .name("5предмет")
                .description("Опис")
                .build();
    }

    public static SubjectEntity getSubjectWithInvalidDescription() {
        return SubjectEntity
                .builder()
                .name("Предмет")
                .description("опис")
                .build();
    }
}
