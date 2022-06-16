package com.softserveinc.ita.util;

import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.softserveinc.ita.util.DataProvider.LOGIN_PAGE_URL;
import static com.softserveinc.ita.util.SelenoidDriverProvider.setUpBrowser;
import static com.softserveinc.ita.util.SystemPropertyHelper.isRemote;

public abstract class TestRunner {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass(groups = {"positive", "negative"})
    public void setUp(@Optional("chrome") String browserName, @Optional("101") String browserVersion) {
        if (isRemote()) {
            setUpBrowser(browserName, browserVersion);
            browser = SelenoidDriverProvider.class.getName();
        } else {
            browser = "chrome";
            browserSize = "1920x1080";
        }

        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openLoginPage() {
        open(LOGIN_PAGE_URL);
    }
}
