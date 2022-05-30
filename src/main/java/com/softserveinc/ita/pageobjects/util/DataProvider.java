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
    String STUDENT_LOGIN = readConfig().getString("users.student.login");
    String STUDENT_PASSWORD = readConfig().getString("users.student.password");
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
    String FACULTY_NAME = readConfig().getString("fieldsInAddingForms.faculties.facultyName");
    String SUBJECT_NAME = readConfig().getString("fieldsInAddingForms.subjects.subjectName");
    String FACULTY_DESCRIPTION = readConfig().getString("fieldsInAddingForms.faculties.facultyDescription");
    String SUBJECT_DESCRIPTION = readConfig().getString("fieldsInAddingForms.subjects.subjectDescription");
    String INPUT_TEMPLATE = readConfig().getString("addingFormsTemplates.input");
    String TEXTAREA_TEMPLATE = readConfig().getString("addingFormsTemplates.textArea");
    String MAT_SELECT_TEMPLATE = readConfig().getString("addingFormsTemplates.matSelect");
}
