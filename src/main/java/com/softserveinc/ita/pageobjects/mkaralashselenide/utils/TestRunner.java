package com.softserveinc.ita.pageobjects.mkaralashselenide.utils;

import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

public class TestRunner {
    @BeforeClass
    public void openHomePage() {
        open("https://www.google.com/");
    }
}
