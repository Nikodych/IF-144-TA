package com.softserveinc.ita.utils;

import com.softserveinc.ita.pageobjects.andrewobitotski.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementUtil extends BasePage {

    public static void clickElementWithJSExecutor(WebElement webElement) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", webElement);
    }
}
