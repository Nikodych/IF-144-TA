package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.*;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class DashboardTest extends TestRunner {

    private DashboardPage dashboardPage;

    @BeforeMethod(groups = {"positive", "negative"})
    public void openDashboardPage() {
        dashboardPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openDashboardPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify Dashboard Us page opening")
    public void verifyDashboardPageOpening() {

        var expectedUrl = DASHBOARD_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test(groups = "positive")
    @Description("Test to verify Dashboard cards buttons work")
    public void verifyDashboardButtonsWork() {

        var soft = getSoftAssert();

        verifyDashboardButton(FacultiesPage.class, FACULTIES_PAGE_URL, soft);

        verifyDashboardButton(GroupsPage.class, GROUPS_PAGE_URL, soft);

        verifyDashboardButton(SpecialitiesPage.class, SPECIALITIES_PAGE_URL, soft);

        verifyDashboardButton(SubjectsPage.class,SUBJECTS_PAGE_URL, soft);

        verifyDashboardButton(StudentsPage.class, GROUPS_PAGE_URL, soft); //button on the students card on the dashboard page redirect us to groups page

        verifyDashboardButton(AdminsPage.class, ADMINS_PAGE_URL, soft);

        verifyDashboardButton(ResultsPage.class, RESULTS_PAGE_URL, soft);

        verifyDashboardButton(ProtocolPage.class, PROTOCOL_PAGE_URL, soft);

        soft.assertAll();
    }

    private <T> void verifyDashboardButton(Class<T> classType, String expectedUrl, SoftAssertions soft) {
        var method = getMethod(DashboardPage.class,"goTo" + classType.getSimpleName());

        dashboardPage.openDashboardPage();
        runMethod(dashboardPage, method);

        var currentUrl = getCurrentUrl();

        soft.assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    private <T> Method getMethod(Class<T> classType, String nameOfMethod) {
        try {
            return classType.getMethod(nameOfMethod);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void runMethod(Object object, Method method) {
        try {
            method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}