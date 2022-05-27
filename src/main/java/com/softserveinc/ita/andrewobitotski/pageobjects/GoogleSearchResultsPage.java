package com.softserveinc.ita.andrewobitotski.pageobjects;

import static java.lang.String.*;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.PageFactory.initElements;

public class GoogleSearchResultsPage extends BasePage {

    public GoogleSearchResultsPage() {
        initElements(driver, this);
    }

    public MoyoHomePage checkInSearchResult(String mention) {
        var searchedResultLinkLocator = format("//a[contains(@href, '%s')]", mention);

        driver
                .findElements(xpath(searchedResultLinkLocator))
                .stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("There is no result with this mention"))
                .click();

        return new MoyoHomePage();
    }
}
