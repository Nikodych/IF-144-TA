package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.QuestionsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.repos.QuestionRepo.getValidQuestion;
import static com.softserveinc.ita.util.DataProvider.TEST_SUBJECT;
import static com.softserveinc.ita.util.DataProvider.TEST_TEST;
import static org.assertj.core.api.Assertions.assertThat;

public class QuestionsTest extends TestRunner {

    private QuestionsPage questionsPage;

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSubjectTestQuestionsPage() {
        questionsSteps.openQuestionsPage(TEST_SUBJECT, TEST_TEST);
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

    @Test(groups = "positive")
    @Description("Test to verify adding new question functionality")
    public void verifyAddingNewQuestion() {

        var question = getValidQuestion();

        questionsSteps.addNewQuestion(question);

        var isExpectedTextOfQuestionFound = questionsPage.isExpectedTextOfQuestionFound(question.getTextOfQuestion());

        assertThat(isExpectedTextOfQuestionFound)
                .as("Question should be added and displayed with expected text of it")
                .isTrue();

        questionsSteps.deleteQuestion(question);
    }

    @Test(groups = "positive")
    @Description("Test to verify deleting question functionality")
    public void verifyDeletingQuestion() {

        var question = getValidQuestion();

        questionsSteps.addNewQuestion(question);

        var isAdded = questionsPage.isExpectedTextOfQuestionFound(question.getTextOfQuestion());

        var soft = getSoftAssert();

        assertThat(isAdded)
                .as("Question should be added and displayed with expected text of it")
                .isTrue();

        questionsSteps.deleteQuestion(question);

        refresh();

        var isDeleted = !(questionsPage.isExpectedTextOfQuestionFound(question.getTextOfQuestion()));

        soft.assertThat(isDeleted)
                .as("Question should be deleted and its text should be removed from list")
                .isTrue();

        soft.assertAll();
    }
}