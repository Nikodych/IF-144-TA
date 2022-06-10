package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;

import static com.softserveinc.ita.models.AddingFormFields.SUBJECT_DESCRIPTION;
import static com.softserveinc.ita.models.AddingFormFields.SUBJECT_NAME;

public class SubjectStep {

    public AddingFormModal openAndFillSubjectFields(SubjectEntity subject) {
       return new SubjectsPage()
                .openAddingSubjectForm()
                .setValueFor(SUBJECT_NAME, subject.getName())
                .setValueFor(SUBJECT_DESCRIPTION, subject.getDescription());
    }
}
