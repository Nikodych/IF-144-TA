package com.softserveinc.ita.pageobjects.mkaralash.pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.openqa.selenium.By.xpath;

public class GoogleSearchResultPage extends BasePage {
    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getResultList() {
        return driver
                .findElements(xpath("//*[@id='rso']/div[not(@class= 'ULSxyf')]//a//h3"))
                .stream()
                .map(element -> element.getText().toLowerCase())
                .collect(toList());
    }

    public RozetkaHomePage openLinkBy(int index) {
        driver.findElement(xpath(format("(//*[@id='rso']/div[not(@class= 'ULSxyf')]//a//h3)[%s]", index))).click();

        return new RozetkaHomePage(driver);
    }
}
