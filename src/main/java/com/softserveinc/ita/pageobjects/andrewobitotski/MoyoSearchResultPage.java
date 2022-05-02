package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MoyoSearchResultPage extends AbstractBasePage {

    private final By searchResultXpath = By.xpath("//a[@class='product-item_name gtm-link-product']");
    private WebElement searchResult;

    public MoyoSearchResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean verifySearchResultsArePresent() {
        try {
            searchResult = driver.findElement(searchResultXpath);
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    public List<WebElement> collectElementsByXPath() {
        if (verifySearchResultsArePresent()) {
            List<WebElement> collectedElements = driver.findElements(searchResultXpath);
            return collectedElements;
        }

        return null;
    }

}
