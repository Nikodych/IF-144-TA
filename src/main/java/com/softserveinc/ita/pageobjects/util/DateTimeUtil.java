package com.softserveinc.ita.pageobjects.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static java.time.LocalDate.now;

@UtilityClass
public class DateTimeUtil {
    public static LocalDate getCurrentDate() {
        return now();
    }
}
