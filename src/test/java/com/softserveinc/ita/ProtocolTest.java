package com.softserveinc.ita;

import static com.codeborne.selenide.Selenide.webdriver;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.ProtocolPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.Test;

public class ProtocolTest extends TestRunner {

    @Test
    public void verifyProtocolPageOpening() {
        // TODO: 13.05.2022 use config files for credentials 
        ProtocolPage protocolPage = new LoginPage().login("admin", "dtapi_admin").openProtocolPage();

        String currentUrl =  webdriver().object().getCurrentUrl();
        assertThat(currentUrl).isEqualTo("https://dtapi.if.ua/admin/protocol");
    }
}
