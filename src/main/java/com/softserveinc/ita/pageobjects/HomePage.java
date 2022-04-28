package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(id = "searchbox")
  private WebElement searchField;
  @FindBy(xpath = "//input[@type='submit']")
  private WebElement searchButton;
  @FindBy(className = "logo-city")
  private WebElement logo;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public boolean isLogoVisible() {
    return logo.isDisplayed();
  }

  public void clickSearchButton(){
    searchButton.click();
  }
  public void makeSearch(String keyword) {
    searchField.sendKeys(keyword);
  }
}
