package com.softserveinc.ita.pageobjects;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaSearchResultsPage extends BasePage {

  @FindBy(xpath = "//div[@class='goods-tile ng-star-inserted']")
  private List<WebElement> searchingGoodsList;

  public RozetkaSearchResultsPage(WebDriver driver) {
    super(driver);
  }

  public List<String> getTextOfGoods() {
    return searchingGoodsList
        .stream()
        .map(WebElement::getText)
        .map(String::toLowerCase)
        .collect(Collectors.toList());
  }

  public int getAmountOfGoods(){
    return searchingGoodsList.size();
  }

}
