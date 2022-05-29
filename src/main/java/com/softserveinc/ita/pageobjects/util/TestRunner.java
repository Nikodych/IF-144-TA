package com.softserveinc.ita.pageobjects.util;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.open;
import static com.softserveinc.ita.pageobjects.util.DataProvider.LOGIN_PAGE_URL;

public abstract class TestRunner {

    @BeforeClass
    public void setUp() {
        browser = "chrome";
        browserSize = "1920x1080";
    }

    @BeforeMethod
    public void openLoginPage() {
        open(LOGIN_PAGE_URL);
    }
}
