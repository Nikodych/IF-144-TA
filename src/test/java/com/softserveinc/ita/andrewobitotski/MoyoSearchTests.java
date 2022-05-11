package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.MoyoHomePage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.utils.ReadDataFileValues.*;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.*;

public class MoyoSearchTests extends TestRunner {

    @Test
    @Description("Verify that incorrect search request produces no search results")
    public void verifyMoyoSearchWithNoResults() {
        var isResultPresent = isSearchResultPresent("dadawawd");

        assertThat(isResultPresent)
                .as("Search results should be absent")
                .isFalse();
    }

    @Test
    @Description("Verify that correct search request produces search results")
    public void verifyMoyoSearchWithResults() {
        var isResultPresent = isSearchResultPresent("msi");

        assertThat(isResultPresent)
                .as("Search results should be present")
                .isTrue();
    }

    public boolean isSearchResultPresent(String searchRequest) {
        MoyoHomePage moyo = new MoyoHomePage();

        return moyo
                .searchOnMoyo()
                .performSearch(searchRequest)
                .verifySearchResultsArePresent();
    }

    @Test
    @Description("Verify that search results contains expected mention in them")
    public void verifyMoyoSearchResultsContent() {
        var actualResults = collectSearchResultsTitlesFromFirstPage("msi");

        assertThat(actualResults
                .stream()
                .anyMatch(result -> result
                        .toLowerCase()
                        .contains(MOYO_EXPECTED_RESULT)))
                .as("Each search result from the first page should contain " + MOYO_EXPECTED_RESULT)
                .isTrue();
    }

    @Test
    @Description("Verify expected amount of search results")
    public void verifyMoyoSearchResultsCount() {
        var actualResults = collectSearchResultsTitlesFromFirstPage("msi");

        assertThat(actualResults.size())
                .as("Should be " + MOYO_EXPECTED_RESULTS_COUNT + " of search results from the first page")
                .isEqualTo(MOYO_EXPECTED_RESULTS_COUNT);
    }

    public List<String> collectSearchResultsTitlesFromFirstPage(String searchRequest) {
        MoyoHomePage moyo = new MoyoHomePage();

        return moyo
                .searchOnMoyo()
                .performSearch(searchRequest)
                .collectTitlesFromSearchResults();
    }

    @Test
    @Description("Verify that search results contains expected mention in them on all pages with search results")
    public void verifyMoyoSearchResultsContentWithShowMoreButton() {
        var actualResults = collectSearchResultsTitlesFromAllPages("msi");

        assertThat(actualResults
                .stream()
                .anyMatch(result -> result
                        .toLowerCase()
                        .contains(MOYO_EXPECTED_RESULT)))
                .as("Each search result from all pages should contain " + MOYO_EXPECTED_RESULT)
                .isTrue();
    }

    @Test
    @Description("Verify expected amount of search results from all search results pages")
    public void verifyMoyoSearchResultsCountWithShowMoreButton() {
        var actualResults = collectSearchResultsTitlesFromAllPages("msi");

        assertThat(actualResults.size())
                .as("Should be " + MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT + " of search results from the first page")
                .isEqualTo(MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT);
    }

    public List<String> collectSearchResultsTitlesFromAllPages(String searchRequest) {
        MoyoHomePage moyo = new MoyoHomePage();

        return moyo
                .searchOnMoyo()
                .performSearch(searchRequest)
                .goToShowMoreButton()
                .showAllSearchResults()
                .collectTitlesFromSearchResults();
    }
}


