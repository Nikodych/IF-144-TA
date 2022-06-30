package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.TestsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.repos.TestRepo.getValidTest;
import static com.softserveinc.ita.util.ApiUtil.getTestsListByAPI;
import java.util.List;

import static com.softserveinc.ita.repos.TestRepo.getValidTest;
import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.API_LOGOUT_PATH;
import static com.softserveinc.ita.util.DataProvider.TEST_SUBJECT;
import static org.assertj.core.api.Assertions.assertThat;

public class TestsTest extends TestRunner {

    private TestsPage testsPage;
    private Cookie sessionId;

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpTestsTests() {
        sessionId = getSessionsCookie(authAsAdmin());
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSubjectsTestsPage() {
        testsPage = testsSteps.openTestsPage(TEST_SUBJECT);
    }

    @Test(groups = "positive")
    @Description("Test to verify certain Subject`s Tests page opening")
    public void verifySubjectsTestsPageOpening() {

        var isTestsPageOfExpectedSubject = testsPage.isExpectedSubjectTestsPage(TEST_SUBJECT);

        assertThat(isTestsPageOfExpectedSubject)
                .as("Should be tests page of subject: " + TEST_SUBJECT)
                .isTrue();
    }

    @Test(groups = "positive")
    @Description("Test to verify adding new test functionality")
    public void verifyAddingNewTest() {

        var test = getValidTest();

        var isExpectedTestNameFound = testsSteps
                .addNewTest(test)
                .isExpectedNameOfTestFound(test.getName());

        var soft = getSoftAssert();

        soft.assertThat(isExpectedTestNameFound)
                .as("Test should be added and displayed with expected name")
                .isTrue();

        var tests = getTestsListByAPI(sessionId);

        soft.assertThat(tests)
                .as("After adding new test should be present in the list of tests returned by API call")
                .contains(test);

        soft.assertAll();

        steps.deleteTest(test);
    }

    @Test(groups = "positive")
    @Description("Test to verify deleting test functionality")
    public void verifyDeletingTest() {

        var test = getValidTest();

        var isAdded = steps
                .addNewTest(test)
                .isExpectedNameOfTestFound(test.getName());

        var soft = getSoftAssert();

        soft.assertThat(isAdded)
                .as("Test name should be present in the list of tests")
                .isTrue();

        var tests = getTestsListByAPI(sessionId);

        soft.assertThat(tests)
                .as("After adding test should be present in the list of tests returned by API call")
                .contains(test);

        var isDeleted = !(steps
                .deleteTest(test)
                .refreshPage()
                .isExpectedNameOfTestFound(test.getName()));

        soft.assertThat(isDeleted)
                .as("Test name should be deleted from the list of tests")
                .isTrue();

        var testsAfterDeleting = getTestsListByAPI(sessionId);

        soft.assertThat(testsAfterDeleting)
                .as("After deleting test should be absent in the list of tests returned by API call")
                .doesNotContain(test);

        soft.assertAll();
    }

    @AfterClass
    public void tearDown() {
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }
}