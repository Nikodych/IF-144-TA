package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaPage extends BasePage {

  @FindBy(css = "body > app-root > div > div > rz-header > rz-main-header > header > div > div > div > form > div > div > input")
  private WebElement searchField;

  public RozetkaPage(WebDriver driver) {
    super(driver);
  }

  public void makeSearch(String keyword) {
    searchField.click();
    searchField.sendKeys(keyword);
    searchField.sendKeys(Keys.ENTER);
  }
}
