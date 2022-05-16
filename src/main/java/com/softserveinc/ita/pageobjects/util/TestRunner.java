package com.softserveinc.ita.pageobjects.util;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestRunner {

    @BeforeClass
    public void setUp() {
        browser = "chrome";
    }

    @BeforeMethod
    public void openLoginPage() {
        // TODO: 16.05.2022 use config files for url
        open("https://dtapi.if.ua/");
    }
}
