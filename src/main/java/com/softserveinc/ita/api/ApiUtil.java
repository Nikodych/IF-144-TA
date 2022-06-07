package com.softserveinc.ita.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import java.util.Map;

import static com.softserveinc.ita.pageobjects.util.DataProvider.API_BASE_URI;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@UtilityClass
public class ApiUtil {

    public void setUpApiSpecifications() {
        requestSpecification = getRequestSpecification();
        responseSpecification = getResponseSpecification();
    }

    public Response performPostRequestWithBody(Map<String, String> bodyContent, String basePath) {
        setUpApiSpecifications();

        return given()
                .body(bodyContent)
                .post(basePath);
    }

    public Response performGetRequest(Cookie cookie, String basePath) {
        setUpApiSpecifications();

        return given()
                .cookie(cookie)
                .get(basePath);
    }

    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(API_BASE_URI)
                .setContentType(JSON)
                .setContentType(JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured())
                .build();
    }

    private ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(JSON)
                .build();
    }
}
