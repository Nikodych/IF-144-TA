package com.softserveinc.ita.petrus;

import com.softserveinc.ita.petrus.page_objects.GoogleHomePage;
import com.softserveinc.ita.util.TestRunner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleResultsPageTest extends TestRunner {
    public static final String SEARCH_TITLES = "rozetka";
    public static final String EXPECTED_CONTENT = "rozetka";

    @Test
    @Description("Check if every title contains expected text")
    void checkGoogleResults() {
        var googleHomePage = new GoogleHomePage();
        var listOfTitles = googleHomePage.search(SEARCH_TITLES)
                .getTitles();

        Assert.assertTrue(listOfTitles.stream().allMatch(t -> t.toLowerCase().contains(EXPECTED_CONTENT)));
    }
}
