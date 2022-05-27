package com.softserveinc.ita.pageobjects.andrewobitotski.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.softserveinc.ita.pageobjects.andrewobitotski.pageobjects.BasePage.*;

public class WebElementUtil {

    public static void jsClick(WebElement webElement) {
        WebDriver driver = getDriver();
        var jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click()", webElement);
    }
}
