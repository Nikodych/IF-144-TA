package com.softserveinc.ita.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class RandomUtil {

    public static String getRandomStringWithLetters(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getRandomStringWithNumbers(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
