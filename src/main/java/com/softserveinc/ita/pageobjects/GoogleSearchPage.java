package com.softserveinc.ita.pageobjects;

import Elements.Img;
import Elements.Input;
import Elements.Link;
import org.openqa.selenium.By;

import java.util.List;

public class GoogleSearchPage extends AbstractBasePage {

    private final Input googleInput = new Input(By.name("q"));
    private final Input searchButton = new Input(By.name("btnK"));
    private final Link searchResults = new Link(By.xpath("//div[@class='yuRUbf']/a"));
    private final Link followLink = new Link(By.xpath("//a[contains(@href, 'rozetka.com')]"));

    public GoogleSearchPage() {
        super(new Img(By.xpath("//img[@alt='Google']")));
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
