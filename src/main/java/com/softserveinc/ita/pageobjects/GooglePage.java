package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GooglePage {

  private final SelenideElement searchField = $(By.name("q"));

  public void makeSearch(String keyword) {
    searchField.setValue(keyword);
    searchField.pressEnter();
  }
}
