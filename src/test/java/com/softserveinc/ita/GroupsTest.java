package com.softserveinc.ita;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import com.softserveinc.ita.steps.GroupsStep;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.ApiUtil.performPostRequestWithBody;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static java.lang.String.format;
import static java.util.Map.of;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupsTest extends TestRunner {

    private GroupsPage groupsPage;
    private final GroupsStep steps = new GroupsStep();

    private Cookie sessionId;
    private SpecialityEntity speciality;
    private FacultyEntity faculty;

    @BeforeClass(groups = {"positive", "negative"})
    @Override
    public void setUp() {
        super.setUp();

        var adminCredentials = of("username", ADMIN_LOGIN, "password", ADMIN_PASSWORD);
        var authResponse = performPostRequestWithBody(adminCredentials, API_LOGIN_USER_PATH);

        sessionId = authResponse.getDetailedCookie("session_id");

        speciality = SpecialityEntity
                .builder()
                .name("Автоматизація та компютерно-інтегровані технології")
                .build();

        faculty = FacultyEntity
                .builder()
                .name("Інститут інформаційних технологій")
                .build();
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openGroupsPage() {
        steps.openPage();
        groupsPage = steps.getPage();
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

        var groups = getGroupsListByAPI();

        assertThat(groups)
                .as("Before adding new group it shouldn't be present in the list of groups returned by API call")
                .doesNotContain(group.getName());

        steps.addNewGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        var soft = getSoftAssert();
        soft.assertThat(lastGroupName)
                .as("After adding new group group with added name " +
                        "should be last in the table")
                .isEqualTo(group.getName());


        groups = getGroupsListByAPI();

        soft.assertThat(groups)
                .as("After adding new group it should be present in the list of groups returned by API call")
                .contains(group.getName());

        soft.assertAll();

        steps.deleteGroup(group);
    }

    @Test(groups = "positive")
    @Description("Test to verify new group is added")
    public void verifyDeletingGroup() {
        var group = GroupEntity.getNewValidGroup(speciality, faculty);

        steps.addNewGroup(group);

        var groups = getGroupsListByAPI();

        assertThat(groups)
                .as("After adding new group it should be present in the list of groups returned by API call")
                .contains(group.getName());

        steps.deleteGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        var soft = getSoftAssert();
        soft.assertThat(lastGroupName)
                .as("After deleting group last group in the table " +
                        "should not have deleted name")
                .isNotEqualTo(group.getName());

        groups = getGroupsListByAPI();

        soft.assertThat(groups)
                .as("After deleting group it shouldn't be present in the list of groups returned by API call")
                .doesNotContain(group.getName());

        soft.assertAll();
    }

    @AfterClass
    public void tearDown() {
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }

    private List<Object> getGroupsListByAPI() {
        var path = format(API_ENTITY_GET_RECORDS_PATH, "group");
        var response = performGetRequest(sessionId, path);

        return response
                .getBody()
                .jsonPath()
                .getList("group_name");
    }
}
