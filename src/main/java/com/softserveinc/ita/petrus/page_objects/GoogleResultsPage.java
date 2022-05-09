package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GoogleResultsPage extends BasePage{
    @FindBy(xpath = "//div[@id='search']//a/h3")
    private List<WebElement> googleResultsList;

    public GoogleResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public List<String> getTitles() {
        return googleResultsList.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .map(String::toLowerCase)
                .collect(toList());
    }
}