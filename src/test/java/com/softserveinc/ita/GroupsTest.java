package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.GroupsPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class GroupsTest extends TestRunner {

    private GroupsPage groupsPage;

    @BeforeMethod
    public void openGroupsPage() {
        groupsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openGroupsPage();
    }

    @Test
    public void verifyGroupsPageOpening() {

        var expectedUrl = GROUPS_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }
}
