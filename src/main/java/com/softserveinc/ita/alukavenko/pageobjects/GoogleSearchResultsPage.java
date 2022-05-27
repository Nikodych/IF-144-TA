package com.softserveinc.ita.alukavenko.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GoogleSearchResultsPage extends BasePage {

    @FindAll({@FindBy(xpath = "//cite/../../h3")})
    private List<WebElement> searchResults;

    public List<String> getSearchResultTitles() {
        return searchResults
                .stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(toList());
    }
}
