package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.QuestionEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.QuestionsPage;
import lombok.Getter;

import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

@Getter
public class QuestionsSteps {

    private QuestionsPage page = new QuestionsPage();

    public void openQuestionsPage(String subjectName, String testName) {
        new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage()
                .openSubjectTests(subjectName)
                .openQuestionsPage(testName);
    }

    public void addNewQuestion(QuestionEntity question) {
        page
                .openAddingNewQuestionPage()
                .fillTextOfQuestion(question.getTextOfQuestion())
                .setTypeOfQuestion(question.getTypeOfQuestion())
                .setLevelOfQuestion(question.getLevelOfQuestion())
                .addAnswerForQuestion(question.getAnswer())
                .createQuestion()
                .waitTillProgressBarDisappears();
    }

    public void deleteQuestion(QuestionEntity question) {
        page.deleteQuestion(question);
    }
}