package deineka;

import Utils.ConfigurationHelper;
import Utils.TestRunner;
import com.softserveinc.ita.pageobjects.FollowLinkPage;
import com.softserveinc.ita.pageobjects.GoogleSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest extends TestRunner {

    private final String SEARCH_TEXT = "rozetka";
    private final String SEARCH_LOT = "kugoo s3 pro black";

    private final GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
    private final FollowLinkPage followLinkPage = new FollowLinkPage(driver);

    @Test
    public void verifyGoogleSearchResults() {
        driver.get(ConfigurationHelper.getProperty("page.url"));
        Assert.assertTrue(googleSearchPage.isPageOpened(), "The Google Search Page did not open");

        googleSearchPage.inputText(SEARCH_TEXT);
        googleSearchPage.clickSearchButton();
        Assert.assertTrue(googleSearchPage.getTextOfResults().stream().anyMatch(t -> t.contains(SEARCH_TEXT)), "The Results did not contain Search text");

        googleSearchPage.clickFollowLink();
        Assert.assertTrue(followLinkPage.isPageOpened(), "The Follow Page did not open");

        followLinkPage.inputText(SEARCH_LOT);
        followLinkPage.clickSearchButton();
        Assert.assertEquals(followLinkPage.getLotsCount(), followLinkPage.getResult(), "Number of Lots in the list was not equal to number of Results");
    }
}
