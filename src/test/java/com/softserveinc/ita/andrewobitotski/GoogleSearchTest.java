package com.softserveinc.ita.andrewobitotski;

import com.softserveinc.ita.pageobjects.andrewobitotski.GoogleSearchPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.softserveinc.ita.utils.ReadDataFileValues.MENTION;
import static com.softserveinc.ita.utils.ReadDataFileValues.SEARCH_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchTest extends TestRunner {

    @Test
    @Description("Verify that search produces search result which contain expected mention of certain link")
    public void verifyGoogleSearch() {

        GoogleSearchPage googleSearchPage = new GoogleSearchPage();
        googleSearchPage
                .performSearch(SEARCH_REQUEST)
                .checkInSearchResult(MENTION);

        assertThat(getWebDriver()
                .getCurrentUrl())
                .as("Should be moyo.ua website")
                .contains((MENTION));
    }
}
