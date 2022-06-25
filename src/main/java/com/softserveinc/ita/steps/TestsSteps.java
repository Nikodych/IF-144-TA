package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.TestEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.TestsPage;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;

import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

public class TestsSteps {
    private TestsPage page;

    public TestsPage openTestsPage(String subject) {
        return page = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage()
                .openSubjectTests(subject);
    }

    public TestsPage addNewTest(TestEntity test){
        return page
                .openAddingTestForm()
                .setTestName(test.getName())
                .setTestSubject(test.getSubject())
                .setNumberOfTasks(test.getNumberOfTasks())
                .setAmountOfTime(test.getAmountOfTime())
                .setNumberOfAttempts(test.getNumberOfAttempts())
                .setStateAsTurnedOn()
                .addSubjectTest()
                .waitTillProgressBarDisappears();
    }

    public TestsPage deleteTest(TestEntity test) {
        var searchValue = test.getName();
        var table = page.getTable();

        table.findTablePageWithSearchValue(searchValue);
        table.deleteRowByValue(searchValue);

        new DeletingFormModal().confirmModal();
        page.waitTillProgressBarDisappears();

        return page;
    }
}