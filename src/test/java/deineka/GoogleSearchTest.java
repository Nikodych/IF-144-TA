package deineka;

import Utils.TestRunner;
import com.softserveinc.ita.pageobjects.GoogleSearchPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GoogleSearchTest extends TestRunner {

    private final String SEARCH_TEXT = "rozetka";
    private final String SEARCH_LOT = "kugoo s3 pro black";

    @Test
    public void verifyGoogleSearchResults() {
        var googleSearchPage = new GoogleSearchPage();

       googleSearchPage.search(SEARCH_TEXT);

        assertThat(googleSearchPage.getTextOfResults().stream().anyMatch(t -> t.contains(SEARCH_TEXT)))
                .as("The Results should contain Search text")
                .isTrue();

        var followLinkPage = googleSearchPage.clickFollowLink();

        followLinkPage.search(SEARCH_LOT);

        assertThat(followLinkPage.getLotsCount())
                .as("Number of Lots in the list should be equal to number of Results")
                .isEqualTo(followLinkPage.getResult());
    }
}
