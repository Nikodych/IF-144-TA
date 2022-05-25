package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
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
