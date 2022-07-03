package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.StudentsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.models.FormFields.STUDENT_NAME;
import static com.softserveinc.ita.repos.StudentRepo.getNewValidStudent;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class StudentsTest extends TestRunner {
    private StudentsPage studentsPage;

    @BeforeMethod(groups = {"positive", "negative"})
    public void openGroupsStudentsPage() {
        studentsStep.openStudentsPage(GROUP_NAME);
        studentsPage = studentsStep.getPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify Groups` Students page opening")
    public void verifyStudentsPageOpening() {
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should contain " + STUDENTS_PAGE_URL)
                .contains(STUDENTS_PAGE_URL);
    }

    @Test(groups = "positive")
    @Description("Test to verify new student added")
    public void verifyNewStudentAdded() {
        var student = getNewValidStudent();
        studentsStep.addNewStudent(student);

        var messageText = studentsPage.getMessageText();

        assertThat(messageText)
                .as("If student is added successfully  messageText should be equal " + STUDENT_IS_ADDED_SUCCESSFULLY_MESSAGE)
                .isEqualTo(STUDENT_IS_ADDED_SUCCESSFULLY_MESSAGE);

        var actualResult = studentsPage.getStudentsGradeBookId(student.getGradeBookId());

        assertThat(actualResult)
                .as("After student is added student with grade book id should be present at the table")
                .isEqualTo(student.getGradeBookId());

        studentsStep.deleteStudent(student);
    }

    @Test(groups = "positive")
    @Description("Test to verify student deleted")
    public void verifyStudentDeleted() {
        var student = getNewValidStudent();
        studentsStep.addNewStudent(student);
        var studentsGradeBookId = studentsPage.getStudentsGradeBookId(student.getGradeBookId());

        assertThat(studentsGradeBookId)
                .as("After student is added student with grade book id should be present at the table")
                .isEqualTo(student.getGradeBookId());

        studentsStep.deleteStudent(student);

        var messageText = studentsPage.getMessageText();

        assertThat(messageText)
                .as("When student deleted message text should be equal " + STUDENT_IS_DELETED_SUCCESSFULLY_MESSAGE)
                .isEqualTo(STUDENT_IS_DELETED_SUCCESSFULLY_MESSAGE);

        var table = studentsPage.getTable();
        table.findTablePageWithSearchValue(studentsGradeBookId);
        var actualResult = table.isSearchValueInTableTexts(studentsGradeBookId);

        assertThat(actualResult)
                .as("After student is deleted it shouldn't be found in the table")
                .isFalse();
    }

    @Test(groups = "positive")
    @Description("Test to verify editing students data")
    public void verifyStudentsDataEdited() {
        var student = getNewValidStudent();
        studentsStep.addNewStudent(student);

        var studentsGradeBookId = student.getGradeBookId();

        var table = studentsPage.getTable();
        table.findTablePageWithSearchValue(studentsGradeBookId);

        var isStudentPresentInTheTable = table.isSearchValueInTableTexts(studentsGradeBookId);

        var soft = getSoftAssert();

        soft.assertThat(isStudentPresentInTheTable)
                .as("After student is added student with grade book id should be present at the table")
                .isTrue();

        studentsStep.editStudent(student, STUDENT_NAME);
        var oldName = student.getName();

        var doesStudentsDataFormContainsOldValue = studentsPage.hasStudentsDataChanged(student, oldName);

        soft.assertThat(doesStudentsDataFormContainsOldValue)
                .as("After student is edited student's data form shouldn't contain old value")
                .isFalse();

        studentsStep.deleteStudent(student);
        soft.assertAll();
    }
}
