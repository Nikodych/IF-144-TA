package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoyoSearchModal extends AbstractBasePage {

    @FindBy(name = "q")
    private WebElement searchBox;

    public MoyoSearchModal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public MoyoSearchResultPage search(String searchRequest) {
        searchBox.sendKeys(searchRequest, Keys.ENTER);

        return new MoyoSearchResultPage(driver);
    }
}
