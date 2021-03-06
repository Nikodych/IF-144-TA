package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;

import com.softserveinc.ita.pageobjects.modals.AddingAndEditingFormModal;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;
import lombok.Getter;

import static com.softserveinc.ita.models.FormFields.*;
import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

@Getter
public class GroupsStep {
    private GroupsPage page;

    public void openPage() {
        page = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openGroupsPage();
    }

    public void addNewGroup(GroupEntity group) {
        var specialityName = group
                .getSpeciality()
                .getName();

        var facultyName = group
                .getFaculty()
                .getName();

        page
                .openAddingNewForm()
                .setValueFor(GROUP_NAME, group.getName())
                .setValueFor(GROUP_SPECIALTY_ID, specialityName)
                .setValueFor(GROUP_FACULTY_ID, facultyName)
                .confirmModal();

        page.waitTillProgressBarDisappears();
    }

    public void deleteGroup(GroupEntity group) {
        var searchValue = group.getName(); //consider name as unique id
        var table = page.getTable();

        table.findTablePageWithSearchValue(searchValue);
        table.deleteRowByValue(searchValue);

        new DeletingFormModal().confirmModal();
        page.waitTillProgressBarDisappears();
    }

    public void editGroup(String searchValue, GroupEntity group) {
        var table = page.getTable();
        table.findTablePageWithSearchValue(searchValue);
        table.editRowByValue(searchValue);

        var specialityName = group
                .getSpeciality()
                .getName();

        var facultyName = group
                .getFaculty()
                .getName();

        var addingForm = new AddingAndEditingFormModal();
        addingForm
                .setValueFor(GROUP_NAME, group.getName())
                .setValueFor(GROUP_SPECIALTY_ID, specialityName)
                .setValueFor(GROUP_FACULTY_ID, facultyName)
                .confirmModal();
        addingForm.waitToDisappear();

        page.waitForProgressBarToDisappear();
    }
}
