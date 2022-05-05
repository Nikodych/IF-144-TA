package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebDriver;

abstract public class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
