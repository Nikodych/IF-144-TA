package com.softserveinc.ita.pageobjects.alukavenko;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RozetkaMainPage extends BasePage {

    @FindBy(xpath = "//a[@class = 'menu-categories__link' and @href='https://bt.rozetka.com.ua/']")
    private WebElement categoryBT;

    public RozetkaMainPage() {
        driver.get("https://rozetka.com.ua/");
        PageFactory.initElements(driver, this);
    }

    public RozetkaBTPage goToCategoryBT() {

        categoryBT.click();

        return new RozetkaBTPage();
    }
}
