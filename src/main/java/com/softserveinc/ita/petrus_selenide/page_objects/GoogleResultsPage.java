package com.softserveinc.ita.petrus_selenide.page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.util.stream.Collectors.toList;

public class GoogleResultsPage {
    private final ElementsCollection titlesList = $$x("//div[@id='search']//a/h3");

    public List<String> getTitles() {
        return titlesList
                .stream()
                .filter(SelenideElement::isDisplayed)
                .map(SelenideElement::getText)
                .map(String::toLowerCase)
                .collect(toList());
    }
}
