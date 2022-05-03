package com.softserveinc.ita.pageobjects.alukavenko;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMainPage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(xpath = "//input[@name = 'btnK']")
    private WebElement searchButton;

    public GoogleMainPage() {
        driver.get("https://www.google.com/");
        PageFactory.initElements(driver, this);
    }

    public GoogleSearchResultsPage search(String searchString) {
        searchField.sendKeys(searchString);
        searchButton.click();

        return new GoogleSearchResultsPage();
    }
}
