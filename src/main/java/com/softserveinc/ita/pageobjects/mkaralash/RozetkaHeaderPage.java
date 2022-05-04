package com.softserveinc.ita.pageobjects.mkaralash;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class RozetkaHeaderPage {
    protected WebDriver driver;

    public RozetkaHeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartWindow openCart() {
        driver.findElement(xpath("//li[contains(@class, 'cart')]//button")).click();
        return new CartWindow(driver);
    }
}
