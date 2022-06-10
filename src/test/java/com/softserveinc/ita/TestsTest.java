package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.TestsPage;
import com.softserveinc.ita.steps.TestsSteps;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.repos.TestRepo.*;
import static com.softserveinc.ita.util.DataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TestsTest extends TestRunner {

    private TestsPage testsPage;
    private TestsSteps steps = new TestsSteps();

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSubjectsTestsPage() {
        testsPage = steps.openTestsPage(TEST_SUBJECT);
    }

    @Test(groups = "positive")
    @Description("Test to verify Subject`s Tests page opening")
    public void verifySubjectsTestsPageOpening() {

        var isExpectedSubject = testsPage.isExpectedSubjectTestsPage(TEST_SUBJECT);

        assertThat(isExpectedSubject)
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

        assertThat(isExpectedTestNameFound)
                .as("Test should be added and displayed with expected name")
                .isTrue();
    }
}
