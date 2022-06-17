package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.pageobjects.admin.AddingSubjectModal;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.admin.TimetablePage;

public class SubjectStep {

    public void openAndFillSubjectFields(SubjectEntity subject) {
        new SubjectsPage()
                .openAddingSubjectForm()
                .setSubjectTitle(subject.getName())
                .setSubjectDescription(subject.getDescription());
    }

    public void addAndWaitForSubjectToAppear() {
        new AddingSubjectModal()
                .addNewSubject()
                .waitTillProgressBarDisappears();
    }

    public void deleteSubject(String subject) {
        new SubjectsPage()
                .deleteSubjectByName(subject)
                .confirmDeletingSubject();
    }

    public void editSubjectFields(String subject, String substring) {
        new SubjectsPage()
                .editSubject(subject)
                .setSubjectTitle(substring)
                .setSubjectDescription(substring)
                .addNewSubject();
    }

    public void openAndFillTimetableFields() {
        new TimetablePage()
                .addTimetable()
                .setGroupBy(2)
                .setStartDate()
                .setStartTime()
                .setEndDate()
                .setEndTime();
    }

    public void addAndWaitForNewTimetableForAppear() {
        new TimetablePage()
                .submitAdding()
                .waitTillProgressBarDisappears();
    }
}
