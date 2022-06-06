package com.softserveinc.ita;

import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import com.softserveinc.ita.steps.SpecialitiesSteps;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupsTest extends TestRunner {

    private GroupsPage groupsPage;

    @BeforeMethod(groups = {"positive", "negative"})
    public void openGroupsPage() {
        groupsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openGroupsPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify Groups page opening")
    public void verifyGroupsPageOpening() {

        var expectedUrl = GROUPS_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test(groups = "positive")
    @Description("Test to verify group adding")
    public void verifyAddingNewGroup() {

        var speciality = SpecialityEntity.getNewValidSpeciality();
        new SpecialitiesSteps().addNewSpeciality(speciality);

        var group = GroupEntity
                .builder()
                .name(GroupEntity.getNewValidName())
                .speciality(speciality)
                .build();

        groupsPage
                .openAddingNewForm()
                .setCode(group.getName())
                .setSpeciality(group
                        .getSpeciality()
                        .getName())
                .setFaculty(group.getFaculty())
                .confirmModal();

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName).isEqualTo(group.getName());
    }
}
