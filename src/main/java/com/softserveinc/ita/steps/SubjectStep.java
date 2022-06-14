package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;

import static com.softserveinc.ita.models.AddingFormFields.SUBJECT_DESCRIPTION;
import static com.softserveinc.ita.models.AddingFormFields.SUBJECT_NAME;

public class SubjectStep {

    public void openAndFillSubjectFields(SubjectEntity subject) {
        new SubjectsPage()
                .openAddingNewForm()
                .setValueFor(SUBJECT_NAME, subject.getName())
                .setValueFor(SUBJECT_DESCRIPTION, subject.getDescription());
    }
}
