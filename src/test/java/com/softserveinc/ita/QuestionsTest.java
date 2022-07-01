package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.QuestionsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.TEST_SUBJECT;
import static com.softserveinc.ita.util.DataProvider.TEST_TEST;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionsTest extends TestRunner {

    private QuestionsPage questionsPage;
    private Cookie sessionId;

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpQuestionsTests() {
        sessionId = getSessionsCookie(authAsAdmin());
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSubjectTestQuestionsPage() {
        questionsSteps.openQuestionsPage(TEST_SUBJECT,TEST_TEST);
        questionsPage = questionsSteps.getPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify certain Test`s Questions page opening")
    public void verifyTestQuestionsPageOpening() {

        var isQuestionsPageOfExpectedTest = questionsPage.isExpectedTestQuestionsPage(TEST_TEST);

        assertThat(isQuestionsPageOfExpectedTest)
                .as("Should be questions page of test: " + TEST_TEST)
                .isTrue();
    }
}