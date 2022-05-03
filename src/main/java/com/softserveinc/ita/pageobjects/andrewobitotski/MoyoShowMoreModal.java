package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoyoShowMoreModal extends AbstractBasePage {

    private final By showMoreButtonXpath = By.xpath("//button[@class='btn btn--yellow js-load-more-products']");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    JavascriptExecutor jse = (JavascriptExecutor) driver;

    public MoyoShowMoreModal(WebDriver webDriver) {
        super();
    }

    public boolean isShowMoreButtonPresent() {
        try {
            WebElement showMoreButton = driver.findElement(showMoreButtonXpath);
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }

        return true;
    }

    private WebElement waitForShowMoreButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(showMoreButtonXpath));
    }

    public MoyoShowMoreModal showMoreSearchResults() {
        if (isShowMoreButtonPresent()) {
            jse.executeScript("arguments[0].click()", waitForShowMoreButton());
        }
        return this;
    }

    public MoyoSearchResultPage showAllSearchResults() {
        do {
            showMoreSearchResults();
        } while (isShowMoreButtonPresent());
        return new MoyoSearchResultPage(driver);
    }
}
