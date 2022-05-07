package com.softserveinc.ita.pageobjects.mkaralash.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;

public class RozetkaSearchResultPage extends RozetkaBasePage {
    public RozetkaSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public RozetkaSearchResultPage addProductToTheCartBy(int index) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(xpath(format("(//div[@class='goods-tile__inner'])[%s]" +
                "//button[contains(@class,'buy-button')]", index)))).click();

        return new RozetkaSearchResultPage(driver);
    }
}
