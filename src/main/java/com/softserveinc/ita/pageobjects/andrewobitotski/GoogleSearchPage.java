package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.softserveinc.ita.utils.ReadDataFileValues.URL_GOOGLE_HOMEPAGE;

public class GoogleSearchPage {

    private final SelenideElement searchInputField = $(By.name("q"));
    private final SelenideElement searchButton = $x("//input[@name = 'btnK']");

    public GoogleSearchPage() {
        open(URL_GOOGLE_HOMEPAGE);
    }

    public GoogleSearchResultsPage performSearch(String searchRequest) {

        searchInputField.setValue(searchRequest);
        searchButton.click();

        return new GoogleSearchResultsPage();
    }
}
