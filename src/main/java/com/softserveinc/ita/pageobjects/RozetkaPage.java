package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class RozetkaPage {

  private final SelenideElement searchFieldRozetka = $x("//div/input");

  public RozetkaSearchResultsPage search(String key) {
    searchFieldRozetka.setValue(key);
    searchFieldRozetka.pressEnter();

    return new RozetkaSearchResultsPage();
  }

}
