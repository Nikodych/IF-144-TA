package com.softserveinc.ita.nkupchenko;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import Utils.TestRunner;
import com.softserveinc.ita.pageobjects.GooglePage;
import com.softserveinc.ita.pageobjects.RozetkaPage;
import com.softserveinc.ita.pageobjects.RozetkaSearchResultsPage;
import com.softserveinc.ita.pageobjects.SearchResultsPage;
import org.testng.annotations.Test;

public class RozetkaTest extends TestRunner {

  private static final String GOOGLE_SEARCH_KEYWORD = "rozetka";
  private static final String ROZETKA_SEARCH_KEYWORD = "iphone 12";
  private static final String EXPECTED_KEYWORD = "iphone";
  private static final int EXPECTED_AMOUNT_OF_GOODS = 3;

  @Test
  public void checkAmountsOfGoods() {
    new GooglePage().makeSearch(GOOGLE_SEARCH_KEYWORD);
    new SearchResultsPage().followFirstLink();
    new RozetkaPage().search(ROZETKA_SEARCH_KEYWORD);
    assertThat(new RozetkaSearchResultsPage().goodsCount())
        .as("should be 3 goods")
        .isEqualTo(EXPECTED_AMOUNT_OF_GOODS);
  }
}