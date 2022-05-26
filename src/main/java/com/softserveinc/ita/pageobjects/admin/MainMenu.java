package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.pageobjects.admin.MainMenuButtons.*;
import static java.time.Duration.ofSeconds;

public class MainMenu {

    @Step("Main menu: Opened dashboard page")
    public DashboardPage openDashboardPage() {
        DASHBOARD_PAGE
                .getButton()
                .click();

        return new DashboardPage();
    }

    @Step("Main menu: Opened faculties page")
    public FacultiesPage openFacultiesPage() {
        FACULTIES_PAGE
                .getButton()
                .click();

        return new FacultiesPage();
    }

    @Step("Main menu: Opened groups page")
    public GroupsPage openGroupsPage() {
        GROUPS_PAGE
                .getButton()
                .click();

        return new GroupsPage();
    }

    @Step("Main menu: Opened specialities page")
    public SpecialitiesPage openSpecialitiesPage() {
        SPECIALITIES_PAGE
                .getButton()
                .click();

        return new SpecialitiesPage();
    }

    @Step("Main menu: Opened subjects page")
    public SubjectsPage openSubjectsPage() {
        SUBJECTS_PAGE
                .getButton()
                .click();

        return new SubjectsPage();
    }

    @Step("Main menu: Opened results page")
    public ResultsPage openResultsPage() {
        RESULTS_PAGE
                .getButton()
                .click();

        return new ResultsPage();
    }

    @Step("Main menu: Opened protocol page")
    public ProtocolPage openProtocolPage() {
        PROTOCOL_PAGE
                .getButton()
                .click();

        return new ProtocolPage();
    }

    @Step("Main menu: Opened admins page")
    public AdminsPage openAdminsPage() {
        ADMINS_PAGE
                .getButton()
                .click();

        return new AdminsPage();
    }

    @Step("Main menu: Opened about us page")
    public AboutUsPage openAboutUsPage() {
        ABOUT_US_PAGE
                .getButton()
                .click();

        return new AboutUsPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]")
                .should(appear, ofSeconds(5))
                .getText();
    }
}
