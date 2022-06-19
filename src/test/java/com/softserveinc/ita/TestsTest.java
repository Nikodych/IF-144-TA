package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.TestsPage;
import com.softserveinc.ita.steps.TestsSteps;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.softserveinc.ita.repos.TestRepo.*;
import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.*;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class TestsTest extends TestRunner {

    private TestsPage testsPage;
    private TestsSteps steps = new TestsSteps();
    private Cookie sessionId;
    private SoftAssertions soft;

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpTestsTests() {
        sessionId = getSessionsCookie(authAsAdmin());
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSubjectsTestsPage() {
        testsPage = steps.openTestsPage(TEST_SUBJECT);
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

        var isExpectedTestNameFound = steps
                .addNewTest(test)
                .isExpectedNameOfTestFound(test.getName());

        soft = getSoftAssert();

        soft.assertThat(isExpectedTestNameFound)
                .as("Test should be added and displayed with expected name")
                .isTrue();

        var tests = getTestsListByAPI();

        soft.assertThat(tests)
                .as("After adding new test it should be present in the list of tests returned by API call")
                .contains(test.getName());

        soft.assertAll();
    }

    @AfterClass
    public void tearDown() {
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }

    private List<Object> getTestsListByAPI() {
        var path = format(API_ENTITY_GET_RECORDS_PATH, "test");
        var response = performGetRequest(sessionId, path);

        return response
                .getBody()
                .jsonPath()
                .getList("test_name");
    }
}