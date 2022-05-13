package com.softserveinc.ita.petrus_selenide.page_objects;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.petrus_selenide.util.ConfigProvider;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class RozetkaHomePage {
    private final SelenideElement searchField = $x("//input[@name = 'search']");
    private final SelenideElement searchButton = $x("//button[contains(@class, 'search-form__submit')]");

    public RozetkaHomePage() {
        open(ConfigProvider.ROZETKA_URL);
    }

    public RozetkaResultsPage findGoods(String query) {
        searchField.setValue(query);
        searchButton.click();

        return new RozetkaResultsPage();
    }
}
