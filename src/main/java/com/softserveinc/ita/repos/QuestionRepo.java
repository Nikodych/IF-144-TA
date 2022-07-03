package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.QuestionEntity;

import java.io.File;

import static com.softserveinc.ita.repos.AnswerRepo.getValidAnswer;
import static com.softserveinc.ita.util.RandomUtil.getRandomIntegerInRange;
import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithLetters;

public class QuestionRepo {

    public static QuestionEntity getValidQuestion() {
        return QuestionEntity
                .builder()
                .textOfQuestion(getRandomStringWithLetters(15))
                .typeOfQuestion(getRandomIntegerInRange(1, 2))
                .levelOfQuestion(getRandomIntegerInRange(1, 20))
                .file(new File("src/main/resources/testimage.png"))
                .answer(getValidAnswer())
                .build();
    }
}