package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ProtocolPage extends MainMenu {

    private final SelenideElement chooseMonthAndYearButton = $x("//button[@aria-label='Choose month and year']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");

    private static final String OPTION_BUTTON_TEMPLATE = "//div[text()='%s']";
    private static final String DATE_PICKER_TEMPLATE = "(//button[@aria-label='Open calendar'])[%s]";
    private static final String DATE_INPUT_FIELD_TEMPLATE = "//input[@formcontrolname='%s']";

    public ProtocolPage chooseStartDate(LocalDate date) {
        return chooseDate(date, 1);
    }

    public ProtocolPage chooseEndDate(LocalDate date) {
        return chooseDate(date, 2);
    }

    public ProtocolPage enterStartDate(String date) {
        return enterDate(date, "startDate");
    }

    public ProtocolPage enterEndDate(String date) {
        return enterDate(date, "endDate");
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

    private ProtocolPage enterDate(String date, String field) {
        $x(format(DATE_INPUT_FIELD_TEMPLATE, field)).sendKeys(date);

        return this;
    }
}
