package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class SpecialitiesPage extends MainMenu {

    private final String NAVIGATION_BUTTON_PATH_TEMPLATE = "//button[contains(@Class,'paginator-navigation-%s')]";

    private final SelenideElement progressBar = $x("//mat-progress-bar");

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
        goToTablePage("last");

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

    @Step("Speciality page: Found table page with searched value")
    public SpecialitiesPage findTablePageWithSearchValue(String searchValue) {
        goToTablePage("first");

        var buttonNavigationNext = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, "next"));
        var isSearchValueOnCurrentPage = false;

        while (buttonNavigationNext.isEnabled() && !isSearchValueOnCurrentPage) {
            goToTablePage("next");
            buttonNavigationNext
                    .$x(".//div[contains(@class,'round')]/div[@class='mat-ripple-element']")
                    .should(disappear);

            isSearchValueOnCurrentPage = isSearchValueInTableTexts(searchValue);
        }

        return this;
    }

    @Step("Speciality page: Changed table page")
    public void goToTablePage(String direction) {
        var buttonNavigation = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, direction));

        if (buttonNavigation.isEnabled()) {
            buttonNavigation.click();
        }
    }

    public boolean isSearchValueInTableTexts(String searchValue) {
        var tableRows = $$x("//table//tr//td");

        return tableRows
                .texts()
                .contains(searchValue);
    }

    @Step("Speciality page: Deleted row with searched value")
    public SpecialitiesPage deleteRowByValue(String searchValue) {
        $$x("//table//tr//td")
                .find(exactText(searchValue))
                .parent()
                .$x(".//i[contains(@class,'delete')]")
                .click();

        return this;
    }
}
