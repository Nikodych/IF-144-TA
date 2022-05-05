package com.softserveinc.ita.pageobjects.alukavenko;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class RozetkaMainPage extends BasePage {

    @FindBy(xpath = "//a[@class = 'menu-categories__link' and @href='https://bt.rozetka.com.ua/']")
    private WebElement homeAppliancesCategory;

    public RozetkaMainPage() {
        driver.get("https://rozetka.com.ua/");
        initElements(driver, this);
    }

    public RozetkaHomeAppliancesPage goToHomeAppliancesCategory() {
        homeAppliancesCategory.click();
        return new RozetkaHomeAppliancesPage();
    }
}
