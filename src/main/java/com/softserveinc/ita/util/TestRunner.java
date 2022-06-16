package com.softserveinc.ita.util;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.open;
import static com.softserveinc.ita.util.DataProvider.LOGIN_PAGE_URL;

public abstract class TestRunner {

    protected SoftAssertions soft;

    @BeforeClass (groups = {"positive", "negative"})
    public void setUp() {
        browser = "chrome";
        browserSize = "1920x1080";
    }

    @BeforeMethod (groups = {"positive", "negative"})
    public void openLoginPage() {
        open(LOGIN_PAGE_URL);
    }
}
