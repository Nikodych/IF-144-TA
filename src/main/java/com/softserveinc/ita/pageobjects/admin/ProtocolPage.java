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

    public boolean isSearchButtonEnabled() {
        return searchButton.isEnabled();
    }

    @Step("Protocol page: moving date picker backward")
    public ProtocolPage moveDatePickerBackward(int index) {
        $x(format(DATE_PICKER_TEMPLATE, index)).click();
        $x("//button[contains(@class,'mat-calendar-previous-button')]").click();

        return this;
    }

    @Step("Protocol page: moving date picker forward")
    public ProtocolPage moveDatePickerForward(int index) {
        $x(format(DATE_PICKER_TEMPLATE, index)).click();
        $x("//button[contains(@class,'mat-calendar-next-button')]").click();

        return this;
    }

    @Step("Protocol page: getting current month from date picker")
    public String getCurrentMonth() {
        return chooseMonthAndYearButton
                .getText();
    }

    @Step("Protocol page: closing date picker pop-up window")
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
}
