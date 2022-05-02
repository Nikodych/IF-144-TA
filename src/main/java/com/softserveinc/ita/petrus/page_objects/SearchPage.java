package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name = 'search']")
    public WebElement searchField;

    @FindBy(xpath = "//button[contains(@class, 'search-form__submit')]")
    public WebElement searchButton;

    @FindBy(xpath = "//span[@class ='goods-tile__title']")
    public List<WebElement> goods;


    public void searchGoods(String query) {
        searchField.sendKeys(query);
        searchButton.click();
    }

    public int amountOfGoods() {
        return goods.size();
    }
}
