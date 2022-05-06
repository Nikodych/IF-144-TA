package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.MoyoHomePage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.softserveinc.ita.utils.ReadConfigFileValues.*;
import static org.testng.Assert.*;

public class MoyoSearchTests extends TestRunner {

    @Test
    @Description("Verify that incorrect search request produces no search results")
    public void verifyMoyoSearchWithNoResults() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var isResultsPresent = moyo
                .searchOnMoyo()
                .search("dadawawd")
                .verifySearchResultsArePresent();

        assertFalse(isResultsPresent, "Search results should be absent");
    }

    @Test
    @Description("Verify that correct search request produces search results")
    public void verifyMoyoSearchWithResults() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var isResultsPresent = moyo
                .searchOnMoyo()
                .search("msi")
                .verifySearchResultsArePresent();

        assertTrue(isResultsPresent, "Search results should be present");
    }

    @Test
    @Description("Verify that search results contains expected mention in them")
    public void verifyMoyoSearchResultsContent() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("msi")
                .collectTitlesFromSearchResults();

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
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("msi")
                .collectTitlesFromSearchResults();

        assertEquals(actualResults.size(), (MOYO_EXPECTED_RESULTS_COUNT),
                "Should be " + MOYO_EXPECTED_RESULTS_COUNT + " of search results from the first page");
    }

    @Test
    @Description("Verify that search results contains expected mention in them on all pages with search results")
    public void verifyMoyoSearchResultsContentWithShowMoreButton() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("msi")
                .goToShowMoreButton()
                .showAllSearchResults()
                .collectTitlesFromSearchResults();

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
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("msi")
                .goToShowMoreButton()
                .showAllSearchResults()
                .collectTitlesFromSearchResults();

        assertEquals(actualResults.size(), (MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT),
                "Should be " + MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT + " of search results from all pages");
    }
}
