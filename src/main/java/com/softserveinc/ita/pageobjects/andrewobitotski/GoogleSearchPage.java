package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.softserveinc.ita.utils.ReadDataFileValues.URL_GOOGLE_HOMEPAGE;
import static org.openqa.selenium.support.PageFactory.initElements;

public class GoogleSearchPage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchInputField;
    @FindBy(xpath = "//input[@name = 'btnK']")
    private WebElement searchButton;

    public GoogleSearchPage() {
        driver.get(URL_GOOGLE_HOMEPAGE);
        initElements(driver, this);
    }

    public GoogleSearchResultsPage performSearch(String searchRequest) {
        searchInputField.sendKeys(searchRequest);
        searchButton.click();

        return new GoogleSearchResultsPage();
    }
}
