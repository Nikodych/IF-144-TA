package com.softserveinc.ita.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;

public class FollowLinkPage {

    private final SelenideElement searchInput = $x("//input[@name='search']");
    private final SelenideElement searchButton = $x("//button[contains (@class, 'search-form__submit')]");
    private final SelenideElement searchResult = $x("//p[contains (@class, 'catalog-selection__label')]");
    private final SelenideElement lotsCount = $x("//ul[contains (@class, 'catalog-grid')]");

    public void search(String text) {
        searchInput.sendKeys(text);
        searchButton.click();
    }

    public int getResult() {

        return parseInt(searchResult.text().replaceAll("[^0-9]", ""));
    }

    public int getLotsCount() {

        return parseInt(lotsCount.getAttribute("childElementCount"));
    }
}
