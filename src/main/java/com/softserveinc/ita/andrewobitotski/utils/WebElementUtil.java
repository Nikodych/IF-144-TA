package com.softserveinc.ita.andrewobitotski.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.softserveinc.ita.andrewobitotski.pageobjects.BasePage.*;

public class WebElementUtil {

    public static void jsClick(WebElement webElement) {
        var jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].click()", webElement);
    }
}
