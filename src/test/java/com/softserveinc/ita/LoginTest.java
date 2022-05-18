package com.softserveinc.ita;

import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_LOGIN;
import static com.softserveinc.ita.pageobjects.util.DataProvider.ADMIN_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.Test;

public class LoginTest extends TestRunner {

    @Test
    public void verifyAdminLogin() {
        String loggedUserName = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .getLoggedUserName();

        assertThat(loggedUserName).isEqualTo(ADMIN_LOGIN);
    }
}
