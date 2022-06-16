package com.softserveinc.ita.steps;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import lombok.Getter;

import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;

@Getter
public class GroupsSteps {
    private GroupsPage page;

    public void openSpecialityPage() {
        page = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openGroupsPage();
    }

    public void addNewGroup(GroupEntity group) {
        page
                .openAddingNewForm()
                .setCode(group.getName())
                .setSpeciality(group
                        .getSpeciality()
                        .getName())
                .setFaculty(group
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

        page
                .confirmModal()
                .waitTillProgressBarDisappears();
    }
}
