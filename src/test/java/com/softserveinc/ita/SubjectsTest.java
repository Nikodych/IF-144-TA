package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

public class SubjectsTest extends TestRunner {

    private SubjectsPage subjectsPage;

    @BeforeMethod
    public void openSubjectsPage() {
        subjectsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage();
    }

    @Test
    public void verifyAddSubjectButtonIsEnabledWithValidData() {
        var isEnabled = subjectsPage
                .openAddingSubjectForm()
                .writeSubjectTitle()
                .writeSubjectDescription()
                .isAddButtonEnabled();

        assertThat(isEnabled)
                .as("When both fields have valid data add button should be enabled")
                .isTrue();
    }
}
