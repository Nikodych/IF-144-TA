package com.softserveinc.ita.pageobjects.mkaralashselenide.pages;

import static com.codeborne.selenide.Selenide.$x;

public abstract class RozetkaBasePage {

    public CartModal openCart() {
        $x("//li[contains(@class, 'cart')]//button").click();

        return new CartModal();
    }

    public RozetkaBasePage setSearchValue(String name) {
        $x("//input[@name = 'search']").sendKeys(name);

        return this;
    }

    public RozetkaSearchResultPage clickSearchButton() {
        $x("//button[contains(@class, 'search-form__submit')]").click();

        return new RozetkaSearchResultPage();
    }
}
