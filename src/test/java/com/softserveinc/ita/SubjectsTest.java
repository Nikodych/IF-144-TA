package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.AddingSubjectModal;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.steps.SubjectStep;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.repos.SubjectRepo.*;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SubjectsTest extends TestRunner {

    private SubjectsPage subjectsPage;
    private final SubjectStep step = new SubjectStep();

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSubjectsPage() {
        subjectsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSubjectsPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify Subjects page opening")
    public void verifySubjectsPageOpening() {

        var expectedUrl = SUBJECTS_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test(groups = "positive")
    @Description("Test to verify \" Add subject\" button should work with valid data")
    public void verifyAddSubjectButtonIsEnabledWithValidData() {
        step.openAndFillSubjectFields(getValidSubject());

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When both fields have valid data add button should be enabled")
                .isTrue();
    }

    @Test(groups = "negative")
    @Description("Test to verify that new subject should not be able to be created with invalid title")
    public void verifyNewSubjectCanNotBeCreatedWithInvalidTitle() {
        step.openAndFillSubjectFields(getSubjectWithInvalidName());

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When title field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test(groups = "negative")
    @Description("Test to verify that new subject should not be able to be created with invalid description")
    public void verifyNewSubjectCanNotBeCreatedWithInvalidDescription() {
        step.openAndFillSubjectFields(getSubjectWithInvalidDescription());

        assertThat(new AddingSubjectModal().isAddButtonEnabled())
                .as("When description field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test(groups = "positive")
    @Description("Test to verify that new subject should be able to be created with valid data")
    public void verifyAddingNewSubject() {
        var subjectName = getValidSubject().getName();

        step.openAndFillSubjectFields(getValidSubject());
        step.addAndWaitForSubjectToAppear();

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of table")
                .isTrue();

        var isFound = subjectsPage
                .switchToFirstPageOfTable()
                .setSearchValue(subjectName)
                .hasSubject(subjectName);

        assertThat(isFound)
                .as("New subject should be displayed after search is performed")
                .isTrue();

        step.deleteSubject(subjectName);
    }

    @Test(groups = "positive")
    @Description("Test to verify that deleted subject should not be displayed in the table")
    public void verifyDeletingSubject() {
        var subjectName = getValidSubject().getName();

        step.openAndFillSubjectFields(getValidSubject());
        step.addAndWaitForSubjectToAppear();

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of table")
                .isTrue();

        step.deleteSubject(subjectName);

        var hasDeletedSubject = subjectsPage
                .setSearchValue(subjectName)
                .hasSubject(subjectName);

        assertThat(hasDeletedSubject)
                .as("Deleted subject should not be displayed in the table")
                .isFalse();
    }

    @Test
    @Description("Test to verify that after editing subject changes should be displayed")
    public void verifyEditingSubject() {
        var subjectName = getValidSubject().getName();

        step.openAndFillSubjectFields(getValidSubject());
        step.addAndWaitForSubjectToAppear();

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of the table")
                .isTrue();

        var editSubstring = " редагований";

        step.editSubjectFields(subjectName, editSubstring);

        var editedSubjectName = subjectName + editSubstring;
        var isSubjectEdited = subjectsPage
                .setSearchValue(editedSubjectName)
                .hasSubject(editedSubjectName);

        assertThat(isSubjectEdited)
                .as("Edited subject should be displayed in the table")
                .isTrue();

        step.deleteSubject(editedSubjectName);

        var hasDeletedSubject = subjectsPage
                .setSearchValue(editedSubjectName)
                .hasSubject(editedSubjectName);

        assertThat(hasDeletedSubject)
                .as("Deleted subject should not be displayed in the table")
                .isFalse();
    }
}