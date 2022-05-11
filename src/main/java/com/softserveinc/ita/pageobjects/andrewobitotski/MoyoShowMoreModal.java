package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class MoyoShowMoreModal {

    private final String showMoreButtonXpath = "//button[@class='btn btn--yellow js-load-more-products']";
    private final SelenideElement showMoreButton = $x(showMoreButtonXpath);

    public boolean isShowMoreButtonPresent() {
        try {
            showMoreButton
                    .shouldBe(exist, ofSeconds(5));
        } catch (ElementNotFound e) {
            return false;
        }

        return true;
    }

    public MoyoShowMoreModal showMoreSearchResults() {
        if (isShowMoreButtonPresent()) {
            showMoreButton
                    .should(appear, ofSeconds(5))
                    .click();
        }
        return this;
    }

    public MoyoSearchResultPage showAllSearchResults() {
        if (isShowMoreButtonPresent()) {
            do {
                showMoreSearchResults();
            } while (isShowMoreButtonPresent());
        }

        return new MoyoSearchResultPage();
    }
}
