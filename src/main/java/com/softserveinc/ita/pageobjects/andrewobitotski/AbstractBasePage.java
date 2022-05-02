package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.WebDriver;

public abstract class AbstractBasePage {

    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

}
