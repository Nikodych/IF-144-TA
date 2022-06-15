package com.softserveinc.ita.pageobjects.mkaralash.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class RozetkaSearchResultPage extends RozetkaBasePage {
    public RozetkaSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public RozetkaSearchResultPage addProductToTheCartBy(int index) {
        var wait = new WebDriverWait(driver, 10);
        wait.until(elementToBeClickable(xpath(format("(//div[@class='goods-tile__inner'])[%s]" +
                "//button[contains(@class,'buy-button')]", index)))).click();

        return new RozetkaSearchResultPage(driver);
    }
}
