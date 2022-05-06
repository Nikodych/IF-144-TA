package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.PageFactory.initElements;

public class GoogleSearchResultsPage extends BasePage {

    public GoogleSearchResultsPage(WebDriver driver) {
        initElements(driver, this);
    }

    public MoyoHomePage checkInSearchResult(String mention) {

        String linkTemplate = "//a[contains(@href, '%s')]";
        String searchedResultLinkXpath = String.format(linkTemplate, mention);

        driver
                .findElements(xpath(searchedResultLinkXpath))
                .stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("There is no result with this mention"))
                .click();

        return new MoyoHomePage(driver);
    }
}
