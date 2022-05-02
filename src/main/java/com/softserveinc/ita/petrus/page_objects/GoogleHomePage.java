package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleHomePage {
    WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    WebElement searchField;

    @FindBy(xpath = "//input[@name = 'btnK']")
    WebElement searchButton;

    @FindBy(xpath = "//h3[@class ='LC20lb MBeuO DKV0Md']")
    List<WebElement> titles;

    @FindBy(xpath = "//cite[contains(text(), 'https://rozetka.com.ua')][1]")
    public WebElement followPage;

    public void search(String query) {
        searchField.click();
        searchField.sendKeys(query);
        searchButton.click();
        new GoogleHomePage(driver);
    }

    public List<String> titlesToString() {
        return titles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public SearchPage clickOnFirstTitle() {
        followPage.click();
        return new SearchPage(driver);
    }


}
