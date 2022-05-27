package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.pageobjects.models.DateTimeRange.MainMenuButtons.*;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class MainMenu {

    @Step("Main menu: Opened dashboard page")
    public DashboardPage openDashboardPage() {
        openPage(DASHBOARD_PAGE.getPageName());

        return new DashboardPage();
    }

    @Step("Main menu: Opened faculties page")
    public FacultiesPage openFacultiesPage() {
        openPage(FACULTIES_PAGE.getPageName());

        return new FacultiesPage();
    }

    @Step("Main menu: Opened groups page")
    public GroupsPage openGroupsPage() {
        openPage(GROUPS_PAGE.getPageName());

        return new GroupsPage();
    }

    @Step("Main menu: Opened specialities page")
    public SpecialitiesPage openSpecialitiesPage() {
        openPage(SPECIALITIES_PAGE.getPageName());

        return new SpecialitiesPage();
    }

    @Step("Main menu: Opened subjects page")
    public SubjectsPage openSubjectsPage() {
        openPage(SUBJECTS_PAGE.getPageName());

        return new SubjectsPage();
    }

    @Step("Main menu: Opened results page")
    public ResultsPage openResultsPage() {
        openPage(RESULTS_PAGE.getPageName());

        return new ResultsPage();
    }

    @Step("Main menu: Opened protocol page")
    public ProtocolPage openProtocolPage() {
        openPage(PROTOCOL_PAGE.getPageName());

        return new ProtocolPage();
    }

    @Step("Main menu: Opened admins page")
    public AdminsPage openAdminsPage() {
        openPage(ADMINS_PAGE.getPageName());

        return new AdminsPage();
    }

    @Step("Main menu: Opened about us page")
    public AboutUsPage openAboutUsPage() {
        openPage(ABOUT_US_PAGE.getPageName());

        return new AboutUsPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]")
                .should(appear, ofSeconds(5))
                .getText();
    }

    private void openPage(String pageName) {
        $x(format("//a[@href='/admin/%s']", pageName))
                .should(appear, ofSeconds(5))
                .click();
    }
}
