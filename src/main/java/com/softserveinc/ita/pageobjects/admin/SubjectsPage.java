package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SubjectsPage extends MainMenu {

    @Step("Subjects page: Opened adding subject form")
    public AddingSubjectModal openAddingSubjectForm() {
        $x("//button[contains(@class, 'addSubject')]").click();

        return new AddingSubjectModal();
    }

    public List<String> getNamesOfSubjects() {
        return $$x("//td[contains(@class, 'mat-column-subject_name')]").texts();
    }

    @Step("Subjects Page: Showed last page of table")
    public SubjectsPage switchToLastPageOfTable() {
        $x("//mat-paginator//button[contains(@class, 'mat-paginator-navigation-last')]").click();

        return this;
    }

    @Step("Subjects page: Showed first page of table")
    public SubjectsPage switchToFirstPageOfTable() {
        $x("//mat-paginator//button[contains(@class, 'mat-paginator-navigation-first')]").click();

        return this;
    }

    @Step("Subjects page: Set search value")
    public SubjectsPage setSearchValue(String subject) {
        $x("//mat-form-field[contains(@class, 'filter')]//input").sendKeys(subject);

        return this;
    }
}
