package com.softserveinc.ita.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage {

  @FindBy(xpath = "//h3[@class='LC20lb MBeuO DKV0Md']")
  private List<WebElement> searchResultsList;

  public SearchResultsPage(WebDriver driver) {
    super(driver);
  }

  public List<WebElement> getSearchResultsList() {
    return searchResultsList;
  }
}
