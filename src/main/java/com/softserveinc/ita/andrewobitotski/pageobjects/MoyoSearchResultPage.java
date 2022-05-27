package com.softserveinc.ita.andrewobitotski.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.PageFactory.initElements;

public class MoyoSearchResultPage extends BasePage {

    private final By searchResultLocator = xpath("//a[@class='product-item_name gtm-link-product']");

    public MoyoSearchResultPage() {
        initElements(driver, this);
    }

    public boolean areSearchResultsPresent() {
        try {
            return driver
                    .findElement(searchResultLocator)
                    .isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    public MoyoShowMoreModal goToShowMoreButton() {
        return new MoyoShowMoreModal();
    }

    public List<String> getSearchResultsTitles() {
        if (areSearchResultsPresent()) {
            return driver
                    .findElements(searchResultLocator)
                    .stream()
                    .map(WebElement::getText)
                    .collect(toList());
        }
        else {
            throw new AssertionError("No search results");
        }
    }
}
