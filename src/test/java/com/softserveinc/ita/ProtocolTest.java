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

        LocalDate startDate = parse(START_DATE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.US);

        var currentMonth = protocolPage.chooseStartDate(startDate)
                .moveDatePickerBackward(1)
                .getCurrentMonth();


        var expectedMonth = startDate
                .minusMonths(1)
                .format(formatter);

        assertThat(currentMonth)
                .as("Date after moving one month backward should be chosen from previous month")
                .isEqualToIgnoringCase(expectedMonth);

        currentMonth = protocolPage
                .moveDatePickerForward(1)
                .getCurrentMonth();

        expectedMonth = startDate
                .plusMonths(1)
                .format(formatter);

        assertThat(currentMonth)
                .as("Date after moving one month forward should be chosen from next month")
                .isEqualToIgnoringCase(expectedMonth);
    }
}
