package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.AdminsPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class AdminsTest extends TestRunner {

    private AdminsPage adminsPage;

    @BeforeMethod (groups = {"positive", "negative"})
    public void openAdminsPage() {
        adminsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openAdminsPage();
    }

    @Test (groups = "positive")
    @Description("Test to verify Admins page opening")
    public void verifyAdminsPageOpening() {

        var expectedUrl = ADMINS_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }
}
