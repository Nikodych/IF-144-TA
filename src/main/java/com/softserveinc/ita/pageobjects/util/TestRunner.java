package com.softserveinc.ita.pageobjects.util;

import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.BeforeClass;

public abstract class TestRunner {

    @BeforeClass
    public void setUp() {
        open("https://dtapi.if.ua/");
    }
}
