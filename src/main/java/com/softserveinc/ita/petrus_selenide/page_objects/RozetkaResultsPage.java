package com.softserveinc.ita.petrus_selenide.page_objects;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class RozetkaResultsPage {
    private final ElementsCollection listOfGoods = $$x("//span[@class ='goods-tile__title']");

    public int numberOfGoods() {
        return listOfGoods.size();
    }
}
