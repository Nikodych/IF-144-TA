package com.softserveinc.ita.petrus;

import com.softserveinc.ita.petrus.page_objects.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends TestRunner {
    public static final String EXPECTED_CONTENT = "rozetka";
    public static final String SEARCH_TITLES = "rozetka";

    @Test
    void eachTitleContains() {
        GoogleHomePage homePage = new GoogleHomePage(driver);
        homePage.search(SEARCH_TITLES);
        var titles = homePage.titlesToString();
        Assert.assertTrue(titles.stream().allMatch(t -> t.contains(EXPECTED_CONTENT)));
    }

}
