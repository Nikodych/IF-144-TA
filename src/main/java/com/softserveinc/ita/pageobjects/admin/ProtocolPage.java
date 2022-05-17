package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class ProtocolPage extends MainMenu {

    private final SelenideElement startDatePickerButton = $x("(//button[@aria-label='Open calendar'])[1]");
    private final SelenideElement endDatePickerButton = $x("(//button[@aria-label='Open calendar'])[2]");
    private final SelenideElement chooseMonthAndYearButton = $x("//button[@aria-label='Choose month and year']");
    private final SelenideElement searchButton = $x("//button[@type='submit']");

    private final String optionButtonTemplate = "//div[text()='%s']";

    public ProtocolPage chooseStartDate(String startYear, String startMonth, String startDay) {
        SelenideElement startYearButton = $x(format(optionButtonTemplate, startYear));
        SelenideElement startMonthButton = $x(format(optionButtonTemplate, startMonth));
        SelenideElement startDayButton = $x(format(optionButtonTemplate, startDay));

        startDatePickerButton.click();
        chooseMonthAndYearButton.click();
        startYearButton.click();
        startMonthButton.click();
        startDayButton.click();

        return new ProtocolPage();
    }

    public ProtocolPage chooseEndDate(String endYear, String endMonth, String endDay) {
        SelenideElement endYearButton = $x(format(optionButtonTemplate, endYear));
        SelenideElement endMonthButton = $x(format(optionButtonTemplate, endMonth));
        SelenideElement endDayButton = $x(format(optionButtonTemplate, endDay));

        endDatePickerButton.click();
        chooseMonthAndYearButton.click();
        endYearButton.click();
        endMonthButton.click();
        endDayButton.click();

        return new ProtocolPage();
    }

    public SelenideElement getSearchButton() {
        return searchButton;
    }
}
