package com.softserveinc.ita.pageobjects.util;

import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class WindowTabHelper {

    public static String getCurrentUrl() {
        return getAndCheckWebDriver().getCurrentUrl();
    }
}
