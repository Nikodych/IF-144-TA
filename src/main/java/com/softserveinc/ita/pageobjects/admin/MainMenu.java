package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.models.MainMenuButtons;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.models.MainMenuButtons.*;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class MainMenu<T extends MainMenu> {

    private static final String PROGRESS_BAR_PATH = "//mat-progress-bar";

    @Step("Main menu: Opened dashboard page")
    public DashboardPage openDashboardPage() {
        openPage(DASHBOARD_PAGE);

        return new DashboardPage();
    }

    @Step("Main menu: Opened faculties page")
    public FacultiesPage openFacultiesPage() {
        openPage(FACULTIES_PAGE);

        return new FacultiesPage();
    }

    @Step("Main menu: Opened groups page")
    public GroupsPage openGroupsPage() {
        openPage(GROUPS_PAGE);

        return new GroupsPage();
    }

    @Step("Main menu: Opened specialities page")
    public SpecialitiesPage openSpecialitiesPage() {
        openPage(SPECIALITIES_PAGE);

        return new SpecialitiesPage();
    }

    @Step("Main menu: Opened subjects page")
    public SubjectsPage openSubjectsPage() {
        openPage(SUBJECTS_PAGE);

        return new SubjectsPage();
    }

    @Step("Main menu: Opened results page")
    public ResultsPage openResultsPage() {
        openPage(RESULTS_PAGE);

        return new ResultsPage();
    }

    @Step("Main menu: Opened protocol page")
    public ProtocolPage openProtocolPage() {
        openPage(PROTOCOL_PAGE);

        return new ProtocolPage();
    }

    @Step("Main menu: Opened admins page")
    public AdminsPage openAdminsPage() {
        openPage(ADMINS_PAGE);

        return new AdminsPage();
    }

    @Step("Main menu: Opened about us page")
    public AboutUsPage openAboutUsPage() {
        openPage(ABOUT_US_PAGE);

        return new AboutUsPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]")
                .should(appear, ofSeconds(5))
                .getText();
    }

    private void openPage(MainMenuButtons pageName) {
        $x(format("//a[@href='/admin/%s']", pageName.getPageName()))
                .should(appear, ofSeconds(5))
                .click();
    }

    public T waitTillProgressBarDisappears() {
        waitForAppear();
        waitForDisappear();

        return (T) this;
    }

    private void waitForDisappear() {
        $x(PROGRESS_BAR_PATH).should(disappear);
    }

    private void waitForAppear() {
        $x(PROGRESS_BAR_PATH).should(appear, ofSeconds(3));
    }
}
