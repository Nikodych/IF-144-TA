package com.softserveinc.ita.pageobjects.util;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;
import static com.softserveinc.ita.pageobjects.util.DataProvider.LOGIN_PAGE_URL;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestRunner {

    @BeforeClass
    public void setUp() {
        browser = "chrome";
    }

    @BeforeMethod
    public void openLoginPage() {
        open(LOGIN_PAGE_URL);
    }
}
