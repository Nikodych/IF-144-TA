package com.softserveinc.ita.util;

import com.typesafe.config.Config;

import java.io.File;

import static com.typesafe.config.ConfigFactory.load;
import static com.typesafe.config.ConfigFactory.parseFile;
import static java.lang.System.getenv;

public interface DataProvider {

    static Config readConfig() {
        return load("data.conf");
    }

    static Config readCredentials() {
        var path = getenv("CREDENTIALS");
        Config config;

        if (path == null) {
            config = load("credentials.conf"); // local usage, file should be at resources directory
        } else {
            var configFile = new File(path);
            var fileConfig = parseFile(configFile);
            config = load(fileConfig);
        }

        return config;
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
    String STUDENTS_PAGE_URL = readConfig().getString("pages.students");
    String START_DATE = readConfig().getString("dates.startDate");
    String END_DATE = readConfig().getString("dates.endDate");
    String ERROR_MESSAGE_WRONG_DATE_ORDER = readConfig().getString("messages.error.wrongDateOrder");
    String TEST_SUBJECT = readConfig().getString("testData.testSubject");
    String TEST_TEST = readConfig().getString("testData.testTest");
    String GROUP_NAME = readConfig().getString("studentsData.groupName");
    String STUDENT_IS_ADDED_SUCCESSFUL_MESSAGE = readConfig().getString("studentsData.successfulAddingMessage");
    String API_BASE_URI = readConfig().getString("api.baseUri");
    String API_LOGIN_USER_PATH = readConfig().getString("api.basePathes.login");
    String API_IS_LOGGED_PATH = readConfig().getString("api.basePathes.isLogged");
    String API_LOGOUT_PATH = readConfig().getString("api.basePathes.logout");
    String API_ENTITY_GET_RECORDS_PATH = readConfig().getString("api.entityPathes.getRecords");
    String API_ENTITY_POST_RECORDS_PATH = readConfig().getString("api.entityPathes.insertData");
    String API_ENTITY_UPDATE_RECORDS_PATH = readConfig().getString("api.entityPathes.update");
    String API_ENTITY_DELETE_RECORDS_PATH = readConfig().getString("api.entityPathes.delete");
}