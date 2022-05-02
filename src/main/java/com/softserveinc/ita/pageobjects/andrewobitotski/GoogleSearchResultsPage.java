package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.NoSuchElementException;

import static org.openqa.selenium.By.xpath;

public class GoogleSearchResultsPage extends AbstractBasePage {

    private final String linkTemplate = "//a[contains(@href, '%s')]";
    private String searchedResultLinkXpath;
    private WebElement searchedResult;

    public GoogleSearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement checkInSearchResult(String mention) {
        searchedResultLinkXpath = String.format(linkTemplate, mention);

        try {
            searchedResult = driver.findElement(xpath(searchedResultLinkXpath));
        } catch (NoSuchElementException noSuchElementException) {
            searchedResult = null;
            throw new NoSuchElementException("There is no result with this mention");
        }

        return searchedResult;
    }
}
