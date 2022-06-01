package com.softserveinc.ita.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.softserveinc.ita.pageobjects.util.DataProvider.API_BASE_URI;
import static io.restassured.RestAssured.given;

public class Api {

    public Response performPOSTWithBody(Map<String, ?> bodyContent, String basePath) {
        return setUpRequestSpecification().body(bodyContent).post(basePath);
    }

    public Response performGET(Cookie cookie, String basePath) {
        return setUpRequestSpecification().cookie(cookie).get(basePath);
    }

    private RequestSpecification setUpRequestSpecification() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(API_BASE_URI);
        builder.setContentType(ContentType.JSON);
        var requestSpecification = builder.build();

        return given().spec(requestSpecification);
    }
}
