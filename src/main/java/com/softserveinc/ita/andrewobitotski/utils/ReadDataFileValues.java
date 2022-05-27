package com.softserveinc.ita.andrewobitotski.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ReadDataFileValues {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("data.properties");
    }

    String URL_GOOGLE_HOMEPAGE = readConfig().getString("url.google.homepage");
    String SEARCH_REQUEST = readConfig().getString("search.request");
    String MENTION = readConfig().getString("mention.in.search.results");
    String URL_MOYO_HOMEPAGE = readConfig().getString("url.moyo.homepage");
    String MOYO_EXPECTED_RESULT = readConfig().getString("expected.result");
    Integer MOYO_EXPECTED_RESULTS_COUNT = readConfig().getInt("expected.results.count");
    Integer MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT = readConfig().getInt("expected.show.all.results.count");
}
