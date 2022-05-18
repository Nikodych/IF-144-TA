package com.softserveinc.ita.alukavenko;

import com.softserveinc.ita.pageobjects.alukavenko.GoogleMainPage;
import com.softserveinc.ita.alukavenko.utils.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static java.lang.String.join;
import static org.testng.Assert.assertTrue;

public class GoogleSearchTest extends TestRunner {
    private static final String SEARCH_STRING = "rozetka";
    private static final String EXPECTED_STRING = "rozetka";

    @Test
    public void verifySearchResults() {
        List<String> searchTexts = new GoogleMainPage()
                .search(SEARCH_STRING)
                .getSearchResultTitles();

        boolean titlesMatch = searchTexts
                .stream()
                .allMatch(text -> text.toLowerCase().contains(EXPECTED_STRING));

        assertTrue(titlesMatch,
                "Search results should contain: " + EXPECTED_STRING
                        + ". Actual results: \n" + join("\n", searchTexts));
    }
}
