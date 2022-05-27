package com.softserveinc.ita.alukavenko.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class GoogleMainPage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(xpath = "//input[@name = 'btnK']")
    private WebElement searchButton;

    public GoogleMainPage() {
        driver.get("https://www.google.com/");
        initElements(driver, this);
    }

    public GoogleSearchResultsPage search(String searchString) {
        searchField.sendKeys(searchString);
        searchButton.click();

        return new GoogleSearchResultsPage();
    }
}
