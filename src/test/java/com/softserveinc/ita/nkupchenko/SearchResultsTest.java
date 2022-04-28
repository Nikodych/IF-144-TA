package com.softserveinc.ita.nkupchenko;

import com.softserveinc.ita.nkupchenko.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchResultsTest extends BaseTest {

  private static final String EXPECTED_TITLE = "iphone";
  private static final String SEARCH_KEYWORD = "iphone 12";
  private static final int EXPECTED_AMOUNT = 24;

  @Test
  public void checkElementsAmountOnSearchPage(){
    getHomePage().implicitWait(60);
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getHomePage().clickSearchButton();
    Assert.assertEquals(getSearchResultPage().getSearchResultsCount(), EXPECTED_AMOUNT);
  }

  @Test
  public void logo(){
    Assert.assertTrue(getHomePage().isLogoVisible());
  }
  @Test
  public void checkTitelsName(){
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getHomePage().implicitWait(60);
    Assert.assertEquals(getSearchResultPage().getProductTitleList().listIterator(), EXPECTED_TITLE);
  }
}
