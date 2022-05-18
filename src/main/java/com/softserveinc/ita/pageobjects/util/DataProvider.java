package com.softserveinc.ita.pageobjects.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface DataProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("data.conf");
    }

    String ADMIN_LOGIN = readConfig().getString("users.admin.login");
    String ADMIN_PASSWORD = readConfig().getString("users.admin.password");
    String PROTOCOL_PAGE_URL = readConfig().getString("pages.protocol");
    String LOGIN_PAGE_URL = readConfig().getString("pages.login");
    String START_DATE = readConfig().getString("dates.startDate");
    String END_DATE = readConfig().getString("dates.endDate");
}
