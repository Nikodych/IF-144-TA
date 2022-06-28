package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.StudentsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
                .as("If student is added successfully  messageText should be equal " + STUDENT_IS_ADDED_SUCCESSFUL_MESSAGE)
                .isEqualTo(STUDENT_IS_ADDED_SUCCESSFUL_MESSAGE);

        var actualResult = studentsPage.getStudentsGradeBookId(student.getGradeBookId());

        assertThat(actualResult)
                .as("After student is added student with grade book id should be present at the table")
                .isEqualTo(student.getGradeBookId());

        studentsStep.deleteStudent(student);
    }
}
