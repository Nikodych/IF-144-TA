package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.models.EntityTable;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SubjectsPage extends MainMenu {

    private EntityTable table = new EntityTable();

    @Step("Subjects page: Opened adding subject form")
    public AddingSubjectModal openAddingSubjectForm() {
        $x("//button[contains(@class, 'addSubject')]").click();

        return new AddingSubjectModal();
    }

    public List<String> getNamesOfSubjects() {
        return $$x("//td[contains(@class, 'mat-column-subject_name')]").texts();
    }

    @Step("Subjects Page: Switched to last page of table")
    public SubjectsPage switchToLastPageOfTable() {
        table.goToTablePage("last");

        return this;
    }

    @Step("Subjects page: Switched to first page of table")
    public SubjectsPage switchToFirstPageOfTable() {
        table.goToTablePage("first");

        return this;
    }

    @Step("Subjects page: Set search value")
    public SubjectsPage setSearchValue(String subject) {
        $x("//mat-form-field[contains(@class, 'filter')]//input").sendKeys(subject);

        return this;
    }
}
