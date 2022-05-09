package com.softserveinc.ita.petrus.page_objects;

import com.softserveinc.ita.petrus.util.ConfigurationPropertiesHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RozetkaHomePage extends BasePage{
    @FindBy(xpath = "//input[@name = 'search']")
    private WebElement searchField;

    @FindBy(xpath = "//button[contains(@class, 'search-form__submit')]")
    private WebElement searchButton;

    public RozetkaHomePage(){
        PageFactory.initElements(driver, this);
        driver.get(ConfigurationPropertiesHelper.getProperty("rozetka.url"));
    }

    public RozetkaResultsPage findGoods(String query){
        searchField.click();
        searchField.sendKeys(query);
        searchButton.click();

        return new RozetkaResultsPage();
    }
}
