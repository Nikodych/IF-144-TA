package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import lombok.Getter;

import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_PASSWORD;

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
                .setCode(speciality.getCode())
                .setName(speciality.getName())
                .confirmModal()
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }

    public void deleteSpeciality(SpecialityEntity speciality) {
        var searchValue = speciality.getCode(); //consider code as unique id
        page
                .findTablePageWithSearchValue(searchValue)
                .deleteRowByValue(searchValue)
                .confirmModal()
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear();
    }
}