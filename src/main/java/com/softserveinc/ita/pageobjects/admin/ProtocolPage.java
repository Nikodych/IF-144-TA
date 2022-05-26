package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class ProtocolPage extends MainMenu {

    private final SelenideElement chooseMonthAndYearButton = $x("//button[@aria-label='Choose month and year']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");

    private static final String OPTION_BUTTON_TEMPLATE = "//div[text()='%s']";
    private static final String DATE_PICKER_TEMPLATE = "(//button[@aria-label='Open calendar'])[%s]";

    @Step ("Protocol page: Performed search")
    public ProtocolPage performSearch() {
        searchButton.click();

        return this;
    }

    public String getErrorMessage() {
        return $x("//mat-error[@id='mat-error-0']")
                .should(appear, ofSeconds(5))
                .getText();
    }

    public ProtocolPage chooseStartDate(LocalDate date) {
        return chooseDate(date, 1);
    }

    public ProtocolPage chooseEndDate(LocalDate date) {
        return chooseDate(date, 2);
    }

    @Step("Protocol page: Set start date")
    public ProtocolPage setStartDate(String date) {
        return setDate(date, "startDate");
    }

    @Step("Protocol page: Set end date")
    public ProtocolPage setEndDate(String date) {
        return setDate(date, "endDate");
    }

    public boolean isSearchButtonEnabled() {
        return searchButton.isEnabled();
    }

    public boolean isSearchResultPresent() {
        return $x("//tr[@class='mat-row ng-star-inserted']")
                .should(appear, ofSeconds(5))
                .exists();
    }

    @Step("Protocol page: Moved date picker backward")
    public ProtocolPage moveDatePickerBackward(int index) {
        return moveDatePicker(index, "previous");
    }

    @Step("Protocol page: Moved date picker forward")
    public ProtocolPage moveDatePickerForward(int index) {
        return moveDatePicker(index, "next");
    }

    @Step("Protocol page: Got current month from date picker")
    public String getCurrentMonth() {
        return chooseMonthAndYearButton.getText();
    }

    @Step("Protocol page: Closed date picker pop-up window")
    public ProtocolPage closeDatePickerWindow() {
        if ($x("//div[contains(@class,'mat-datepicker-popup')]").exists()) {
            //just clicking on the other part of the page
            $x("//body").click();
        }

        return this;
    }

    private ProtocolPage chooseDate(LocalDate date, int index) {

        var month = date
                .getMonth()
                .toString()
                .substring(0, 3);

        $x(format(DATE_PICKER_TEMPLATE, index)).click();
        chooseMonthAndYearButton.click();
        $x(format(OPTION_BUTTON_TEMPLATE, date.getYear())).click();
        $x(format(OPTION_BUTTON_TEMPLATE, month)).click();
        $x(format(OPTION_BUTTON_TEMPLATE, date.getDayOfMonth())).click();

        return this;
    }

    private ProtocolPage setDate(String date, String fieldName) {
        $x(format("//input[@formcontrolname='%s']", fieldName)).sendKeys(date);

        return this;
    }

    private ProtocolPage moveDatePicker(int index, String direction) {
        $x(format(DATE_PICKER_TEMPLATE, index)).click();
        $x(format("//button[contains(@class,'mat-calendar-%s-button')]", direction)).click();

        return this;
    }
}
