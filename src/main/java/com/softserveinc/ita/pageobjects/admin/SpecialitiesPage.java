package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class SpecialitiesPage extends MainMenu<SpecialitiesPage> {

    private final EntityTable table = new EntityTable();

    @Step("Speciality page: Confirmed in modal window")
    public SpecialitiesPage confirmModal() {
        $x("//button/span[contains(text(),'Підтвердити')]").click();

        return this;
    }

    @Step("Speciality page: Set speciality name")
    public SpecialitiesPage setName(String value) {
        $x("//input[@formcontrolname='speciality_name']").setValue(value);

        return this;
    }

    @Step("Speciality page: Set speciality code")
    public SpecialitiesPage setCode(String value) {
        $x("//input[@formcontrolname='speciality_code']").setValue(value);

        return this;
    }

    @Step("Speciality page: Added new speciality")
    public SpecialitiesPage openAddingNewForm() {
        $x("//button[contains(@class,'addButton')]").click();

        return this;
    }

    @Step("Speciality page: Got last speciality code")
    public String getLastSpecialityCode() {
        table.goToTablePage("last");

        return $x("//table")
                .$$x(".//tr")
                .last()
                .$x(".//td[contains(@class,'code')]")
                .getText();
    }

    @Step("Speciality page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear)
                .getText();
    }
}
