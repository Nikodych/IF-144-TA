package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class GoogleSearchResultsPage {

    public MoyoHomePage checkInSearchResult(String mention) {

        String linkTemplate = "//a[contains(@href, '%s')]";
        String searchResultLinkXpath = String.format(linkTemplate, mention);
        List<SelenideElement> searchResults = $$x(searchResultLinkXpath);

        searchResults
                .stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("There is no link that contains " + "'"+ mention + "'"))
                .click();

        return new MoyoHomePage();
    }
}
