package com.softserveinc.ita.repos;

import com.softserveinc.ita.models.TestEntity;

import static com.softserveinc.ita.util.DataProvider.TEST_SUBJECT;
import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithLetters;
import static com.softserveinc.ita.util.RandomUtil.getRandomStringWithNumbers;

public class TestRepo {

    public static TestEntity getValidTest() {
        return TestEntity
                .builder()
                .name(getRandomStringWithLetters(10))
                .subject(TEST_SUBJECT)
                .numberOfTasks(getRandomStringWithNumbers(2))
                .amountOfTime(getRandomStringWithNumbers(2))
                .numberOfAttempts(getRandomStringWithNumbers(1))
                .build();
    }
}