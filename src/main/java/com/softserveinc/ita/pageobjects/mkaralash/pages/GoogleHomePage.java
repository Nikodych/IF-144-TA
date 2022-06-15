package com.softserveinc.ita.pageobjects.mkaralash.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class GoogleHomePage extends BasePage {
    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleHomePage setSearchValue(String name) {
        driver.findElement(name("q")).sendKeys(name);

        return this;
    }

    public GoogleSearchResultPage clickSearchButton() {
        driver.findElement(xpath("//input[@name = 'btnK']")).click();

        return new GoogleSearchResultPage(driver);
    }
}
