package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.StudentEntity;

public class StudentRepo {
    public static StudentEntity getNewValidStudent(){
        return StudentEntity
                .builder()
                .surname("Іваненко")
                .name("Іван")
                .fatherName("Іванович")
                .gradeBookId("12345")
                .login("ivan")
                .email("ivan@gmail.com")
                .password("qwerty123")
                .passwordConfirm("qwerty123")
                .build();
    }
}
