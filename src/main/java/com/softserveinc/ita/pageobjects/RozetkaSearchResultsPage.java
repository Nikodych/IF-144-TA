package com.softserveinc.ita.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaSearchResultsPage extends BasePage {

  @FindBy(className = "catalog-grid__cell catalog-grid__cell_type_slim ng-star-inserted")
  private List<WebElement> searchingGoodsList;

  public RozetkaSearchResultsPage(WebDriver driver) {
    super(driver);
  }

  public List<WebElement> getSearchingGoodsList(){
    return searchingGoodsList;
  }
}
