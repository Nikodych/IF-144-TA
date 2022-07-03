package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.models.EntitiesButtons;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.models.EntitiesButtons.*;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class DashboardPage extends MainMenu<DashboardPage> {

    @Step("Dashboard page: Opened Faculties page")
    public FacultiesPage goToFacultiesPage() {
        openPageWithCardButton(FACULTIES_PAGE);

        return new FacultiesPage();
    }

    @Step("Dashboard page: Opened Groups page")
    public GroupsPage goToGroupsPage() {
        openPageWithCardButton(GROUPS_PAGE);

        return new GroupsPage();
    }

    @Step("Dashboard page: Opened Specialities page")
    public SpecialitiesPage goToSpecialitiesPage() {
        openPageWithCardButton(SPECIALITIES_PAGE);

        return new SpecialitiesPage();
    }

    @Step("Dashboard page: Opened Subjects page")
    public SubjectsPage goToSubjectsPage() {
        openPageWithCardButton(SUBJECTS_PAGE);

        return new SubjectsPage();
    }

    @Step("Dashboard page: Opened Students page")
    public GroupsPage goToStudentsPage() {
        openPageWithCardButton(STUDENTS_PAGE);

        return new GroupsPage();
    }

    @Step("Dashboard page: Opened Admins page")
    public AdminsPage goToAdminsPage() {
        openPageWithCardButton(ADMINS_PAGE);

        return new AdminsPage();
    }

    @Step("Dashboard page: Opened Results page")
    public ResultsPage goToResultsPage() {
        openPageWithCardButton(RESULTS_PAGE);

        return new ResultsPage();
    }

    @Step("Dashboard page: Opened Protocol page")
    public ProtocolPage goToProtocolPage() {
        openPageWithCardButton(PROTOCOL_PAGE);

        return new ProtocolPage();
    }

    private void openPageWithCardButton(EntitiesButtons pageName) {
        $x(format("//mat-card-title[contains(text(),'%s') or contains(text(),'%s')]/ancestor::mat-card//mat-card-actions/button", pageName.getCardTitleEng(), pageName.getCardTitleUa()))
                .should(appear, ofSeconds(5))
                .click();
    }
}
