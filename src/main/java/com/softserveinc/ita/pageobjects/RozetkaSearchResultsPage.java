package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$$x;

import com.codeborne.selenide.ElementsCollection;
import java.util.List;

public class RozetkaSearchResultsPage {

  private final ElementsCollection goodsList = $$x("//div[@class='goods-tile ng-star-inserted']");

  public List<String> getTitles() {
    return goodsList.texts();
  }
}
