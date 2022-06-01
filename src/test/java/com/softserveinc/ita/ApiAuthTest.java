package com.softserveinc.ita;

import com.softserveinc.ita.api.Api;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;

public class ApiAuthTest {

    private Api api;
    private SoftAssertions soft;

    @BeforeMethod
    public void setUpApi() {
        api = new Api();
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
    @Description("Test to verify admin user is logged")
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

        var response = api
                .performGET(getSessionsCookie(authResponse), API_IS_LOGGED_PATH);

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

        var response = api
                .performGET(getSessionsCookie(authResponse), API_LOGOUT_PATH);

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

    private Object getValueFromResponseBody(Response response, String key) {
        return response
                .getBody()
                .jsonPath()
                .get(key);
    }

    private Cookie getSessionsCookie(Response response) {
        return response.getDetailedCookie("session_id");
    }

    private Response authAsAdmin() {
        return api.performPOSTWithBody(setUpAdminCredentials(), API_LOGIN_USER_PATH);
    }

    private Response authAsStudent() {
        return api.performPOSTWithBody(setUpStudentCredentials(), API_LOGIN_USER_PATH);
    }

    private Map<String, ?> setUpAdminCredentials() {
        return setUpAuthRequestBody(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    private Map<String, ?> setUpStudentCredentials() {
        return setUpAuthRequestBody(STUDENT_LOGIN, STUDENT_PASSWORD);
    }

    private Map<String, ?> setUpAuthRequestBody(String username, String password) {
        Map<String, Object> bodyOfRequest = new HashMap<>();
        bodyOfRequest.put("username", username);
        bodyOfRequest.put("password", password);

        return bodyOfRequest;
    }
}
