package com.softserveinc.ita.pageobjects.mkaralash;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.xpath;

public class GoogleSearchResultPage {
    protected WebDriver driver;

    public GoogleSearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getResultList() {
        List<WebElement> list = driver.findElements(xpath("//*[@id='rso']/div[not(@class= 'ULSxyf')]//a//h3"));
        return list;
    }

    public RozetkaHomePage clickLink(int index) {
        driver.findElement(xpath(String.format("(//*[@id='rso']/div[not(@class= 'ULSxyf')]//a//h3)[%s]", index))).click();
        return new RozetkaHomePage(driver);
    }
}
