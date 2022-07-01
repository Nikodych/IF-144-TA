package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.TestEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.TestsPage;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;
import lombok.Getter;

import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

public class TestsSteps {
    @Getter
    private TestsPage page = new TestsPage();

    public void openTestsPage(String subject) {
        new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage()
                .openSubjectTests(subject);
    }

    public void addNewTest(TestEntity test) {
        page
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

    public void deleteTest(TestEntity test) {
        var searchValue = test.getName();
        var table = page.getTable();

        table.findTablePageWithSearchValue(searchValue);
        table.deleteRowByValue(searchValue);

        new DeletingFormModal().confirmModal();
        page.waitTillProgressBarDisappears();
    }
}