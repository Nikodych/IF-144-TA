package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.MoyoHomePage;
import com.softserveinc.ita.utils.ReadConfigFileValues;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class VerifyMoyoSearchResults extends TestRunner {

    @Test
    @Description("Verify that incorrect search request produces no search results")
    public void moyoSearchTestWithNoResults() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var isResultsPresent = moyo
                .searchOnMoyo()
                .search("dadawawd")
                .verifySearchResultsArePresent();

        Assert.assertFalse(isResultsPresent, "Search results should be absent");
    }

    @Test
    @Description("Verify that correct search request produces search results")
    public void moyoSearchTestWithResults() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var isResultsPresent = moyo
                .searchOnMoyo()
                .search("відеокарта")
                .verifySearchResultsArePresent();

        Assert.assertTrue(isResultsPresent, "Search results should be present");
    }

    @Test
    @Description("Verify that search results contains expected mention in them")
    public void moyoSearchResultsContentTest() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("відеокарта msi")
                .collectElementsByXPath()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(actualResults
                        .stream()
                        .anyMatch(result -> result
                                .toLowerCase()
                                .contains(ReadConfigFileValues.MOYO_EXPECTED_RESULT)),
                "Each search result from the first page should contain " + ReadConfigFileValues.MOYO_EXPECTED_RESULT);
    }

    @Test
    @Description("Verify expected amount of search results")
    public void moyoSearchResultsCountTest() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("відеокарта msi")
                .collectElementsByXPath()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(actualResults.size(), (ReadConfigFileValues.MOYO_EXPECTED_RESULTS_COUNT),
                "Should be " + ReadConfigFileValues.MOYO_EXPECTED_RESULTS_COUNT + " of search results from the first page");
    }

    @Test
    @Description("Verify that search results contains expected mention in them on all pages with search results")
    public void moyoSearchResultsContentTestWithShowMoreButton() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("відеокарта msi")
                .goToShowMoreButton()
                .showAllSearchResults()
                .collectElementsByXPath()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(actualResults
                        .stream()
                        .anyMatch(result -> result
                                .toLowerCase()
                                .contains(ReadConfigFileValues.MOYO_EXPECTED_RESULT)),
                "Each search result from all pages should contain " + ReadConfigFileValues.MOYO_EXPECTED_RESULT);
    }

    @Test
    @Description("Verify expected amount of search results from all search results pages")
    public void moyoSearchResultsCountTestWithShowMoreButton() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        var actualResults = moyo
                .searchOnMoyo()
                .search("відеокарта msi")
                .goToShowMoreButton()
                .showAllSearchResults()
                .collectElementsByXPath()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(actualResults.size(), (ReadConfigFileValues.MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT),
                "Should be " + ReadConfigFileValues.MOYO_EXPECTED_RESULTS_SHOW_ALL_COUNT + " of search results from all pages");
    }
}
