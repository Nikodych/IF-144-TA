package com.softserveinc.ita.pageobjects.admin;

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

    public List<String> getDescriptions() {
        return $$x("//td[contains(@class, 'description')]").texts();
    }
}