package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.AddingSubjectModal;
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
        openAndFillSubjectFields("Предметний", "Опис предмета");

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When both fields have valid data add button should be enabled")
                .isTrue();
    }

    @Test
    public void verifyNewSubjectCanNotBeCreatedWithInvalidTitle() {
        openAndFillSubjectFields("5предметний предмет", "Валідний опис предмета");

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When title field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test
    public void verifyNewSubjectCanNotBeCreatedWithInvalidDescription() {
        openAndFillSubjectFields("Предметний предмет", "невалідний опис предмета");

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When description field has invalid data new subject can't be created")
                .isFalse();
    }

    private void openAndFillSubjectFields(String title, String description) {
        subjectsPage
                .openAddingSubjectForm()
                .setSubjectTitle(title)
                .setSubjectDescription(description);
    }
}