package com.softserveinc.ita.alukavenko;

import com.softserveinc.ita.pageobjects.alukavenko.GoogleMainPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class GoogleSearchTest extends BaseTest {
    private static final String SEARCH_STRING = "rozetka";
    private static final String EXPECTED_STRING = "rozetka";

    @Test
    public void verifySearchResults() {

        List<String> searchTexts = new GoogleMainPage()
                .search(SEARCH_STRING)
                .getSearchResultsText();

        assertTrue(searchTexts.stream().allMatch(t-> t.toLowerCase().contains(EXPECTED_STRING)),
                "Search results should contain: " + EXPECTED_STRING
                        + ". Actual results: \n" + String.join("\n", searchTexts));

    }
}
