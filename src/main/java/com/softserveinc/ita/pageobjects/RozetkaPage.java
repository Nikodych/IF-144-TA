package com.softserveinc.ita.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaPage extends BasePage {

  @FindBy(className = "search-form__input ng-untouched ng-pristine ng-valid")
  private WebElement searchField;
  @FindBy(className = "button button_color_green button_size_medium search-form__submit ng-star-inserted")
  private WebElement searchButton;

  public RozetkaPage(WebDriver driver) {
    super(driver);
  }
  public void searchButtonClick() {
    searchButton.click();
  }

  public void makeSearch(String keyword) {
    searchField.sendKeys(keyword);
  }
}
