package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.MoyoHomePage;
import com.softserveinc.ita.utils.ReadConfigFileValues;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyMoyoSearchResults extends TestRunner {

    @Test
    public void moyoSearchTestWithNoResults() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        boolean isResultsPresent = moyo.searchOnMoyo()
                .search("dadawawd")
                .verifySearchResultsArePresent();

        Assert.assertFalse(isResultsPresent, "Search results should be absent");
    }

    @Test
    public void moyoSearchTestWithResults() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        boolean isResultsPresent = moyo.searchOnMoyo()
                .search("відеокарта")
                .verifySearchResultsArePresent();

        Assert.assertTrue(isResultsPresent, "Search results should be present");
    }

    @Test
    public void moyoSearchResultsContentTest() {
        MoyoHomePage moyo = new MoyoHomePage(driver);
        String actualResult = moyo.searchOnMoyo()
                .search("відеокарта msi")
                .collectElementsByXPath()
                .listIterator()
                .next()
                .getText();

        Assert.assertTrue(actualResult.toLowerCase().contains(ReadConfigFileValues.MOYO_EXPECTED_RESULT), "Search results should contain expected mention");
    }
}
