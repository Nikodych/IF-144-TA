package com.softserveinc.ita.pageobjects.mkaralashselenide.pages;

import static com.codeborne.selenide.Selenide.$x;

public class GoogleHomePage {

    public GoogleHomePage setSearchValue(String name) {
        $x("//input[@name='q']").sendKeys(name);

        return this;
    }

    public GoogleSearchResultPage clickSearchButton() {
        $x("//input[@name = 'btnK']").click();

        return new GoogleSearchResultPage();
    }
}
