package com.softserveinc.ita.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@UtilityClass
public class RandomUtil {

    public static String getRandomStringWithLetters(int length) {
        return randomAlphabetic(length);
    }

    public static String getRandomStringWithNumbers(int length) {
        return ((randomNumeric(length)).replaceAll("^0+(?!$)", ""));
    }

    public static int getRandomIntegerInRange(int from, int to) {
        return new Random().nextInt(to-from)+from;
    }
}
