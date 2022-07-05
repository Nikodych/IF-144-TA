package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.pageobjects.admin.FacultiesPage;

import static com.softserveinc.ita.models.FormFields.FACULTY_DESCRIPTION;
import static com.softserveinc.ita.models.FormFields.FACULTY_NAME;

public class FacultyStep {

    public void openAndFillFacultyFields(FacultyEntity faculty) {
        new FacultiesPage()
                .openAddingNewForm()
                .setValueFor(FACULTY_NAME, faculty.getName())
                .setValueFor(FACULTY_DESCRIPTION, faculty.getDescription());
    }
}
