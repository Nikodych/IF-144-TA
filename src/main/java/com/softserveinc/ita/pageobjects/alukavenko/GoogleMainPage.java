package com.softserveinc.ita.pageobjects.alukavenko;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.By;

public class GoogleMainPage {

    public GoogleSearchResultsPage search(String searchString) {
        $(By.name("q")).setValue(searchString).pressEnter();
        return page(GoogleSearchResultsPage.class);
    }
}
