package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.DashboardPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardTest extends TestRunner {

    private DashboardPage dashboardPage;

    @BeforeMethod (groups = {"positive", "negative"})
    public void openDashboardPage() {
        dashboardPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openDashboardPage();
    }

    @Test (groups = "positive")
    @Description("Test to verify Dashboard Us page opening")
    public void verifyDashboardPageOpening() {

        var expectedUrl = DASHBOARD_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test (groups = "positive")
    @Description("Test to verify Dashboard cards buttons work")
    public void verifyDashboardButtonsWork() {

        dashboardPage.goToFacultiesPage();

        var expectedUrl = FACULTIES_PAGE_URL;
        var currentUrl = getCurrentUrl();

        var soft = getSoftAssert();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToGroupsPage();

        expectedUrl = GROUPS_PAGE_URL;
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToSpecialitiesPage();

        expectedUrl = SPECIALITIES_PAGE_URL;
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToSubjectsPage();

        expectedUrl = SUBJECTS_PAGE_URL;
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToStudentsPage();

        expectedUrl = GROUPS_PAGE_URL; //button on the students card on the dashboard page redirect us to groups page
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToAdminsPage();

        expectedUrl = ADMINS_PAGE_URL;
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToResultsPage();

        expectedUrl = RESULTS_PAGE_URL;
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        dashboardPage.openDashboardPage();
        dashboardPage.goToProtocolPage();

        expectedUrl = PROTOCOL_PAGE_URL;
        currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);

        soft.assertAll();
    }
}
