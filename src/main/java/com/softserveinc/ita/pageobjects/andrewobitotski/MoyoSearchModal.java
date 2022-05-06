package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class MoyoSearchModal extends BasePage {

    @FindBy(name = "q")
    private WebElement searchInputField;

    public MoyoSearchModal(WebDriver driver) {
        initElements(driver, this);
    }

    public MoyoSearchResultPage search(String searchRequest) {
        searchInputField.sendKeys(searchRequest, Keys.ENTER);

        return new MoyoSearchResultPage(driver);
    }
}
