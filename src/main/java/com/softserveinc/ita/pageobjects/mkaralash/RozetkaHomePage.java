package com.softserveinc.ita.pageobjects.mkaralash;

import org.openqa.selenium.WebDriver;

public class RozetkaHomePage {
    protected WebDriver driver;

    public RozetkaHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public RozetkaHeaderPage getRozetkaHeaderPage() {
        return new RozetkaHeaderPage(driver);
    }
}
