package com.softserveinc.ita.util;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.models.TimeTableEntity;
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

import java.util.List;
import java.util.Map;

import static com.softserveinc.ita.util.DataProvider.API_BASE_URI;
import static com.softserveinc.ita.util.DataProvider.API_ENTITY_GET_RECORDS_PATH;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

@UtilityClass
public class ApiUtil {

    public static Response performPostRequestWithBody(Map<String, String> bodyContent, String basePath) {
        setUpApiSpecifications();

        return given()
                .body(bodyContent)
                .post(basePath);
    }

    public static Response performGetRequest(Cookie cookie, String basePath) {
        setUpApiSpecifications();

        return given()
                .cookie(cookie)
                .get(basePath);
    }

    public static List<SubjectEntity> getSubjectsListByAPI(Cookie sessionId) {
        var path = format(API_ENTITY_GET_RECORDS_PATH, "Subject");
        var response = performGetRequest(sessionId, path);

        return response
                .then()
                .extract()
                .jsonPath()
                .getList("", SubjectEntity.class);
    }

    public static List<TimeTableEntity> getTimeTablesListByAPI(Cookie sessionId) {
        var path = format(API_ENTITY_GET_RECORDS_PATH, "TimeTable");
        var response = performGetRequest(sessionId, path);

        return response
                .then()
                .extract()
                .jsonPath()
                .getList(".", TimeTableEntity.class);
    }

    private void setUpApiSpecifications() {
        requestSpecification = getRequestSpecification();
        responseSpecification = getResponseSpecification();
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
