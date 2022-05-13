package com.softserveinc.ita.petrus_selenide.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("config.properties");
    }

    String GOOGLE_URL = readConfig().getString("google.url");
    String ROZETKA_URL = readConfig().getString("rozetka.url");
}
