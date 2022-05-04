package com.softserveinc.ita.pageobjects.mkaralash;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class CartWindow {
    protected WebDriver driver;

    public CartWindow(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEmpty() {
        return driver.findElements(xpath("//ul[contains(@class, 'cart-list')]//li")).isEmpty();
    }
}
