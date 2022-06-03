package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.pageobjects.admin.SubjectsPage;

public class SubjectSteps {

    public void openAndFillSubjectFields(SubjectEntity subject) {
        new SubjectsPage()
                .openAddingSubjectForm()
                .setSubjectTitle(subject.getName())
                .setSubjectDescription(subject.getDescription());
    }
}
