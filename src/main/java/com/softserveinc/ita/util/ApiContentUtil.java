package com.softserveinc.ita.util;

import com.softserveinc.ita.models.SubjectEntity;
import com.softserveinc.ita.models.TimeTableEntity;
import io.restassured.http.Cookie;
import lombok.experimental.UtilityClass;

import java.util.List;

import static com.softserveinc.ita.util.ApiUtil.performGetRequest;
import static com.softserveinc.ita.util.DataProvider.API_ENTITY_GET_RECORDS_PATH;
import static java.lang.String.format;

@UtilityClass
public class ApiContentUtil {
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
}
