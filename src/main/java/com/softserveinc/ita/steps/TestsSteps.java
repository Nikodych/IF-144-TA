package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.TestEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.TestsPage;

import static com.softserveinc.ita.util.DataProvider.*;

public class TestsSteps {

    public TestsPage openTestsPage(String subject) {
        return new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage()
                .openSubjectTests(subject);
    }

    public TestsPage addNewTest(TestEntity test){
        return new TestsPage()
                .openAddingTestForm()
                .setTestName(test.getName())
                .setTestSubject(test.getSubject())
                .setNumberOfTasks(test.getNumberOfTasks())
                .setAmountOfTime(test.getAmountOfTime())
                .setNumberOfAttempts(test.getNumberOfAttempts())
                .setStateAsTurnedOn()
                .addSubjectTest()
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }
}