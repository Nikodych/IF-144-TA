package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;
import lombok.Getter;

import static com.softserveinc.ita.models.AddingFormFields.SPECIALTY_CODE;
import static com.softserveinc.ita.models.AddingFormFields.SPECIALTY_NAME;
import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

@Getter
public class SpecialitiesStep {

    private SpecialitiesPage page;

    public void openPage() {
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

        page.waitForProgressBarToDisappear();
    }

    public void deleteSpeciality(SpecialityEntity speciality) {
        var searchValue = speciality.getCode(); //consider code as unique id

        var table = page.getTable();
        table.findTablePageWithSearchValue(searchValue);
        table.deleteRowByValue(searchValue);

        new DeletingFormModal().confirmModal();

        page.waitTillProgressBarDisappears();
    }

    public void editSpeciality(SpecialityEntity speciality) {
        var searchValue = speciality.getCode(); //consider code as unique id

        var table = page.getTable();
        table.findTablePageWithSearchValue(searchValue);
        table.editRowByValue(searchValue);

        var addingForm = new AddingFormModal();
        addingForm.setValueFor(SPECIALTY_NAME, speciality.getName()) //left code as it was
                .confirmModal();
        addingForm.waitToDisappear();

        page.waitForProgressBarToDisappear();
    }
}
