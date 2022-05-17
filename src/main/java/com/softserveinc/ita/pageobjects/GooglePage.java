package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class GooglePage {

  private final SelenideElement searchField = $(By.name("q"));

  public SearchResultsPage makeSearch(String keyword) {
    searchField.setValue(keyword);
    searchField.pressEnter();

    return new SearchResultsPage();
  }
}
