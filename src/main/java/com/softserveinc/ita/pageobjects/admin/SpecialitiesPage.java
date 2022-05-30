package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SpecialitiesPage extends MainMenu {

    private SelenideElement buttonNavigationLast = $x("//button[contains(@class,'paginator-navigation-last')]");
    private SelenideElement buttonNavigationFirst = $x("//button[contains(@class,'paginator-navigation-first')]");
    private SelenideElement buttonNavigationNext = $x("//button[contains(@class,'paginator-navigation-next')]");

    @Step("Speciality page: Added new speciality")
    public SpecialitiesPage addNewSpeciality(String code, String name) {
        $x("//button[contains(@class,'addButton')]").click();
        $x("//input[@formcontrolname='speciality_code']").setValue(code);
        $x("//input[@formcontrolname='speciality_name']").setValue(name);
        $x("//button/span[contains(text(),'Підтвердити')]").click();

        return this;
    }

    @Step("Speciality page: Got last speciality code")
    public String getLastSpecialityCode() {
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
        $x("//mat-progress-bar").should(disappear);

        return this;
    }

    @Step("Speciality page: Waited for progress bar to appear")
    public SpecialitiesPage waitForProgressBarToAppear() {
        $x("//mat-progress-bar").should(appear);

        return this;
    }

    @Step("Speciality page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear)
                .getText();
    }

    @Step("Speciality page: Deleted speciality by code")
    public SpecialitiesPage deleteSpecialityByCode(String code) {
        SelenideElement currentRow = findRowByColumnValue(code);
        currentRow.$x(".//i[contains(@class,'delete')]").click();
        $x("//button/span[contains(text(),'Підтвердити')]").click();

        return this;
    }

    private SelenideElement findRowByColumnValue(String searchValue) {
        SelenideElement currentRow;

        if (buttonNavigationFirst.isEnabled()) {
            buttonNavigationFirst.click();
        }

        currentRow = findRowOnCurrentPageByColumnValue(searchValue);

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
