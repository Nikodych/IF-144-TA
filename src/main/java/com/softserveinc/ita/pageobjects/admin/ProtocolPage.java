package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;

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

    public ProtocolPage performSearch() {
        searchButton.click();

        return this;
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

    public boolean isSearchResultPresent() {

        return $x("//tr[@class='mat-row ng-star-inserted']")
                .should(appear, ofSeconds(5))
                .exists();
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
