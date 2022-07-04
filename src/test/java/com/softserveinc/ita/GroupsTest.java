package com.softserveinc.ita;

import com.softserveinc.ita.apisteps.FacultyApiStep;
import com.softserveinc.ita.apisteps.GroupsApiStep;
import com.softserveinc.ita.apisteps.SpecialitiesApiStep;
import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.util.ApiUtil.*;
import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.API_LOGOUT_PATH;
import static com.softserveinc.ita.util.DataProvider.GROUPS_PAGE_URL;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupsTest extends TestRunner {

    private GroupsPage groupsPage;
    private Cookie sessionId;
    private SpecialityEntity speciality;
    private FacultyEntity faculty;
    private GroupsApiStep groupsApiStep;
    private final List<GroupEntity> addedGroups = new ArrayList<>();

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpGroupsTests() {
        sessionId = getSessionsCookie(authAsAdmin());
        groupsApiStep = new GroupsApiStep(sessionId);

        var specialitiesApiStep = new SpecialitiesApiStep(sessionId);
        speciality = specialitiesApiStep.createNewSpeciality();

        var facultiesApiStep = new FacultyApiStep(sessionId);
        faculty = facultiesApiStep.createNewFaculty();
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openGroupsPage() {
        groupsStep.openPage();
        groupsPage = groupsStep.getPage();
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
    @Description("Test to verify new group is added")
    public void verifyAddingNewGroup() {
        var group = GroupEntity.getNewValidGroup(speciality, faculty);
        groupsApiStep.verifyGroupNotExist(group);

        groupsStep.addNewGroup(group);

        group = groupsApiStep.findGroup(group);
        addedGroups.add(group);

        refresh(); // for some reason there is no auto refresh after adding for this page

        var lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName)
                .as("After adding new group group with added name " +
                        "should be last in the table")
                .isEqualTo(group.getName());
    }

    @Test(groups = "positive")
    @Description("Test to verify group is deleted")
    public void verifyDeletingGroup() {
        var group = groupsApiStep.createNewGroup(speciality, faculty);
        addedGroups.add(group);
        refresh();
        groupsPage.waitForProgressBarToDisappear();

        groupsStep.deleteGroup(group);

        groupsApiStep.verifyGroupNotExist(group);

        var lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName)
                .as("After deleting group last group in the table " +
                        "should not have deleted name")
                .isNotEqualTo(group.getName());
    }

    @Test(groups = "positive")
    @Description("Test to verify group is edited")
    public void verifyEditingGroup() {
        var group = groupsApiStep.createNewGroup(speciality, faculty);
        addedGroups.add(group);
        refresh();
        groupsPage.waitForProgressBarToDisappear();

        var previousName = group.getName();
        group.setName(previousName.replace(previousName.substring(0,5),"edit"));

        groupsStep.editGroup(previousName, group);

        group = groupsApiStep.findGroup(group);
        addedGroups.add(group);

        var lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName)
                .as("After editing group last group in the table should have name after edit")
                 .isEqualTo(group.getName());
    }

    @AfterClass
    public void tearDown() {
        for (GroupEntity group: addedGroups) {
            if (getGroupByID(group.getId(), sessionId) != null) {
                deleteGroup(sessionId, group);
            }
        }
        deleteSpeciality(sessionId, speciality);
        deleteFaculty(sessionId, faculty);
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }
}
