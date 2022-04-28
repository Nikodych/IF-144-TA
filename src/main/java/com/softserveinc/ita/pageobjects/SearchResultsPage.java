package com.softserveinc.ita.pageobjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BasePage{

  @FindBy(xpath = "//div[@class='ty-column3']")
  private List<WebElement> searchResultsList;
  @FindBy(xpath = "//div[@class='ut2-gl__name']")
  private List<WebElement> productTitleList;

  ArrayList<String> titels = new ArrayList<>();

  public SearchResultsPage(WebDriver driver) {
    super(driver);
  }

  public List<WebElement> getSearchResultsList(){
    return searchResultsList;
  }

  public List<WebElement> getProductTitleList(){
    return productTitleList;
  }

  public int getSearchResultsCount(){
    return getSearchResultsList().size();
  }

  public void getTitleText(){
    for (WebElement list : productTitleList){
      titels.add(list.getText());
    }
  }

}
