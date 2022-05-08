package com.softserveinc.ita.pageobjects.mkaralashselenide.pages;

import static com.codeborne.selenide.Selenide.$$x;

public class CartModal {
    public boolean isEmpty() {
        return $$x("//ul[contains(@class, 'cart-list')]//li").isEmpty();
    }
}
