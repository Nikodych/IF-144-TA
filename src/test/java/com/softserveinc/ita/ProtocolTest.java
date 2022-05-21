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
    @Description("Test to verify specific error message which should be present when there is wrong date order in date pickers")
    public void verifyErrorMessageWhenWrongDateOrder() {

        var actualResult = protocolPage
                .chooseStartDate(parse(END_DATE))
                .chooseEndDate(parse(START_DATE))
                .getErrorMessageWhenWrongDateOrder();

        assertThat(actualResult)
                .as("When there is wrong date order should be the next error message: " + ERROR_MESSAGE_WRONG_DATE_ORDER)
                .isEqualTo(ERROR_MESSAGE_WRONG_DATE_ORDER);
    }
}