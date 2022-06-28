package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.StudentEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.StudentsPage;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.softserveinc.ita.models.AddingFormFields.*;
import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

@Getter
public class StudentsStep {
    private StudentsPage page;

    @Step("Students step: opened students page of {group}")
    public void openStudentsPage(String group) {
        page = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openGroupsPage()
                .openStudentsPage(group);
    }

    @Step("Students step: added new student")
    public void addNewStudent(StudentEntity student) {
        page.openAddingNewForm()
                .setValueFor(STUDENT_SURNAME, student.getSurname())
                .setValueFor(STUDENT_NAME, student.getName())
                .setValueFor(STUDENT_FATHERNAME, student.getFatherName())
                .setValueFor(STUDENT_GRADEBOOK_ID, student.getGradeBookId())
                .setValueFor(STUDENT_LOGIN, student.getLogin())
                .setValueFor(STUDENT_EMAIL, student.getEmail())
                .setValueFor(STUDENT_PASSWORD, student.getPassword())
                .setValueFor(STUDENT_PASSWORD_CONFIRM, student.getPasswordConfirm())
                .confirmModal();
    }

    @Step("Students step: deleted student")
    public void deleteStudent(StudentEntity student) {
        var gradeBookId = student.getGradeBookId();

        var table = page.getTable();
        table.findTablePageWithSearchValue(gradeBookId);
        table.deleteRowByValue(gradeBookId);

        new DeletingFormModal().confirmModal();

        page.waitTillProgressBarDisappears();
    }
}
