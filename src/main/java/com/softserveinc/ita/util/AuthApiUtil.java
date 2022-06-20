package com.softserveinc.ita.util;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

import java.util.Map;

import static com.softserveinc.ita.util.ApiUtil.performPostRequestWithBody;
import static com.softserveinc.ita.util.DataProvider.*;
import static java.util.Map.of;

public class AuthApiUtil {

    public static Response authAsAdmin() {
        return performPostRequestWithBody(setUpAdminCredentials(), API_LOGIN_USER_PATH);
    }

    public static Response authAsStudent() {
        return performPostRequestWithBody(setUpStudentCredentials(), API_LOGIN_USER_PATH);
    }

    public static Map<String, String> setUpAdminCredentials() {
        return setUpAuthRequestBody(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    public static Map<String, String> setUpStudentCredentials() {
        return setUpAuthRequestBody(STUDENT_LOGIN, STUDENT_PASSWORD);
    }

    public static Object getValueFromResponseBody(Response response, String key) {
        return response
                .getBody()
                .jsonPath()
                .get(key);
    }

    public static Cookie getSessionsCookie(Response response) {
        return response.getDetailedCookie("session_id");
    }

    private static Map<String, String> setUpAuthRequestBody(String username, String password) {
        return of("username", username, "password", password);
    }
}