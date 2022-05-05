package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.softserveinc.ita.petrus.util.ConfigurationPropertiesHelper.getProperty;

public class GoogleHomePage extends BasePage{
    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(xpath = "//input[@name = 'btnK']")
    private WebElement searchButton;

    public GoogleHomePage() {
        PageFactory.initElements(driver, this);
        driver.get(getProperty("google.url"));
    }

    public GoogleResultsPage search(String query) {
        searchField.click();
        searchField.sendKeys(query);
        searchButton.click();

        return new GoogleResultsPage();
    }
}
