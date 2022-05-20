package com.softserveinc.ita;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.ProtocolPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProtocolTest extends TestRunner {

    private ProtocolPage protocolPage;

    @BeforeMethod
    public void openProtocolPage() {
        protocolPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openProtocolPage();
    }

    @Test
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
    public void verifyDatePickersArrowsWork() {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.US);
        String previousMonth = currentDate
                .minusMonths(1)
                .format(formatter);
        String nextMonth = currentDate
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

            currentMonth = protocolPage
                    .moveDatePickerForward(i)
                    .getCurrentMonth();

            assertThat(currentMonth)
                    .as(((i == 1) ? "Start" : "End") + " date after moving one month forward should be chosen from next month")
                    .isEqualToIgnoringCase(nextMonth);
        }
    }
}
