package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.GoogleSearchPage;
import com.softserveinc.ita.utils.ReadConfigFileValues;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyGoogleSearchResults extends TestRunner {

    @Test
    @Description("Verify that search produces search result which contain expected mention of certain link")
    public void googleSearchTest() {

        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.search(ReadConfigFileValues.SEARCH_REQUEST)
                .checkInSearchResult(ReadConfigFileValues.MENTION)
                .click();

        Assert.assertTrue(driver
                .getCurrentUrl()
                .contains(ReadConfigFileValues.MENTION), "Should be moyo.ua website");
    }
}
