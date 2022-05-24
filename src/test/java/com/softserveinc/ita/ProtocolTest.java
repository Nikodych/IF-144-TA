package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.ProtocolPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static java.time.LocalDate.parse;
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
}
