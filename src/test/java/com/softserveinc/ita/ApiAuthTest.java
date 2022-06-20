package com.softserveinc.ita;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.AuthApiUtil.*;
import static com.softserveinc.ita.util.DataProvider.*;

public class ApiAuthTest {

    private SoftAssertions soft;

    @BeforeMethod
    public void setUpSoftAssertions() {
        soft = new SoftAssertions();
    }

    @Test
    @Description("Test to verify admin user`s authentication")
    public void verifyAdminAuth() {
        verifyUserAuth(authAsAdmin(), ADMIN_LOGIN);
    }

    @Test
    @Description("Test to verify students user`s authentication")
    public void verifyStudentUserAuth() {
        verifyUserAuth(authAsStudent(), STUDENT_LOGIN);
    }

    @Test
    @Description("Test to verify admin user is logged")
    public void verifyAdminUserIsLogged() {
        verifyUserIsLogged(authAsAdmin(), ADMIN_LOGIN);
    }

    @Test
    @Description("Test to verify student user is logged")
    public void verifyStudentUserIsLogged() {
        verifyUserIsLogged(authAsStudent(), STUDENT_LOGIN);
    }

    @Test
    @Description("Test to verify admin user should be able to logout")
    public void verifyAdminUserCanLogout() {
        verifyUserCanLogout(authAsAdmin());
    }

    @Test
    @Description("Test to verify student user should be able to logout")
    public void verifyStudentUserCanLogout() {
        verifyUserCanLogout(authAsStudent());
    }

    //method to verify related user`s authentication
    private void verifyUserAuth(Response response, String username) {
        var actualResponseCode = response.statusCode();
        var actualUsername = getValueFromResponseBody(response,"username");

        soft.assertThat(actualResponseCode)
                .as("Should be 200")
                .isEqualTo(200);

        soft.assertThat(actualUsername)
                .as("Username in response should be " + username)
                .isEqualTo(username);

        soft.assertAll();
    }

    //method to verify related user is logged
    private void verifyUserIsLogged(Response authResponse, String username) {
        var response = performGetRequest(getSessionsCookie(authResponse), API_IS_LOGGED_PATH);

        var actualResponseCode = response.statusCode();
        var actualStatus = getValueFromResponseBody(response,"response");
        var actualUsername = getValueFromResponseBody(response, "username");

        soft.assertThat(actualResponseCode)
                .as("Should be 200")
                .isEqualTo(200);

        soft.assertThat(actualStatus)
                .as("Response should contain status: \"logged\" ")
                .isEqualTo("logged");

        soft.assertThat(actualUsername)
                .as("Username in response should be " + username)
                .isEqualTo(username);

        soft.assertAll();
    }

    //method to verify related user`s can logout
    private void verifyUserCanLogout(Response authResponse) {
        var response = performGetRequest(getSessionsCookie(authResponse), API_LOGOUT_PATH);

        var actualResponseCode = response.statusCode();
        var actualStatus = getValueFromResponseBody(response,"response");

        soft.assertThat(actualResponseCode)
                .as("Should be 200")
                .isEqualTo(200);

        soft.assertThat(actualStatus)
                .as("Response should contain status: \"user has been logout\" ")
                .isEqualTo("user has been logout");

        soft.assertAll();
    }
}