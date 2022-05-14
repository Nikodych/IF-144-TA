package com.softserveinc.ita.tests.nkupchenko;

import com.softserveinc.ita.utils.TestRunner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RozetkaTest extends TestRunner {

  private static final String GOOGLE_SEARCH_KEYWORD = "rozetka";
  private static final String ROZETKA_SEARCH_KEYWORD = "iphone 12";
  private static final String EXPECTED_KEYWORD = "iphone";
  private static final int EXPECTED_AMOUNT_OF_GOODS = 3;


  @Test
  @Description("Verify amount of goods on Rozetka")
  public void checkAmountsOfGoods() {
    getGooglePage().makeSearch(GOOGLE_SEARCH_KEYWORD);
    getSearchResultPage().followFirstLink();
    getRozetkaPage().makeSearch(ROZETKA_SEARCH_KEYWORD);
    Assert.assertEquals(getRozetkaSearchResultsPage().getAmountOfGoods(),
        EXPECTED_AMOUNT_OF_GOODS);
  }

  @Test
  @Description("Verify that search results contains expected keyword")
  public void checkTitlesOfGoods() {
    getGooglePage().makeSearch(GOOGLE_SEARCH_KEYWORD);
    getSearchResultPage().followFirstLink();
    getRozetkaPage().makeSearch(ROZETKA_SEARCH_KEYWORD);
    getRozetkaSearchResultsPage().getTextOfGoods()
        .forEach(a -> Assert.assertTrue(a.contains(EXPECTED_KEYWORD)));
  }
}