package com.softserveinc.ita.alukavenko.pageobjects;

import static org.openqa.selenium.support.PageFactory.initElements;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected static WebDriver driver;

    public BasePage() {
        initElements(driver, this);
    }

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
