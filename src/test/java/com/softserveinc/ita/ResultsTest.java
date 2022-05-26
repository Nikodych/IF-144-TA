package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.ResultsPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class ResultsTest extends TestRunner {

    private ResultsPage resultsPage;

    @BeforeMethod
    public void openResultsPage() {
        resultsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openResultsPage();
    }

    @Test
    public void verifyResultsPageOpening() {

        var expectedUrl = RESULTS_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }
}
