package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.ProtocolPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProtocolTest extends TestRunner {

    @BeforeMethod
    public ProtocolPage openProtocolPage() {
        new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openProtocolPage();

        return new ProtocolPage();
    }

    @Test
    public void verifyProtocolPageOpening() {

        var expectedUrl = PROTOCOL_PAGE_URL;
        var currentUrl = webdriver().object().getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test
    public void verifyDatePickersOptionsChoosing() {

        var actualResult = new ProtocolPage()
                .chooseStartDate(START_YEAR, START_MONTH, START_DAY)
                .chooseEndDate(END_YEAR, END_MONTH, END_DAY)
                .getSearchButton()
                .isEnabled();

        assertThat(actualResult)
                .as("When both date pickers are filled correctly search button should be enabled")
                .isTrue();
    }
}
