package com.softserveinc.ita.pageobjects.mkaralash.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class CartModal extends BasePage {
    public CartModal(WebDriver driver) {
        super(driver);
    }

    public boolean isEmpty() {
        return driver.findElements(xpath("//ul[contains(@class, 'cart-list')]//li")).isEmpty();
    }
}
