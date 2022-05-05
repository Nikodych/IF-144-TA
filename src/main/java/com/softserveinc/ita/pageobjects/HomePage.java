package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

  @FindBy(xpath = "//input[@class='gLFyf gsfi']")
  private WebElement searchField;
  @FindBy(xpath = "//div[@class='CcAdNb']")
  private WebElement searchButton;

  public HomePage(WebDriver driver) {
    super(driver);
  }

  public void makeSearch(String keyword) {
    searchField.clear();
    searchField.sendKeys(keyword);
    searchField.sendKeys(Keys.ENTER);
  }
}
