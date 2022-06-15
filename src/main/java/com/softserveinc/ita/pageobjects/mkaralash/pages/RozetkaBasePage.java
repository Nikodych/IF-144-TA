package com.softserveinc.ita.pageobjects.mkaralash.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public abstract class RozetkaBasePage extends BasePage {
    public RozetkaBasePage(WebDriver driver) {
        super(driver);
    }

    public CartModal openCart() {
        driver.findElement(xpath("//li[contains(@class, 'cart')]//button")).click();

        return new CartModal(driver);
    }

    public RozetkaBasePage setSearchValue(String name) {
        driver.findElement(xpath("//input[@name = 'search']")).sendKeys(name);

        return this;
    }

    public RozetkaSearchResultPage clickSearchButton() {
        driver.findElement(xpath("//button[contains(@class, 'search-form__submit')]")).click();

        return new RozetkaSearchResultPage(driver);
    }
}
