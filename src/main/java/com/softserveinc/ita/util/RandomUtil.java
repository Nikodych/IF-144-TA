package com.softserveinc.ita.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import static org.apache.commons.lang3.RandomStringUtils.*;

@UtilityClass
public class RandomUtil {

    public static String getRandomStringWithLetters(int length) {
        return randomAlphabetic(length);
    }

    public static String getRandomStringWithNumbers(int length) {
        return randomNumeric(length);
    }
}
