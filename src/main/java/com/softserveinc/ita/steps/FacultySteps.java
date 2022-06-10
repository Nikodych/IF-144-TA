package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.pageobjects.admin.FacultiesPage;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;

import static com.softserveinc.ita.models.AddingFormFields.FACULTY_DESCRIPTION;
import static com.softserveinc.ita.models.AddingFormFields.FACULTY_NAME;

public class FacultySteps {
    public AddingFormModal openAndFillFacultyFields(FacultyEntity faculty) {
        return new FacultiesPage()
                .openAddingFacultyForm()
                .setValueFor(FACULTY_NAME, faculty.getName())
                .setValueFor(FACULTY_DESCRIPTION, faculty.getDescription());
    }
}
