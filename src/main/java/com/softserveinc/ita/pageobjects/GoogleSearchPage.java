package com.softserveinc.ita.pageobjects;

import Elements.Img;
import Elements.Input;
import Elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class GoogleSearchPage extends BasePage {

    private final Input googleInput = new Input(driver, By.name("q"));
    private final Input searchButton = new Input(driver, By.name("btnK"));
    private final Link searchResults = new Link(driver, By.xpath("//div[@class='yuRUbf']/a"));
    private final Link followLink = new Link(driver, By.xpath("//a[contains(@href, 'rozetka.com')]"));

    public GoogleSearchPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {

        return new Img(driver, By.xpath("//img[@alt='Google']")).areElementsDisplayed();
    }

    public void inputText(String text) {
        googleInput.sendKeys(text);
    }

    public void clickSearchButton() {
        searchButton.clickElement();
    }

    public List<String> getTextOfResults() {

        return searchResults.getTextOfElements();
    }

    public void clickFollowLink() {
        followLink.clickElement();
    }
}
