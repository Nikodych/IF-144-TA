package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.models.EntitiesButtons;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.models.EntitiesButtons.*;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public abstract class MainMenu<T extends MainMenu<T>> {

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

    private void openPage(EntitiesButtons pageName) {
        $x(format("//a[@href='/admin/%s']", pageName.getPageName()))
                .should(appear, ofSeconds(5))
                .click();
    }

    @Step("Opened adding form")
    public AddingFormModal openAddingNewForm(){
        $x("//*[starts-with(name(), 'app')]//*[@aria-label='add']/ancestor::button")
                .should(appear,ofSeconds(5))
                .click();

        return new AddingFormModal();
    }

    public T waitTillProgressBarDisappears() {
        waitForProgressBarToAppear();
        waitForProgressBarToDisappear();

        return (T) this;
    }

    public void waitForProgressBarToDisappear() {
        $x(PROGRESS_BAR_PATH).should(disappear);
    }

    public void waitForProgressBarToAppear() {
        $x(PROGRESS_BAR_PATH).should(appear, ofSeconds(3));
    }
}
