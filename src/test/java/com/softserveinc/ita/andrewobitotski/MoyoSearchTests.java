package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.andrewobitotski.utils.TestRunner;
import com.softserveinc.ita.andrewobitotski.pageobjects.MoyoHomePage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.softserveinc.ita.andrewobitotski.utils.ReadDataFileValues.*;
import static org.testng.Assert.*;

public class MoyoSearchTests extends TestRunner {

    @Test
    @Description("Verify that incorrect search request produces no search results")
    public void verifyMoyoSearchWithNoResults() {
        var isResultsPresent = new MoyoHomePage()
                .searchOnMoyo()
                .search("dadawawd")
                .areSearchResultsPresent();

        assertFalse(isResultsPresent, "Search results should be absent");
    }

    @Test
    @Description("Verify that correct search request produces search results")
    public void verifyMoyoSearchWithResults() {
        var isResultsPresent = new MoyoHomePage()
                .searchOnMoyo()
                .search("msi")
                .areSearchResultsPresent();

        assertTrue(isResultsPresent, "Search results should be present");
    }

    @Test
    @Description("Verify that search results contains expected mention in them")
    public void verifyMoyoSearchResultsContent() {
        var actualResults = new MoyoHomePage()
                .searchOnMoyo()
                .search("msi")
                .getSearchResultsTitles();

        assertTrue(actualResults
                        .stream()
                        .anyMatch(result -> result
                                .toLowerCase()
                                .contains(MOYO_EXPECTED_RESULT)),
                "Each search result from the first page should contain " + MOYO_EXPECTED_RESULT);
    }

    @Test
    @Description("Verify expected amount of search results")
    public void verifyMoyoSearchResultsCount() {
        var actualResults = new MoyoHomePage()
                .searchOnMoyo()
                .search("msi")
                .getSearchResultsTitles();

        assertEquals(actualResults.size(), (MOYO_EXPECTED_RESULTS_COUNT),
                "Should be " + MOYO_EXPECTED_RESULTS_COUNT + " of search results from the first page");
    }

    @Test
    @Description("Verify that search results contains expected mention in them on all pages with search results")
    public void verifyMoyoSearchResultsContentWithShowMoreButton() {
        var actualResults = new MoyoHomePage()
                .searchOnMoyo()
                .search("msi")
                .goToShowMoreButton()
                .showAllSearchResults()
                .getSearchResultsTitles();

        assertTrue(actualResults
                        .stream()
                        .anyMatch(result -> result
                                .toLowerCase()
                                .contains(MOYO_EXPECTED_RESULT)),
                "Each search result from all pages should contain " + MOYO_EXPECTED_RESULT);
    }

    @Test
    @Description("Verify expected amount of search results from all search results pages")
    public void verifyMoyoSearchResultsCountWithShowMoreButton() {
        var actualResults = new MoyoHomePage()
                .searchOnMoyo()
                .search("msi")
                .goToShowMoreButton()
                .showAllSearchResults()
                .getSearchResultsTitles();

        assertEquals(actualResults.size(), (MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT),
                "Should be " + MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT + " of search results from all pages");
    }
}
