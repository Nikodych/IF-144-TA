package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.support.PageFactory.initElements;

public class MoyoSearchResultPage {

    private final String searchResultXpath = "//a[@class='product-item_name gtm-link-product']";
    private final SelenideElement searchResult = $x(searchResultXpath);
    private final List<SelenideElement> searchResults = $$x(searchResultXpath);

    public boolean verifySearchResultsArePresent() {
        return searchResult.exists();
    }

    public MoyoShowMoreModal goToShowMoreButton() {
        return new MoyoShowMoreModal();
    }

    public List<String> collectTitlesFromSearchResults() {
        if (verifySearchResultsArePresent()) {
            return searchResults
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }

        return null;
    }

//    public boolean verifySearchResultsArePresent() {
//        try {
//            WebElement searchResult = driver.findElement(searchResultXpath);
//            return true;
//        } catch (NoSuchElementException noSuchElementException) {
//            return false;
//        }
//    }
}
