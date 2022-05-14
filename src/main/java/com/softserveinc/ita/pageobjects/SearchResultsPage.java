package com.softserveinc.ita.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {

  @FindBy(xpath = "//div/a/h3")
  private List<WebElement> searchResultsList;

  public SearchResultsPage(WebDriver driver) {
    super(driver);
  }

  public List<WebElement> getSearchResultsList() {
    return searchResultsList;
  }

  public void followFirstLink(){
    searchResultsList.get(0).click();
  }
}
