package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FacultiesPage extends MainMenu {
    SelenideElement searchField = $x("//div/input[@id = 'mat-input-2']");
    ElementsCollection faculties = $$x("//td[contains(@class, 'faculty_name')]");
    ElementsCollection description = $$x("//td[contains(@class, 'description')]");

    public FacultiesPage fillSearchField(String needToFind) {
        searchField.setValue(needToFind).pressEnter();
        return this;
    }

    public List<String> getFaculties(){
        return faculties.texts();
    }
    public List<String> getDescription(){
        return description.texts();
    }
}
