package com.softserveinc.ita.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentEntity {
    private String surname;
    private String name;
    private String fatherName;
    private String gradeBookId;
    private String login;
    private String email;
    private String password;
    private String passwordConfirm;
}
