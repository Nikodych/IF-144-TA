package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends BasePage {

  @FindBy(name = "q")
  private WebElement searchField;

  public GooglePage(WebDriver driver) {
    super(driver);
  }

  public SearchResultsPage makeSearch(String keyword) {
    searchField.clear();
    searchField.sendKeys(keyword);
    searchField.sendKeys(Keys.ENTER);
    return new  SearchResultsPage(driver);
  }
}
