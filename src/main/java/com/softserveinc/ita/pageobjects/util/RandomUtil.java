package com.softserveinc.ita.pageobjects.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.pow;

@UtilityClass
public class RandomUtil {
    public static int getRandomNumber(int length) {
        int bound; // bound is max possible value

        if (length > 9) {
            bound = MAX_VALUE;
        } else {
            // example: if length = 3, bound will be 10^3 - 1 = 999
            bound = (int) pow(10, length) - 1;
        }

        return new Random().nextInt(bound);
    }
}
