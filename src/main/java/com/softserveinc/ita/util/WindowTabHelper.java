package com.softserveinc.ita.util;

import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class WindowTabHelper {

    public static String getCurrentUrl() {
        return getAndCheckWebDriver().getCurrentUrl();
    }
}
