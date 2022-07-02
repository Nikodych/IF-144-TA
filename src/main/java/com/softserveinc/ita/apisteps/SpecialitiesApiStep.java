package com.softserveinc.ita.apisteps;

import com.softserveinc.ita.models.SpecialityEntity;
import io.restassured.http.Cookie;
import lombok.AllArgsConstructor;

import static com.softserveinc.ita.util.ApiUtil.createSpeciality;
import static com.softserveinc.ita.util.ApiUtil.getSpecialitiesListByAPI;
import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class SpecialitiesApiStep {

    private Cookie sessionId;

    public SpecialityEntity createNewSpeciality() {
        var speciality = SpecialityEntity.getNewValidSpeciality();
        var responseSpeciality = createSpeciality(speciality);
        assertThat(responseSpeciality)
                .as("After adding speciality with API it should be returned in response")
                .hasSize(1)
                .contains(speciality);

        speciality = responseSpeciality.get(0); // in order to get known given id
        return speciality;
    }

    public SpecialityEntity findSpeciality(SpecialityEntity speciality) {
        var specialitiesList = getSpecialitiesListByAPI(sessionId);
        assertThat(specialitiesList)
                .as("List of specialities returned by API should contain speciality")
                .contains(speciality);
        //in order to get known given id
        return specialitiesList.get(specialitiesList.indexOf(speciality));
    }

    public void verifySpecialityNotExist(SpecialityEntity speciality) {
        var specialitiesList = getSpecialitiesListByAPI(sessionId);
        assertThat(specialitiesList)
                .as("List of specialities returned by API shouldn't contain speciality")
                .doesNotContain(speciality);
    }
}
