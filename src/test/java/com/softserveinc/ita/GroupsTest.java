package com.softserveinc.ita;

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

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.util.ApiUtil.getGroupsListByAPI;
import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
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

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpGroupsTests() {

        sessionId = getSessionsCookie(authAsAdmin());

        speciality = SpecialityEntity
                .builder()
                .id("1")
                .name("Автоматизація та компютерно-інтегровані технології")
                .build();

        faculty = FacultyEntity
                .builder()
                .id("1")
                .name("Інститут інформаційних технологій")
                .build();
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

        var groups = getGroupsListByAPI(sessionId);

        assertThat(groups)
                .as("Before adding new group it shouldn't be present in the list of groups returned by API call")
                .doesNotContain(group);

        groupsStep.addNewGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        var soft = getSoftAssert();
        soft.assertThat(lastGroupName)
                .as("After adding new group group with added name " +
                        "should be last in the table")
                .isEqualTo(group.getName());


        groups = getGroupsListByAPI(sessionId);

        soft.assertThat(groups)
                .as("After adding new group it should be present in the list of groups returned by API call")
                .contains(group);

        soft.assertAll();

        groupsStep.deleteGroup(group);
    }

    @Test(groups = "positive")
    @Description("Test to verify new group is added")
    public void verifyDeletingGroup() {
        var group = GroupEntity.getNewValidGroup(speciality, faculty);

        groupsStep.addNewGroup(group);

        var groups = getGroupsListByAPI(sessionId);

        assertThat(groups)
                .as("After adding new group it should be present in the list of groups returned by API call")
                .contains(group);

        groupsStep.deleteGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        var soft = getSoftAssert();
        soft.assertThat(lastGroupName)
                .as("After deleting group last group in the table " +
                        "should not have deleted name")
                .isNotEqualTo(group.getName());

        groups = getGroupsListByAPI(sessionId);

        soft.assertThat(groups)
                .as("After deleting group it shouldn't be present in the list of groups returned by API call")
                .doesNotContain(group);

        soft.assertAll();
    }

    @AfterClass
    public void tearDown() {
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }
}
