package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.StudentEntity;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithLetters;
import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

public class StudentRepo {
    public static StudentEntity getNewValidStudent() {
       var password = getRandomStringWithLetters(6) + getRandomStringWithNumbers(3);

        return StudentEntity
                .builder()
                .surname(getRandomStringWithLetters(5))
                .name(getRandomStringWithLetters(5))
                .fatherName(getRandomStringWithLetters(5))
                .gradeBookId(getRandomStringWithNumbers(5))
                .login(getRandomStringWithLetters(5))
                .email(getRandomStringWithLetters(5) + "@mail.com")
                .password(password)
                .passwordConfirm(password)
                .build();
    }
}
