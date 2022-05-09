package com.softserveinc.ita.tests.nkupchenko;

import com.softserveinc.ita.utils.TestRunner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RozetkaTest extends TestRunner {

  private static final String GOOGLE_SEARCH_KEYWORD = "rozetka";
  private static final String ROZETKA_SEARCH_KEYWORD = "iphone 12";
  private static final int EXPECTED_AMOUNT_GOOGLE = 6;
  private static final int EXPECTED_AMOUNT_OF_GOODS = 3;

  @Test
  @Description("Verify amount of results on google page")
  public void checkElementsAmountOnSearchPage() {
    getGooglePage().makeSearch(GOOGLE_SEARCH_KEYWORD);
    Assert.assertEquals(getSearchResultPage().getSearchResultsList().size(), EXPECTED_AMOUNT_GOOGLE);
  }

  @Test
  @Description("Verify amount of goods on Rozetka")
  public void checkAmountsOfGoods() {
    getGooglePage().makeSearch(GOOGLE_SEARCH_KEYWORD);
    getSearchResultPage().getSearchResultsList().get(0).click();
    getRozetkaPage().makeSearch(ROZETKA_SEARCH_KEYWORD);
    Assert.assertEquals(getRozetkaSearchResultsPage().getSearchingGoodsList().size(),
        EXPECTED_AMOUNT_OF_GOODS);


  }
}
