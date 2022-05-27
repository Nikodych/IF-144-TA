package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.pageobjects.GoogleSearchPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.andrewobitotski.utils.ReadDataFileValues.MENTION;
import static com.softserveinc.ita.pageobjects.andrewobitotski.utils.ReadDataFileValues.SEARCH_REQUEST;
import static org.testng.Assert.assertTrue;

public class GoogleSearchTest extends TestRunner {

    @Test
    @Description("Verify that search produces search result which contain expected mention of certain link")
    public void verifyGoogleSearch() {
        new GoogleSearchPage()
                .performSearch(SEARCH_REQUEST)
                .checkInSearchResult(MENTION);

        var currentUrl = driver.getCurrentUrl();

        assertTrue(currentUrl
                .contains(MENTION), "Should be moyo.ua website");
    }
}
