package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.util.RandomUtil;

public class FacultyRepo {

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

    public static FacultyEntity getValidRandomFaculty() {
        var randString = RandomUtil.getRandomStringWithCyrillicLetters(20);

        return FacultyEntity
                .builder()
                .name(randString)
                .description(randString)
                .build();
    }
}
