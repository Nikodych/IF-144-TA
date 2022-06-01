package com.softserveinc.ita.steps;

import com.softserveinc.ita.pageobjects.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecialitiesSteps {
    public void addNewSpeciality(SpecialitiesPage page, SpecialityEntity speciality) {
        page
                .openAddingNewForm()
                .setCode(speciality.getCode())
                .setName(speciality.getName())
                .confirmModal()
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }

    public void deleteSpeciality(SpecialitiesPage page, SpecialityEntity speciality) {
        page
                .deleteSpeciality(speciality)
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }
}
