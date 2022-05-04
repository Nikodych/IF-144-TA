package com.softserveinc.ita.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultsTest extends BaseTest {

  private static final String EXPECTED_TITLE = "rozetka";
  private static final String SEARCH_KEYWORD = "rozetka";
  private static final int EXPECTED_AMOUNT = 11;

  @Test
  public void checkElementsAmountOnSearchPage() {
    getHomePage().implicitWait(60);
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getHomePage().searchButtonClick();
    Assert.assertEquals(getSearchResultPage().getSearchResultsCount(), EXPECTED_AMOUNT);
  }

  @Test
  public void logo() {
    Assert.assertTrue(getHomePage().isLogoVisible());
  }

  @Test
  public void checkTitelsName() {
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getHomePage().implicitWait(60);
    Assert.assertEquals(getSearchResultPage().getSearchResultsList().listIterator(), EXPECTED_TITLE);
  }

  @Test
  public void checkAddToCartTest(){
    getHomePage().implicitWait(60);
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getHomePage().searchButtonClick();
    getSearchResultPage().getSearchResultsList().get(0).click();
    getRozetkaPage().makeSearch("iphone 12");
    getRozetkaPage().searchButtonClick();


  }
}
