package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FacultiesPage extends MainMenu {

    @Step("Faculties page: Performed search of {searchValue}")
    public FacultiesPage performSearch(String searchValue) {
        $x("//div/input[@id = 'mat-input-2']")
                .setValue(searchValue)
                .pressEnter();

        return this;
    }

    public List<String> getFaculties() {
        return $$x("//td[contains(@class, 'faculty_name')]").texts();
    }

    @Step("Faculties page: opened adding faculties form")
    public AddingFormModal openAddingFacultyForm() {
        $x("//app-faculties//*[@aria-label='add']/ancestor::button").click();

        return new AddingFormModal();
    }
}