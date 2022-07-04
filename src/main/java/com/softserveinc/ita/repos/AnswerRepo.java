package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.AnswerEntity;

import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithLetters;

public class AnswerRepo {

    public static AnswerEntity getValidAnswer() {
        return AnswerEntity
                .builder()
                .textOfAnswer(getRandomStringWithLetters(15))
                .isAnswerRight(true)
                .build();
    }
}