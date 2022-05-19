package com.softserveinc.ita.petrus;

import com.softserveinc.ita.petrus.page_objects.GoogleHomePage;
import com.softserveinc.ita.util.TestRunner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleResultsPageTest extends TestRunner {
    public static final String SEARCH_TITLES = "rozetka";
    public static final String EXPECTED_CONTENT_EN = "rozetka";
    public static final String EXPECTED_CONTENT_UA = "розетка";

    @Test
    @Description("Verify if every title contains expected text")
    public void checkGoogleResults() {

        var listOfTitles = new GoogleHomePage()
                .search(SEARCH_TITLES)
                .getTitles();

        Assert.assertTrue(listOfTitles.stream().allMatch(title -> title.contains(EXPECTED_CONTENT_EN)
                || title.contains(EXPECTED_CONTENT_UA)));
    }
}
