package com.softserveinc.ita.pageobjects.mkaralashselenide.pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class RozetkaSearchResultPage extends RozetkaBasePage {

    public RozetkaSearchResultPage addProductToTheCartBy(int index) {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable($x(format("(//div[@class='goods-tile__inner'])[%s]" +
                "//button[contains(@class,'buy-button')]", index)))).click();

        return new RozetkaSearchResultPage();
    }
}
