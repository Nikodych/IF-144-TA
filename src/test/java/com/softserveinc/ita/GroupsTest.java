package com.softserveinc.ita;

import com.softserveinc.ita.models.FacultyEntity;
import com.softserveinc.ita.models.GroupEntity;
import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import com.softserveinc.ita.steps.GroupsSteps;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.ApiUtil.performPostRequestWithBody;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static java.util.Map.of;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupsTest extends TestRunner {

    private GroupsPage groupsPage;
    private GroupsSteps steps = new GroupsSteps();

    @BeforeMethod(groups = {"positive", "negative"})
    public void openGroupsPage() {
        steps.openSpecialityPage();
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
        // TODO: 10.06.2022 move speciality, faculty to BeforeClass
        var speciality = SpecialityEntity
                .builder()
                .name("Автоматизація та компютерно-інтегровані технології")
                .build();

        var faculty = FacultyEntity
                .builder()
                .name("Інститут інформаційних технологій")
                .build();

        var group = GroupEntity
                .builder()
                .name(GroupEntity.getNewValidName())
                .speciality(speciality)
                .faculty(faculty)
                .build();

        steps.addNewGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName)
                .as("After adding new group group with added name " +
                        "should be last in the table")
                .isEqualTo(group.getName());

        var adminCredentials = of("username", ADMIN_LOGIN, "password", ADMIN_PASSWORD);

        var authResponse = performPostRequestWithBody(adminCredentials, API_LOGIN_USER_PATH);

        var response = performGetRequest(authResponse.getDetailedCookie("session_id"), "/group/getRecords");

    }

    @Test(groups = "positive")
    @Description("Test to verify new group is added")
    public void verifyDeletingGroup() {
        // TODO: 10.06.2022 move speciality, faculty to BeforeClass
        var speciality = SpecialityEntity
                .builder()
                .name("Автоматизація та компютерно-інтегровані технології")
                .build();

        var faculty = FacultyEntity
                .builder()
                .name("Інститут інформаційних технологій")
                .build();

        var group = GroupEntity
                .builder()
                .name(GroupEntity.getNewValidName())
                .speciality(speciality)
                .faculty(faculty)
                .build();

        steps.addNewGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        var lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName)
                .as("After adding new group group with added name " +
                        "should be last in the table")
                .isEqualTo(group.getName());

        steps.deleteGroup(group);

        refresh(); // for some reason there is no auto refresh for this page

        lastGroupName = groupsPage.getLastGroupName();

        assertThat(lastGroupName)
                .as("After deleting group last group in the table " +
                        "should not have deleted name")
                .isNotEqualTo(group.getName());
    }
}
