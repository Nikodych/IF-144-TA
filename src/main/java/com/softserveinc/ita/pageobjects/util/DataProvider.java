package com.softserveinc.ita.pageobjects.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface DataProvider {
    Config config = readConfig();

    static Config readConfig(){
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("data.conf");
    }

    String ADMIN_LOGIN = readConfig().getString("users.admin.login");
    String ADMIN_PASSWORD = readConfig().getString("users.admin.password");
    String PROTOCOL_PAGE_URL = readConfig().getString("pages.protocol");
    String LOGIN_PAGE_URL = readConfig().getString("pages.login");
    String START_YEAR = readConfig().getString("dates.startDate.year");
    String START_MONTH = readConfig().getString("dates.startDate.month");
    String START_DAY = readConfig().getString("dates.startDate.day");
    String END_YEAR = readConfig().getString("dates.endDate.year");
    String END_MONTH = readConfig().getString("dates.endDate.month");
    String END_DAY = readConfig().getString("dates.endDate.day");
}
