package com.softserveinc.ita.pageobjects.alukavenko;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage extends BasePage {

    @FindAll({@FindBy(xpath = "//cite/../../h3")})
    private List<WebElement> searchResults;

    public List<String> getSearchResultsText() {
        return searchResults.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
