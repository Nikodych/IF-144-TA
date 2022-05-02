package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.softserveinc.ita.utils.ReadConfigFileValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends AbstractBasePage {

    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(xpath = "//input[@name = 'btnK']")
    private WebElement searchButton;

    public GoogleSearchPage(WebDriver driver) {
        driver.get(ReadConfigFileValues.URL_GOOGLE_HOMEPAGE);
        PageFactory.initElements(driver, this);
    }

    public GoogleSearchResultsPage search(String searchRequest) {
        searchBox.sendKeys(searchRequest);
        searchButton.click();

        return new GoogleSearchResultsPage(driver);
    }
}
