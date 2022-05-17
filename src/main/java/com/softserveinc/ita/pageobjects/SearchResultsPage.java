package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$$x;

import com.codeborne.selenide.ElementsCollection;

public class SearchResultsPage {

  private final ElementsCollection searchResults = $$x("//div/a/h3");

  public RozetkaPage followFirstLink() {
    searchResults.get(0).click();
    return new RozetkaPage();
  }
}
