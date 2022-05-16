package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.util.DataProvider;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.Test;

public class ProtocolTest extends TestRunner {

    @Test
    public void verifyProtocolPageOpening() {
        new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openProtocolPage();
        var expectedUrl = PROTOCOL_PAGE_URL;
        var currentUrl =  webdriver().object().getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }
}
