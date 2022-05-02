package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.GoogleSearchPage;
import com.softserveinc.ita.utils.ReadConfigFileValues;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyGoogleSearchResults extends TestRunner {

    @Test
    public void googleSearchTest(){

        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.search(ReadConfigFileValues.SEARCH_REQUEST)
                .checkInSearchResult(ReadConfigFileValues.MENTION)
                .click();

        Assert.assertTrue(driver.getCurrentUrl().contains(ReadConfigFileValues.MENTION), "Should be moyo.ua website");
    }
}
