package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import lombok.RequiredArgsConstructor;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

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

    public SelenideElement getButton() {
        return $x(format("//a[@href='/admin/%s']", pageName))
                .should(appear, ofSeconds(5));
    }
}
