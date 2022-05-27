package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.DashboardPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardTest extends TestRunner {

    private DashboardPage dashboardPage;

    @BeforeMethod
    public void openDashboardPage() {
        dashboardPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openDashboardPage();
    }

    @Test
    @Description("Test to verify Dashboard Us page opening")
    public void verifyDashboardPageOpening() {

        var expectedUrl = DASHBOARD_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }
}
