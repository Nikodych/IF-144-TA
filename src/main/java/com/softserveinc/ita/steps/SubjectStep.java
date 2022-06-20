package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.admin.TimeTablePage;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;

import static com.softserveinc.ita.models.AddingFormFields.SUBJECT_DESCRIPTION;
import static com.softserveinc.ita.models.AddingFormFields.SUBJECT_NAME;

public class SubjectStep {
    SubjectsPage subjectsPage = new SubjectsPage();
    TimeTablePage timetablePage = new TimeTablePage();

    public void openAndFillSubjectFields(SubjectEntity subject) {
        subjectsPage
                .openAddingNewForm()
                .setValueFor(SUBJECT_NAME, subject.getName())
                .setValueFor(SUBJECT_DESCRIPTION, subject.getDescription());
    }

    public void addAndWaitForSubjectToAppear() {
        new AddingFormModal().confirmModal();

        subjectsPage.waitTillProgressBarDisappears();
    }

    public void deleteSubject(String subject) {
        subjectsPage
                .deleteSubjectByName(subject)
                .confirmDeletingSubject()
                .waitTillProgressBarDisappears();
    }

    public void editSubjectFields(String subject, String substring) {
        subjectsPage
                .editSubject(subject)
                .setValueFor(SUBJECT_NAME, substring)
                .setValueFor(SUBJECT_DESCRIPTION, substring)
                .confirmModal();

        subjectsPage.waitTillProgressBarDisappears();
    }

    public void openAndFillTimetableFields() {
        timetablePage
                .addTimeTable()
                .setGroupBy(4)
                .setStartDate()
                .setStartTime()
                .setEndDate()
                .setEndTime();
    }

    public void addAndWaitForNewTimetableForAppear() {
        new AddingFormModal().confirmModal();

        timetablePage.waitTillProgressBarDisappears();
    }

    public void deleteTimetable(String group) {
        timetablePage
                .deleteTimetableByGroup(group)
                .confirmDeletingTimetable()
                .waitTillProgressBarDisappears();
    }
}
