package com.softserveinc.ita.apisteps;

import com.softserveinc.ita.models.FacultyEntity;
import io.restassured.http.Cookie;
import lombok.AllArgsConstructor;

import static com.softserveinc.ita.repos.FacultyRepo.getValidRandomFaculty;
import static com.softserveinc.ita.util.ApiUtil.createFaculty;
import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class FacultyApiStep {

    private Cookie sessionId;

    public FacultyEntity createNewFaculty() {
        var faculty = getValidRandomFaculty();
        var responseFaculty = createFaculty(faculty);

        assertThat(responseFaculty)
                .as("After adding faculty with POST API it should be returned in response")
                .hasSize(1)
                .contains(faculty);

        return responseFaculty.get(0); // in order to get known given id
    }
}
