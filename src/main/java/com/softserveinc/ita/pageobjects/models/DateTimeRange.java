package com.softserveinc.ita.pageobjects.models;

import com.codeborne.selenide.SelenideElement;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

@Builder
@Data
public class DateTimeRange {
    private LocalDate start;
    private LocalDate end;

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
}
