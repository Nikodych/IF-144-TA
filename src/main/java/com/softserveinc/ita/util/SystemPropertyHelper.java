package com.softserveinc.ita.util;

import lombok.experimental.UtilityClass;

import static java.lang.Boolean.parseBoolean;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

@UtilityClass
public class SystemPropertyHelper {

    public static boolean isRemote() {
        return parseBoolean(getSystemProperty("is.remote"));
    }

    private static String getSystemProperty(String property) {
        return ofNullable(getProperty(property)).orElseThrow(() -> new AssertionError(format("System property '%s' should be set", property)));
    }
}