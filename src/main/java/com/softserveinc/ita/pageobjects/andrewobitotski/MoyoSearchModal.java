package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.*;

public class MoyoSearchModal {

    private final SelenideElement searchInputField = $(By.name("q"));

    public MoyoSearchResultPage performSearch(String searchRequest) {
        searchInputField.sendKeys(searchRequest, ENTER);

        return new MoyoSearchResultPage();
    }
}
