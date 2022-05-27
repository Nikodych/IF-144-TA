package com.softserveinc.ita.pageobjects.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainMenuButtons {
    DASHBOARD_PAGE("dashboard"),
    FACULTIES_PAGE("faculties"),
    GROUPS_PAGE("group"),
    SPECIALITIES_PAGE("speciality"),
    SUBJECTS_PAGE("subjects"),
    RESULTS_PAGE("results"),
    PROTOCOL_PAGE("protocol"),
    ADMINS_PAGE("admin-user"),
    ABOUT_US_PAGE("aboutUs");

    private final String pageName;
}

