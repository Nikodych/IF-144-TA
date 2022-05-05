package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(final WebDriver driver) {
        this.driver = driver;
    }

    public abstract boolean isPageOpened();
}
