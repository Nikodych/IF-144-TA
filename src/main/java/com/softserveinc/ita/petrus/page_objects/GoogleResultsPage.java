package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GoogleResultsPage extends BasePage{
    @FindBy(xpath = "//h3[contains(@href, *)]")
    private List<WebElement> googleResultsList;

    @FindBy(xpath = "//cite[contains(text(), 'https://rozetka.com.ua')][1]")
    private WebElement rozetkaHomePage;

    public GoogleResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public List<String> getTitles() {
        return googleResultsList.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(toList());
    }

    public RozetkaHomePage openFirstResult() {
        rozetkaHomePage.click();

        return new RozetkaHomePage();
    }
}
