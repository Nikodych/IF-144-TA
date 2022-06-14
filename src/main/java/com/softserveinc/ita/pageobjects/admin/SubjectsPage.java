package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SubjectsPage extends MainMenu {

    @Step("Subjects page: Opened adding subject form")
    public AddingSubjectModal openAddingSubjectForm() {
        $x("//button[contains(@class, 'addSubject')]").click();
        $x("//app-subjects-create-modal").shouldBe(visible);

        return new AddingSubjectModal();
    }

    public boolean hasSubject(String subject) {
        return $$x("//td[contains(@class, 'mat-column-subject_name')]")
                .shouldHave(sizeGreaterThanOrEqual(0))
                .texts()
                .contains(subject);
    }

    @Step("Subjects Page: Switched to last page of table")
    public SubjectsPage switchToLastPageOfTable() {
        $x("//mat-paginator//button[contains(@class, 'mat-paginator-navigation-last')]").click();

        return this;
    }

    @Step("Subjects page: Switched to first page of table")
    public SubjectsPage switchToFirstPageOfTable() {
        $x("//mat-paginator//button[contains(@class, 'mat-paginator-navigation-first')]").click();

        return this;
    }

    @Step("Subjects page: Set search value")
    public SubjectsPage setSearchValue(String subject) {
        var searchField = "//mat-form-field[contains(@class, 'filter')]//input";

        $x(searchField).clear();
        $x(searchField).sendKeys(subject);

        return this;
    }

    @Step("Subjects page: Deleted subject")
    public SubjectsPage deleteSubjectByName(String subject) {
        $$x("//tbody//tr//td")
                .findBy(exactText(subject))
                .parent()
                .$x(".//mat-icon[contains(@class, 'delete')]")
                .click();

        return this;
    }

    @Step("Subjects page: Confirmed deleting subject")
    public SubjectsPage confirmDeletingSubject() {
        $x("//app-confirm//button[1]")
                .shouldBe(visible)
                .click();

        return this;
    }

    @Step("Subjects page: Edited subject")
    public AddingSubjectModal editSubject(String subject) {
        $$x("//tbody//tr//td")
                .findBy(exactText(subject))
                .parent()
                .$x(".//mat-icon[contains(@class, 'edit')]")
                .click();
        $x("//app-subjects-create-modal").shouldBe(visible);

        return new AddingSubjectModal();
    }
}
