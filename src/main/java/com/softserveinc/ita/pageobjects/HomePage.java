package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(xpath = "//input[@class='gLFyf gsfi']")
  private WebElement searchField;
  @FindBy(xpath = "//div[@class='CcAdNb']")
  private WebElement searchButton;
  @FindBy(xpath = "//img[@class='lnXdpd']")
  private WebElement logo;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public boolean isLogoVisible() throws NullPointerException{
    return logo.isDisplayed();
  }

  public void searchButtonClick() {
    searchButton.click();
  }

  public void makeSearch(String keyword) throws NullPointerException{
    searchField.clear();
    searchField.sendKeys(keyword);
  }
}
