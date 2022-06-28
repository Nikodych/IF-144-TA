package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.admin.TimeTablePage;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.repos.SubjectRepo.*;
import static com.softserveinc.ita.repos.TimeTableRepo.getTimeTableForSubject;
import static com.softserveinc.ita.repos.TimeTableRepo.getUpdatedTimeTableForSubject;
import static com.softserveinc.ita.util.ApiUtil.*;
import static com.softserveinc.ita.util.AuthApiUtil.*;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SubjectsTest extends TestRunner {

    private SubjectsPage subjectsPage;
    private final AddingFormModal subjectAddingForm = new AddingFormModal();
    private final TimeTablePage timetablePage = new TimeTablePage();
    private Cookie sessionId;

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpSubjectsTest() {
        sessionId = getSessionsCookie(authAsAdmin());
    }

    @AfterClass(groups = {"positive", "negative"})
    public void tearDown() {
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }

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
        subjectStep.openAndFillSubjectFields(getValidSubject());

        assertThat(subjectAddingForm.isAddButtonEnabled())
                .as("When both fields have valid data add button should be enabled")
                .isTrue();
    }

    @Test(groups = "negative")
    @Description("Test to verify that new subject should not be able to be created with invalid title")
    public void verifyNewSubjectCanNotBeCreatedWithInvalidTitle() {
        subjectStep.openAndFillSubjectFields(getSubjectWithInvalidName());

        assertThat(subjectAddingForm.isAddButtonEnabled())
                .as("When title field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test(groups = "negative")
    @Description("Test to verify that new subject should not be able to be created with invalid description")
    public void verifyNewSubjectCanNotBeCreatedWithInvalidDescription() {
        subjectStep.openAndFillSubjectFields(getSubjectWithInvalidDescription());

        assertThat(subjectAddingForm.isAddButtonEnabled())
                .as("When description field has invalid data new subject can't be created")
                .isFalse();
    }

    @Test(groups = "positive")
    @Description("Test to verify that new subject should be able to be created with valid data")
    public void verifyAddingNewSubject() {
        var subjectName = getValidSubject().getName();

        subjectStep.openAndFillSubjectFields(getValidSubject());
        subjectStep.addAndWaitForSubjectToAppear();

        subjectsPage
                .getTable()
                .goToTablePage("last");

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of table")
                .isTrue();

        subjectsPage
                .getTable()
                .goToTablePage("first");

        var isFound = subjectsPage
                .setSearchValue(subjectName)
                .hasSubject(subjectName);

        assertThat(isFound)
                .as("New subject should be displayed after search is performed")
                .isTrue();

        subjectStep.deleteSubject(subjectName);
    }

    @Test(groups = "positive")
    @Description("Test to verify that deleted subject should not be displayed in the table")
    public void verifyDeletingSubject() {
        var subjectName = getValidSubject().getName();

        subjectStep.openAndFillSubjectFields(getValidSubject());
        subjectStep.addAndWaitForSubjectToAppear();

        subjectsPage
                .getTable()
                .goToTablePage("last");

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of table")
                .isTrue();

        subjectStep.deleteSubject(subjectName);

        var hasDeletedSubject = subjectsPage
                .setSearchValue(subjectName)
                .hasSubject(subjectName);

        assertThat(hasDeletedSubject)
                .as("Deleted subject should not be displayed in the table")
                .isFalse();
    }

    @Test(groups = "positive")
    @Description("Test to verify that after editing subject changes should be displayed")
    public void verifyEditingSubject() {
        var subjectName = getValidSubject().getName();

        subjectStep.openAndFillSubjectFields(getValidSubject());
        subjectStep.addAndWaitForSubjectToAppear();

        subjectsPage
                .getTable()
                .goToTablePage("last");

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of the table")
                .isTrue();

        var editSubstring = "Редагований";

        subjectStep.editSubjectFields(subjectName, editSubstring);

        var isSubjectEdited = subjectsPage
                .setSearchValue(editSubstring)
                .hasSubject(editSubstring);

        assertThat(isSubjectEdited)
                .as("Edited subject should be displayed in the table")
                .isTrue();

        subjectStep.deleteSubject(editSubstring);

        var hasDeletedSubject = subjectsPage
                .setSearchValue(editSubstring)
                .hasSubject(editSubstring);

        assertThat(hasDeletedSubject)
                .as("Deleted subject should not be displayed in the table")
                .isFalse();
    }

    @Test(groups = "positive")
    @Description("Test to verify that newly added timetable should be displayed")
    public void verifyCreatingTimetableOfSubject() {
        var subjectName = getValidSubject().getName();

        subjectStep.openAndFillSubjectFields(getValidSubject());
        subjectStep.addAndWaitForSubjectToAppear();

        var addedSubject = getSubjectsListByAPI(sessionId)
                .stream()
                .filter(subject -> subject.getName().equals(subjectName))
                .findFirst()
                .orElse(null);

        var soft = new SoftAssertions();
        soft.assertThat(addedSubject)
                .as("New subject should be present in API get request")
                .isNotNull();

        subjectsPage
                .getTable()
                .goToTablePage("last");

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        soft.assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of the table")
                .isTrue();

        subjectsPage.openTimetablePage(subjectName);
        subjectStep.openAndFillTimetableFields();
        subjectStep.addAndWaitForNewTimetableForAppear();

        soft.assertThat(getTimeTablesListByAPI(sessionId))
                .as("New timetable should be present in APi get request")
                .filteredOn(timeTableEntity -> timeTableEntity.getSubjectId().equals(addedSubject.getId()))
                .isNotEmpty();

        var group = "ТК-12-1";
        var isTimetableAdded = timetablePage.hasTimetable(group);

        soft.assertThat(isTimetableAdded)
                .as("New timetable should be displayed in the table")
                .isTrue();

        subjectStep.deleteTimetable(group);

        soft.assertThat(getTimeTablesListByAPI(sessionId))
                .as("Deleted timetable should not be present in APi get request")
                .filteredOn(timeTableEntity -> timeTableEntity.getSubjectId().equals(addedSubject.getId()))
                .isEmpty();

        var hasDeletedTimetable = timetablePage.hasTimetable(group);

        assertThat(hasDeletedTimetable)
                .as("Deleted timetable should not be displayed in the table")
                .isFalse();

        timetablePage
                .openSubjectsPage()
                .waitTillProgressBarDisappears()
                .setSearchValue(subjectName);

        subjectStep.deleteSubject(subjectName);

        soft.assertThat(getSubjectsListByAPI(sessionId))
                .as("Deleted subject should not be present in API get request")
                .filteredOn(subject -> subject.getName().equals(subjectName))
                .isEmpty();

        var hasDeletedSubject = subjectsPage
                .setSearchValue(subjectName)
                .hasSubject(subjectName);

        soft.assertThat(hasDeletedSubject)
                .as("Deleted subject should not be displayed in the table")
                .isFalse();

        soft.assertAll();
    }

    @Test(groups = "positive")
    @Description("Test to verify that the edited timetable should be displayed in api requests")
    public void verifyEditingTimetableOfSubject() {
        var initialSubjects = getSubjectsListByAPI(sessionId);
        var subjectName = getValidSubject().getName();
        var addedSubject = postSubject(getValidSubject())
                .stream()
                .filter(subject -> subject.getName().equals(subjectName))
                .findFirst()
                .orElse(null);
        var updatedSubjects = getSubjectsListByAPI(sessionId);
        var soft = new SoftAssertions();

        soft.assertThat(updatedSubjects)
                .as("After new subject added previous list of subjects cannot be the same as the current one")
                .isNotEqualTo(initialSubjects);
        soft.assertThat(addedSubject)
                .as("Posted subject has to be not null")
                .isNotNull();
        soft.assertThat(addedSubject.getName())
                .as("Posted subject has to be equal to the valid subject entity pattern")
                .isEqualTo(getValidSubject().getName());

        refresh();

        subjectsPage
                .getTable()
                .goToTablePage("last");

        var isAddedAtTheEnd = subjectsPage.hasSubject(subjectName);

        soft.assertThat(isAddedAtTheEnd)
                .as("New subject should be displayed at the end of the table")
                .isTrue();

        subjectsPage.openTimetablePage(subjectName);

        var timeTable = getTimeTableForSubject(addedSubject);
        var postedTimeTable = postTimeTable(timeTable)
                .stream()
                .filter(timeTableEntity -> timeTableEntity.getSubjectId().equals(addedSubject.getId()))
                .findFirst()
                .orElse(null);

        soft.assertThat(postedTimeTable)
                .as("Response of the timetable post request should not be null")
                .isNotNull();
        soft.assertThat(getTimeTablesListByAPI(sessionId))
                .as("New timetable should be present in APi get request")
                .contains(postedTimeTable);

        refresh();

        var group = "ТК-12-1";
        var isTimetableAdded = timetablePage
                .waitTillProgressBarDisappears()
                .hasTimetable(group);

        soft.assertThat(isTimetableAdded)
                .as("New timetable should be displayed in the table")
                .isTrue();

        var updatedTimeTable = getUpdatedTimeTableForSubject(postedTimeTable, addedSubject);

        var updatedTimeTableResponse = updateTimeTable(updatedTimeTable);

        soft.assertThat(getTimeTablesListByAPI(sessionId))
                .as("Timetable has to be modified after update")
                .doesNotContain(timeTable);
        soft.assertThat(updatedTimeTableResponse)
                .as("Update response has to be equal to the sent timetable")
                .filteredOn(timeTable1 -> timeTable1.getGroupId().equals(updatedTimeTable.getGroupId()))
                .isNotEmpty();

        var deleteTimeTableResponse = deleteTimeTable(sessionId, updatedTimeTable);

        soft.assertThat(getTimeTablesListByAPI(sessionId))
                .as("Deleted timetable should not be present in APi get request")
                .doesNotContain(updatedTimeTable);
        soft.assertThat(deleteTimeTableResponse.statusCode())
                .as("Status code has to be 200")
                .isEqualTo(200);

        timetablePage
                .openSubjectsPage()
                .waitTillProgressBarDisappears()
                .setSearchValue(subjectName);

        var deleteSubjectResponse = deleteSubject(sessionId, addedSubject);

        soft.assertThat(getSubjectsListByAPI(sessionId))
                .as("Deleted subject should not be present in API get request")
                .filteredOn(subject -> subject.getName().equals(subjectName))
                .isEmpty();
        soft.assertThat(deleteSubjectResponse.statusCode())
                .as("Status code has to be 200")
                .isEqualTo(200);

        refresh();

        var hasDeletedSubject = subjectsPage
                .setSearchValue(subjectName)
                .hasSubject(subjectName);

        soft.assertThat(hasDeletedSubject)
                .as("Deleted subject should not be displayed in the table")
                .isFalse();
        soft.assertAll();
    }
}