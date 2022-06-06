package com.softserveinc.ita.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static java.time.LocalDate.now;

@UtilityClass
public class DateTimeUtil {
    public static LocalDate getCurrentDate() {
        return now();
    }
}
