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

  @Test
  public void checkTitlesOfGoods() throws InterruptedException {
    new GooglePage().makeSearch(GOOGLE_SEARCH_KEYWORD);
    new SearchResultsPage().followFirstLink();
    new RozetkaPage().search(ROZETKA_SEARCH_KEYWORD);

    assertThat(new RozetkaSearchResultsPage().getTitles().contains(EXPECTED_KEYWORD));

  }
}