package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.DateTimeRange;
import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.admin.TimeTablePage;
import com.softserveinc.ita.pageobjects.modals.AddingAndEditingFormModal;
import org.joda.time.LocalDateTime;

import static com.softserveinc.ita.models.FormFields.SUBJECT_DESCRIPTION;
import static com.softserveinc.ita.models.FormFields.SUBJECT_NAME;

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
        new AddingAndEditingFormModal().confirmModal();

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
        var currentDate = LocalDateTime.now();
        var dateRange = DateTimeRange
                .builder()
                .start(currentDate
                        .millisOfDay()
                        .withMinimumValue())
                .end(currentDate
                        .millisOfDay()
                        .withMaximumValue())
                .build();

        timetablePage
                .addTimeTable()
                .setGroupBy(4)
                .setStartDate(dateRange.getStart())
                .setStartTime(dateRange.getStart())
                .setEndDate(dateRange.getEnd())
                .setEndTime(dateRange.getEnd());
    }

    public void addAndWaitForNewTimetableForAppear() {
        new AddingAndEditingFormModal().confirmModal();

        timetablePage.waitTillProgressBarDisappears();
    }

    public void deleteTimetable(String group) {
        timetablePage
                .deleteTimetableByGroup(group)
                .confirmDeletingTimetable()
                .waitTillProgressBarDisappears();
    }
}
