package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.PageFactory.initElements;

public class MoyoSearchResultPage extends BasePage {

    private final By searchResultXpath = By.xpath("//a[@class='product-item_name gtm-link-product']");

    public MoyoSearchResultPage(WebDriver driver) {
        initElements(driver, this);
    }

    public boolean verifySearchResultsArePresent() {
        try {
            WebElement searchResult = driver.findElement(searchResultXpath);
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    public MoyoShowMoreModal goToShowMoreButton() {
        return new MoyoShowMoreModal(driver);
    }

    public List<String> collectTitlesFromSearchResults() {
        if (verifySearchResultsArePresent()) {
            return driver
                    .findElements(searchResultXpath)
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }

        return null;
    }
}
