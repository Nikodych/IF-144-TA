package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SubjectsPage extends MainMenu {
    private final EntityTable table = new EntityTable();

    public List<String> getNamesOfSubjects() {
        return $$x("//td[contains(@class, 'mat-column-subject_name')]").texts();
    }

    @Step("Subjects page: Set search value")
    public SubjectsPage setSearchValue(String subject) {
        $x("//mat-form-field[contains(@class, 'filter')]//input").sendKeys(subject);

        return this;
    }
}
