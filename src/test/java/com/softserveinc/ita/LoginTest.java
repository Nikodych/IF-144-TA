package com.softserveinc.ita;

import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_PASSWORD;
import static com.softserveinc.ita.pageobjects.util.DataProvider.STUDENT_LOGIN;
import static com.softserveinc.ita.pageobjects.util.DataProvider.STUDENT_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {

    @Test
    public void verifyAdminCanLogin() {
        var loggedUserName = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .getLoggedUserName();

        assertThat(loggedUserName)
                .as("Logged user name should be equal to entered user name")
                .isEqualTo(ADMIN_LOGIN);
    }

    @Test
    public void verifyStudentCanLogin() {
        var loggedUserName = new LoginPage()
                .login(STUDENT_LOGIN, STUDENT_PASSWORD)
                .getLoggedUserName();

        assertThat(loggedUserName)
                .as("Logged user name should be equal to entered user name")
                .isEqualTo(STUDENT_LOGIN);
    }
}
