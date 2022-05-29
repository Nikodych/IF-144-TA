package com.softserveinc.ita.pageobjects.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface DataProvider {

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("data.conf");
    }

    static Config readCredentials() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("credentials.conf");
    }

    String ADMIN_LOGIN = readCredentials().getString("users.admin.login");
    String ADMIN_PASSWORD = readCredentials().getString("users.admin.password");
    String STUDENT_LOGIN = readCredentials().getString("users.student.login");
    String STUDENT_PASSWORD = readCredentials().getString("users.student.password");

    String LOGIN_PAGE_URL = readConfig().getString("pages.login");
    String DASHBOARD_PAGE_URL = readConfig().getString("pages.dashboard");
    String FACULTIES_PAGE_URL = readConfig().getString("pages.faculties");
    String GROUPS_PAGE_URL = readConfig().getString("pages.groups");
    String SPECIALITIES_PAGE_URL = readConfig().getString("pages.specialities");
    String SUBJECTS_PAGE_URL = readConfig().getString("pages.subjects");
    String RESULTS_PAGE_URL = readConfig().getString("pages.results");
    String PROTOCOL_PAGE_URL = readConfig().getString("pages.protocol");
    String ADMINS_PAGE_URL = readConfig().getString("pages.admins");
    String ABOUT_US_PAGE_URL = readConfig().getString("pages.aboutUs");
    String START_DATE = readConfig().getString("dates.startDate");
    String END_DATE = readConfig().getString("dates.endDate");
    String ERROR_MESSAGE_WRONG_DATE_ORDER = readConfig().getString("messages.error.wrongDateOrder");
    String FACULTY = readConfig().getString("searchInput.faculty");
    String DESCRIPTION = readConfig().getString("searchInput.description");
    String INVALID = readConfig().getString("searchInput.invalid");
}
