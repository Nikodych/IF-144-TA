package com.softserveinc.ita.petrus_selenide.page_objects;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.petrus_selenide.util.ConfigProvider;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GoogleHomePage {
    private final SelenideElement searchField = $(By.name("q"));

    public GoogleHomePage () {
        Selenide.open(ConfigProvider.GOOGLE_URL);
    }

    public GoogleResultsPage search(String query) {
        searchField.setValue(query).pressEnter();

        return new GoogleResultsPage();
    }
}
