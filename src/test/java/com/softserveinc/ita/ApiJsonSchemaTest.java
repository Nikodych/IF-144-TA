package com.softserveinc.ita;

import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.ApiUtil.verifySchemaRecords;
import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.API_LOGOUT_PATH;

public class ApiJsonSchemaTest {

    private Cookie sessionId;

    @BeforeClass(groups = {"positive", "negative"})
    public void setUp() {
        sessionId = getSessionsCookie(authAsAdmin());
    }

    @Test(groups = "positive")
    @Description("Test to verify corectness of the json schema of tests entities")
    public void verifyTestEntitySchemaRecords() {
        verifySchemaRecords("test", "schemas/TestGetRecordsSchema200.json");
    }

    @AfterClass
    public void tearDown() {
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }
}