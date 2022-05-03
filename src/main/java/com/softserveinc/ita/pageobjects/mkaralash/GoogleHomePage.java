package com.softserveinc.ita.pageobjects.mkaralash;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class GoogleHomePage {
    protected WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public GoogleHomePage inputSearchName(String name) {
        driver.findElement(name("q")).sendKeys(name);
        return this;
    }

    public GoogleSearchResultPage clickSearchButton() {
        driver.findElement(xpath("//input[@name = 'btnK']")).click();
        return new GoogleSearchResultPage(driver);
    }
}
