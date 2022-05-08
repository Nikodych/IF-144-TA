package com.softserveinc.ita.pageobjects.mkaralashselenide.pages;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class GoogleSearchResultPage {

    public List<String> getResultList() {
        return $$x("//*[@id='rso']//div[contains(@class, 'VwiC3b')]/span").texts();
    }

    public RozetkaHomePage openLinkBy(int index) {
        $x(format("(//*[@id='rso']/div[not(@class= 'ULSxyf')]//a//h3)[%s]", index)).click();

        return new RozetkaHomePage();
    }
}
