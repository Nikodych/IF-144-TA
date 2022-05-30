package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.AddingSubjectModal;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SubjectsTest extends TestRunner {

    private SubjectsPage subjectsPage;

    @BeforeMethod (groups = {"positive", "negative"})
    public void openSubjectsPage() {
        subjectsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage();
    }

    @Test (groups = "positive")
    @Description("Test to verify Subjects page opening")
    public void verifySubjectsPageOpening() {

        var expectedUrl = SUBJECTS_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test (groups = "positive")
    @Description("Test to verify \" Add subject\" button should work with valid data")
    public void verifyAddSubjectButtonIsEnabledWithValidData() {
        openAndFillSubjectFields("Предметний", "Опис предмета");

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When both fields have valid data add button should be enabled")
                .isTrue();
    }

    @Test (groups = "negative")
    @Description("Test to verify that new subject should not be able to be created with invalid title")
    public void verifyNewSubjectCanNotBeCreatedWithInvalidTitle() {
        openAndFillSubjectFields("5предметний предмет", "Валідний опис предмета");

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When title field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test (groups = "negative")
    @Description("Test to verify that new subject should not be able to be created with invalid description")
    public void verifyNewSubjectCanNotBeCreatedWithInvalidDescription() {
        openAndFillSubjectFields("Предметний предмет", "невалідний опис предмета");

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When description field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test (groups = "positive")
    @Description("Test to verify that new subject should be able to be created with valid data")
    public void verifyAddingNewSubject() {
        var subjectName = "Новий предмет";
        var subjectDescription = "Його опис";

        openAndFillSubjectFields(subjectName, subjectDescription);

        var isAddedAtTheEnd = new AddingSubjectModal()
                .addNewSubject()
                .switchToLastPageOfTable()
                .getNamesOfSubjects()
                .contains(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of table")
                .isTrue();

        var isFound = subjectsPage
                .switchToFirstPageOfTable()
                .setSearchValue(subjectName)
                .getNamesOfSubjects()
                .contains(subjectName);

        assertThat(isFound)
                .as("New subject should be displayed after search is performed")
                .isTrue();
    }

    private void openAndFillSubjectFields(String title, String description) {
        subjectsPage
                .openAddingSubjectForm()
                .setSubjectTitle(title)
                .setSubjectDescription(description);
    }
}