package com.softserveinc.ita.petrus.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RozetkaResultsPage extends BasePage {
    @FindBy(xpath = "//span[@class ='goods-tile__title']")
    private List<WebElement> goodsList;

    public RozetkaResultsPage() {
        PageFactory.initElements(driver, this);
    }

    public int findAmountOfGoods() {
        return goodsList.size();
    }
}
