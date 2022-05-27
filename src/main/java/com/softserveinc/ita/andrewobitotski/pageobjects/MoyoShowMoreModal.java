package com.softserveinc.ita.andrewobitotski.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.softserveinc.ita.andrewobitotski.utils.WebElementUtil.jsClick;
import static java.time.Duration.*;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class MoyoShowMoreModal extends BasePage {

    private final By showMoreButtonLocator = xpath("//button[@class='btn btn--yellow js-load-more-products']");

    WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));

    public MoyoShowMoreModal() {
        super();
    }

    public MoyoShowMoreModal showMoreSearchResults() {
        if (isShowMoreButtonPresent()) {
            jsClick(waitForShowMoreButton());
        }

        return this;
    }

    public MoyoSearchResultPage showAllSearchResults() {
        var attempts = 0;

        do {
            showMoreSearchResults();
        } while (isShowMoreButtonPresent() && attempts++<20);

        return new MoyoSearchResultPage();
    }

    private boolean isShowMoreButtonPresent() {
        try {
            return driver
                    .findElement(showMoreButtonLocator)
                    .isDisplayed();
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    private WebElement waitForShowMoreButton() {
        return wait.until(visibilityOfElementLocated(showMoreButtonLocator));
    }
}
