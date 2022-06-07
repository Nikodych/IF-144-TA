package com.softserveinc.ita;

import static com.softserveinc.ita.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.util.DataProvider.ADMIN_PASSWORD;
import static com.softserveinc.ita.util.DataProvider.STUDENT_LOGIN;
import static com.softserveinc.ita.util.DataProvider.STUDENT_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends TestRunner {

    @Test (groups = "positive")
    @Description("Test to verify that admin user can login")
    public void verifyAdminCanLogin() {
        var loggedUserName = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .getLoggedUserName();

        assertThat(loggedUserName)
                .as("Logged user name should be equal to entered user name")
                .isEqualTo(ADMIN_LOGIN);
    }

    @Test (groups = "positive")
    @Description("Test to verify that student user can login")
    public void verifyStudentCanLogin() {
        var loggedUserName = new LoginPage()
                .login(STUDENT_LOGIN, STUDENT_PASSWORD)
                .getLoggedUserName();

        assertThat(loggedUserName)
                .as("Logged user name should be equal to entered user name")
                .isEqualTo(STUDENT_LOGIN);
    }
}
