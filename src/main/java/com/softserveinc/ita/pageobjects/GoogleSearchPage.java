package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchPage {
    private final SelenideElement googleInput = $x("//input[@name='q']");
    private final SelenideElement searchButton = $x("//input[@name = 'btnK']");
    private final ElementsCollection searchResults = $$x("//div[@class='yuRUbf']/a");
    private final SelenideElement followLink = $x("//a[contains(@href, 'rozetka.com')]");

    public void search(String text) {
        googleInput.sendKeys(text);
        searchButton.click();
    }

    public List<String> getTextOfResults() {

        return searchResults.texts();
    }

    public FollowLinkPage clickFollowLink() {
        followLink.click();

        return new FollowLinkPage();
    }
}
