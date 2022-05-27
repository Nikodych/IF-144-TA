package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.ProtocolPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.DateTimeUtil.getCurrentDate;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Locale.US;
import static org.assertj.core.api.Assertions.assertThat;

public class ProtocolTest extends TestRunner {

    private ProtocolPage protocolPage;

    @BeforeMethod
    public void openProtocolPage() {
        protocolPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openProtocolPage();
    }

    @Test
    @Description("Test to verify Protocol page opening")
    public void verifyProtocolPageOpening() {

        var expectedUrl = PROTOCOL_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test
    public void verifyDatePickersOptionsChoosing() {

        var actualResult = protocolPage
                .chooseStartDate(parse(START_DATE))
                .chooseEndDate(parse(END_DATE))
                .isSearchButtonEnabled();

        assertThat(actualResult)
                .as("When both date pickers are filled correctly search button should be enabled")
                .isTrue();
    }

    @Test
    @Description("Test to verify search results are present when there is valid date range")
    public void verifySearchResultIsPresent() {

        var actualResult = protocolPage
                .chooseStartDate(parse(START_DATE))
                .chooseEndDate(parse(END_DATE))
                .performSearch()
                .isSearchResultPresent();

        assertThat(actualResult)
                .as("Search result should be present when there is valid date range")
                .isTrue();
    }

    @Test
    @Description("Test to verify the date input fields work with valid input")
    public void verifyDateInputFields() {

        var actualResult = protocolPage
                .setStartDate(START_DATE)
                .setEndDate(END_DATE)
                .isSearchButtonEnabled();

        assertThat(actualResult)
                .as("When both date input fields are entered correctly search button should be enabled")
                .isTrue();
    }

    @Test
    @Description("Test to verify the correct work of moving date pickers backward and forward by arrows")
    public void verifyDatePickersArrowsWork() {

        var currentDate = getCurrentDate();
        var formatter = ofPattern("MMM yyyy", US);
        var previousMonth = currentDate
                .minusMonths(1)
                .format(formatter);
        var nextMonth = currentDate
                .plusMonths(1)
                .format(formatter);

        String currentMonth;

        for (int i = 1; i <= 2; i++) { // index 1 stands for start date, 2 - for end date
            currentMonth = protocolPage
                    .moveDatePickerBackward(i)
                    .getCurrentMonth();

            assertThat(currentMonth)
                    .as(((i == 1) ? "Start" : "End") + " date after moving one month backward should be chosen from previous month")
                    .isEqualToIgnoringCase(previousMonth);

            protocolPage.closeDatePickerWindow(); // in order to perform next steps

            currentMonth = protocolPage
                    .moveDatePickerForward(i)
                    .getCurrentMonth();

            assertThat(currentMonth)
                    .as(((i == 1) ? "Start" : "End") + " date after moving one month forward should be chosen from next month")
                    .isEqualToIgnoringCase(nextMonth);

            protocolPage.closeDatePickerWindow(); // in order to perform next steps
        }
    }

    @Test
    public void verifySearchButtonIsDisabledWhenOnlyStartDateIsFilled() {

        var actualResult = protocolPage
                .chooseStartDate(parse(START_DATE))
                .isSearchButtonEnabled();

        assertThat(actualResult)
                .as("When only start date picker is filled correctly search button should be disabled")
                .isFalse();
    }

    @Test
    public void verifySearchButtonIsDisabledWhenOnlyEndDateIsFilled() {

        var actualResult = protocolPage
                .chooseEndDate(parse(END_DATE))
                .isSearchButtonEnabled();

        assertThat(actualResult)
                .as("When only end date picker is filled correctly search button should be disabled")
                .isFalse();
    }

    @Test
    @Description("Test to verify the correctness of error message which should be present when there is wrong date order in date pickers")
    public void verifyErrorMessageWhenWrongDateOrder() {

        var actualResult = protocolPage
                .chooseStartDate(parse(END_DATE))
                .chooseEndDate(parse(START_DATE))
                .getErrorMessage();

        assertThat(actualResult)
                .as("When there is wrong date order should be the next error message: " + ERROR_MESSAGE_WRONG_DATE_ORDER)
                .isEqualTo(ERROR_MESSAGE_WRONG_DATE_ORDER);
    }
}
