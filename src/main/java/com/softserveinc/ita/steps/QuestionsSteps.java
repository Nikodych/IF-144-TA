package com.softserveinc.ita.steps;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.QuestionsPage;
import lombok.Getter;

import static com.softserveinc.ita.util.DataProvider.*;

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
}