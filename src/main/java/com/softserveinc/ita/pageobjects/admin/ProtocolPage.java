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

    private ProtocolPage chooseDate(String date, int index) {
        LocalDate formattedDate = LocalDate.parse(date);
        $x(format(DATE_PICKER_TEMPLATE, index)).click();
        chooseMonthAndYearButton.click();
        $x(format(OPTION_BUTTON_TEMPLATE, formattedDate.getYear())).click();
        $x(format(OPTION_BUTTON_TEMPLATE, formattedDate.getMonth().toString().substring(0, 3))).click();
        $x(format(OPTION_BUTTON_TEMPLATE, formattedDate.getDayOfMonth())).click();

        return this;
    }

    public ProtocolPage chooseStartDate(String date) {
        return chooseDate(date, 1);
    }

    public ProtocolPage chooseEndDate(String date) {
        return chooseDate(date, 2);
    }

    public boolean isSearchButtonEnabled() {
        return searchButton.isEnabled();
    }
}
