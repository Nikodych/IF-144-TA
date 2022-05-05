package com.softserveinc.ita.tests.nkupchenko;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RozetkaTest extends TestRunner {

  private static final String SEARCH_KEYWORD = "rozetka";
  private static final int EXPECTED_AMOUNT = 8;
  private static final int EXPECTED_AMOUNT_OF_GOODS = 3;

  @Test
  public void checkElementsAmountOnSearchPage() {
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getHomePage().implicitWait(60);
    Assert.assertEquals(getSearchResultPage().getSearchResultsList().size(), EXPECTED_AMOUNT);
  }

  @Test
  public void checkAmountsOfGoods() {
    getHomePage().implicitWait(60);
    getHomePage().makeSearch(SEARCH_KEYWORD);
    getSearchResultPage().getSearchResultsList().get(0).click();
    getRozetkaPage().implicitWait(60);
    getRozetkaPage().makeSearch("iphone 12");
    Assert.assertEquals(getRozetkaSearchResultsPage().getSearchingGoodsList().size(),
        EXPECTED_AMOUNT_OF_GOODS);


  }
}
