package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;
import lombok.Getter;

import static com.softserveinc.ita.models.AddingFormFields.*;
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
        page
                .openAddingNewForm()
                .setValueFor(GROUP_NAME, group.getName())
                .setValueFor(GROUP_SPECIALTY_ID, group
                        .getSpeciality()
                        .getName())
                .setValueFor(GROUP_FACULTY_ID, group
                        .getFaculty()
                        .getName())
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
}
