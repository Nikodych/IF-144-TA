package com.softserveinc.ita.alukavenko.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RozetkaHomeAppliancesPage extends BasePage {

    @FindAll({@FindBy(xpath = "//div[@class='tile-cats']")})
    private List<WebElement> subCategories;

    public int getSubCategoriesQuantity() {
        return subCategories.size();
    }
}
