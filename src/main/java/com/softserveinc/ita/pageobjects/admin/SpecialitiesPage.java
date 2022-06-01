package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.pageobjects.models.SpecialityEntity;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class SpecialitiesPage extends MainMenu {

    private final String NAVIGATION_BUTTON_PATH_TEMPLATE = "//button[contains(@Class,'paginator-navigation-%s')]";

    private SelenideElement progressBar = $x("//mat-progress-bar");

    public SpecialitiesPage confirmModal() {
        $x("//button/span[contains(text(),'Підтвердити')]").click();

        return this;
    }

    public SpecialitiesPage setName(String value) {
        $x("//input[@formcontrolname='speciality_name']").setValue(value);

        return this;
    }

    public SpecialitiesPage setCode(String value) {
        $x("//input[@formcontrolname='speciality_code']").setValue(value);

        return this;
    }

    public SpecialitiesPage openAddingNewForm() {
        $x("//button[contains(@class,'addButton')]").click();

        return this;
    }

    @Step("Speciality page: Got last speciality code")
    public String getLastSpecialityCode() {
        var buttonNavigationLast = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, "last"));

        if (buttonNavigationLast.isEnabled()) {
            buttonNavigationLast.click();
        }

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
        progressBar.should(appear, Duration.ofSeconds(3));

        return this;
    }

    @Step("Speciality page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear)
                .getText();
    }

    @Step("Speciality page: Deleted speciality by code")
    public SpecialitiesPage deleteSpeciality(SpecialityEntity speciality) {
        var currentRow = findRowByColumnValue(speciality.getCode()); //consider code as unique field
        currentRow.$x(".//i[contains(@class,'delete')]").click();
        confirmModal();

        return this;
    }

    private SelenideElement findRowByColumnValue(String searchValue) {
        var buttonNavigationFirst = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, "first"));

        if (buttonNavigationFirst.isEnabled()) {
            buttonNavigationFirst.click();
        }

        var currentRow = findRowOnCurrentPageByColumnValue(searchValue);
        var buttonNavigationNext = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, "next"));

        while (buttonNavigationNext.isEnabled() && currentRow == null) {
            buttonNavigationNext.click();
            buttonNavigationNext
                    .$x(".//div[contains(@class,'round')]/div[@class='mat-ripple-element']")
                    .should(disappear);

            currentRow = findRowOnCurrentPageByColumnValue(searchValue);
        }

        return currentRow;
    }

    private SelenideElement findRowOnCurrentPageByColumnValue(String searchValue) {
        SelenideElement searchRow = null;
        var tableRows = $$x("//table//tr//td");

        if (tableRows
                .texts()
                .contains(searchValue)) {
            searchRow = tableRows
                    .find(exactText(searchValue))
                    .parent();
        }

        return searchRow;
    }
}
