package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import lombok.Getter;

import static com.softserveinc.ita.models.AddingFormFields.SPECIALTY_CODE;
import static com.softserveinc.ita.models.AddingFormFields.SPECIALTY_NAME;
import static com.softserveinc.ita.util.DataProvider.*;

@Getter
public class SpecialitiesSteps {

    private SpecialitiesPage page;

    public void openSpecialityPage() {
        page = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSpecialitiesPage();
    }

    public void addNewSpeciality(SpecialityEntity speciality) {
        page
                .openAddingNewForm()
                .setValueFor(SPECIALTY_CODE, speciality.getCode())
                .setValueFor(SPECIALTY_NAME, speciality.getName())
                .confirmModal();
        page
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }

    public void deleteSpeciality(SpecialityEntity speciality) {
        var searchValue = speciality.getCode(); //consider code as unique id
        page
                .findTablePageWithSearchValue(searchValue)
                .deleteRowByValue(searchValue)
                .confirmModal();
        page
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }
}
