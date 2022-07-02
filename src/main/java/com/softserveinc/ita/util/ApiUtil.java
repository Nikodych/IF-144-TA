package com.softserveinc.ita.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.softserveinc.ita.models.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.google.gson.JsonParser.parseString;
import static com.softserveinc.ita.util.DataProvider.*;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static java.lang.String.format;
import static java.util.Map.of;
import static java.util.Objects.nonNull;

@UtilityClass
public class ApiUtil {

    public static Response performPostRequestWithBody(Object bodyContent, String basePath) {
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
        return getEntitiesListByAPI(sessionId, SubjectEntity.class);
    }

    public static List<SubjectEntity> postSubject(SubjectEntity subject) {
        var bodyContent = setUpSubjectBody(subject);
        return createEntity(bodyContent, SubjectEntity.class);
    }

    public static List<SpecialityEntity> createSpeciality(SpecialityEntity speciality) {
        var bodyContent = setUpSpecialityBody(speciality);
        return createEntity(bodyContent, SpecialityEntity.class);
    }

    public static List<FacultyEntity> createFaculty(FacultyEntity faculty) {
        var bodyContent = setUpFacultyBody(faculty);
        return createEntity(bodyContent, FacultyEntity.class);
    }

    public static List<GroupEntity> createGroup(GroupEntity group, Cookie sessionId) {
        var bodyContent = setUpGroupBody(group);
        var groups = createEntity(bodyContent, GroupEntity.class);
        fillConnectedEntitiesDataForGroupsList(sessionId, groups);

        return groups;
    }

    public static Response deleteSubject(Cookie sessionId, SubjectEntity subject) {
        return deleteEntity(subject.getId(), sessionId, subject.getClass());
    }

    public static Response deleteSpeciality(Cookie sessionId, SpecialityEntity speciality) {
        return deleteEntity(speciality.getId(), sessionId, speciality.getClass());
    }

    public static Response deleteFaculty(Cookie sessionId, FacultyEntity faculty) {
        return deleteEntity(faculty.getId(), sessionId, faculty.getClass());
    }

    public static Response deleteGroup(Cookie sessionId, GroupEntity group) {
        return deleteEntity(group.getId(), sessionId, group.getClass());
    }

    public static List<TimeTableEntity> getTimeTablesListByAPI(Cookie sessionId) {
        return getEntitiesListByAPI(sessionId, TimeTableEntity.class);
    }

    public static List<TimeTableEntity> postTimeTable(TimeTableEntity timeTable) {
        var bodyContent = setUpTimeTableBody(timeTable);
        return createEntity(bodyContent, TimeTableEntity.class);
    }

    public static List<TimeTableEntity> updateTimeTable(TimeTableEntity timeTable) {
        var path = getPathStringByEntityClass(API_ENTITY_UPDATE_RECORDS_PATH, TimeTableEntity.class);
        path = format(path, timeTable.getId());
        var response = performPostRequestWithBody(setUpTimeTableBody(timeTable), path);

        return extractFromJson(response).getList("", TimeTableEntity.class);
    }

    public static Response deleteTimeTable(Cookie sessionId, TimeTableEntity timeTable) {
        return deleteEntity(timeTable.getId(), sessionId, timeTable.getClass());
    }

    public static List<SpecialityEntity> getSpecialitiesListByAPI(Cookie sessionId) {
        return getEntitiesListByAPI(sessionId, SpecialityEntity.class);
    }

    public static List<GroupEntity> getGroupsListByAPI(Cookie sessionId) {
        var groups = getEntitiesListByAPI(sessionId, GroupEntity.class);
        fillConnectedEntitiesDataForGroupsList(sessionId, groups);

        return groups;
    }

    public static List<TestEntity> getTestsListByAPI(Cookie sessionId) {
        return getEntitiesListByAPIGson(sessionId, TestEntity.class);
    }

    public static <T> void verifySchemaRecords(Class<T> genericType, String filePath) {
        given()
                .filter(new AllureRestAssured())
                .get(API_BASE_URI + getPathStringByEntityClass(API_ENTITY_GET_RECORDS_PATH, genericType))
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(filePath));
    }

    public static SpecialityEntity getSpecialityByID(String id, Cookie sessionId) {
        return getEntityByAPI(id, sessionId, SpecialityEntity.class);
    }

    public static GroupEntity getGroupByID(String id, Cookie sessionId) {
        return getEntityByAPI(id, sessionId, GroupEntity.class);
    }

    private static void fillConnectedEntitiesDataForGroupsList(Cookie sessionId, List<GroupEntity> groups) {
        Map<String, SpecialityEntity> specialitiesCache = new HashMap<>();
        Map<String, FacultyEntity> facultiesCache = new HashMap<>();
        String specialityId;
        String facultyId;

        for (var group : groups) {
            if (nonNull(group.getSpeciality())) {
                specialityId = group
                        .getSpeciality()
                        .getId();

                if (nonNull(specialityId)) {
                    var speciality = specialitiesCache.computeIfAbsent(specialityId,
                            k -> getEntityByAPI(k, sessionId, SpecialityEntity.class));
                    group.setSpeciality(speciality);
                }
            }

            if (nonNull(group.getFaculty())) {
                facultyId = group
                        .getFaculty()
                        .getId();

                if (nonNull(facultyId)) {
                    var faculty = facultiesCache.computeIfAbsent(facultyId,
                            k -> getEntityByAPI(k, sessionId, FacultyEntity.class));
                    group.setFaculty(faculty);
                }
            }
        }
    }

    private static <T> List<T> createEntity(Object bodyContent, Class<T> genericType) {
        var path = getPathStringByEntityClass(API_ENTITY_POST_RECORDS_PATH, genericType);
        var response = performPostRequestWithBody(bodyContent, path);

        return extractFromJson(response).getList("", genericType);
    }

    private static <T> Response deleteEntity(String id, Cookie sessionId, Class<T> genericType) {
        var path = getPathStringByEntityClass(API_ENTITY_DELETE_RECORDS_PATH, genericType);
        path = format(path, id);

        return performGetRequest(sessionId, path);
    }

    private static <T> List<T> getEntitiesListByAPI(Cookie sessionId, Class<T> genericType) {
        var path = getPathStringByEntityClass(API_ENTITY_GET_RECORDS_PATH, genericType);
        var response = performGetRequest(sessionId, path);

        return extractFromJson(response).getList("", genericType);
    }

    private static <T> T getEntityByAPI(String id, Cookie sessionId, Class<T> genericType) {
        var path = getPathStringByEntityClass(API_ENTITY_GET_RECORD_PATH, genericType);
        path = format(path, id);
        var response = performGetRequest(sessionId, path);
        var responseValue = response
                .getBody()
                .jsonPath()
                .get("response");

        if (nonNull(responseValue) && responseValue.equals("no records")) {
            return null;
        } else {
            var list = extractFromJson(response).getList("", genericType);
            return list.get(0);
        }
    }

    private static <T> String getPathStringByEntityClass(String pathTemplate, Class<T> genericType) {
        var classname = genericType
                .getSimpleName()
                .replace("Entity", "");

        return pathTemplate.replace("{Entity}", classname);
    }

    private static <T> LinkedList<T> getEntitiesListByAPIGson(Cookie sessionId, Class<T> genericType) {
        var path = getPathStringByEntityClass(API_ENTITY_GET_RECORDS_PATH, genericType);
        var response = getJsonArrayFromGetRequest(sessionId, path);
        var list = new LinkedList<T>();

        response.forEach(item -> list.add(new Gson().fromJson(item, genericType)));

        return list;
    }

    private JsonArray getJsonArrayFromGetRequest(Cookie sessionId, String path) {
        return parseString(performGetRequest(sessionId, path)
                .then()
                .extract()
                .body()
                .asString())
                .getAsJsonArray();
    }

    private Map<String, String> setUpSubjectBody(SubjectEntity subject) {
        return of("subject_name", subject.getName(), "subject_description", subject.getDescription());
    }

    private String setUpSpecialityBody(SpecialityEntity speciality) {
        try {
            return new ObjectMapper().writeValueAsString(speciality);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    private String setUpFacultyBody(FacultyEntity faculty) {
        try {
            return new ObjectMapper().writeValueAsString(faculty);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    private String setUpGroupBody(GroupEntity group) {
        try {
            return new ObjectMapper().writeValueAsString(group);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    private Map<String, String> setUpTimeTableBody(TimeTableEntity timeTable) {
        return of("group_id", timeTable.getGroupId(), "subject_id", timeTable.getSubjectId(),
                "start_date", timeTable.getStartDate(), "start_time", timeTable.getStartTime(),
                "end_date", timeTable.getEndDate(), "end_time", timeTable.getEndTime());
    }

    private JsonPath extractFromJson(Response response) {
        return response
                .then()
                .extract()
                .jsonPath();
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
