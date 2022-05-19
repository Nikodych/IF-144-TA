package com.softserveinc.ita.petrus.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class ConfigurationPropertiesHelper {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        try (var inputStream = ConfigurationPropertiesHelper.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            PROPERTIES.load(inputStream);
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
