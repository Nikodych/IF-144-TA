package com.softserveinc.ita.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ReadConfigFileValues {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("andrewconfig.properties");
    }

    String URL_GOOGLE_HOMEPAGE = readConfig().getString("url_google_homepage");
    String SEARCH_REQUEST = readConfig().getString("search_request");
    String MENTION = readConfig().getString("mention_in_search_results");
    String URL_MOYO_HOMEPAGE = readConfig().getString("url_moyo_homepage");
    String MOYO_EXPECTED_RESULT = readConfig().getString("expected_result");

}
