package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.models.EntityTable;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class SpecialitiesPage extends MainMenu {

    private final EntityTable table = new EntityTable();

    private final SelenideElement progressBar = $x("//mat-progress-bar");

    public EntityTable getTable() {
        return table;
    }

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

    @Step("Speciality page: Waited for progress bar to disappear")
    public SpecialitiesPage waitForProgressBarToDisappear() {
        progressBar.should(disappear);

        return this;
    }

    @Step("Speciality page: Waited for progress bar to appear")
    public SpecialitiesPage waitForProgressBarToAppear() {
        progressBar.should(appear, ofSeconds(3));

        return this;
    }

    @Step("Speciality page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear)
                .getText();
    }

    @Step("Speciality page: Deleted row with searched value")
    public SpecialitiesPage deleteRowByValue(String searchValue) {
        table.deleteRowByValue(searchValue);

        return this;
    }
}
