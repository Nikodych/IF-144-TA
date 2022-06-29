package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.models.SpecialityEntity.getNewValidSpeciality;
import static com.softserveinc.ita.util.ApiUtil.getSpecialitiesListByAPI;
import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.SPECIALITIES_PAGE_URL;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialitiesTest extends TestRunner {

    private SpecialitiesPage specialitiesPage;
    private Cookie sessionId;

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpSpecialitiesTest() {
        sessionId = getSessionsCookie(authAsAdmin());
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSpecialitiesPage() {
        specialitiesStep.openPage();
        specialitiesPage = specialitiesStep.getPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify Specialities page opening")
    public void verifySpecialitiesPageOpening() {

        var expectedUrl = SPECIALITIES_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test(groups = "positive")
    @Description("Test to verify new speciality is added")
    public void verifyAddingNewSpeciality() {

        var speciality = getNewValidSpeciality();

        var specialitiesList = getSpecialitiesListByAPI(sessionId);
        assertThat(specialitiesList)
                .as("Before adding list of specialities shouldn't contain new speciality")
                .doesNotContain(speciality);

        specialitiesStep.addNewSpeciality(speciality);

        var soft = getSoftAssert();
        specialitiesList = getSpecialitiesListByAPI(sessionId);
        soft.assertThat(specialitiesList)
                .as("After adding list of specialities should contain new speciality")
                .contains(speciality);

        var messageText = specialitiesPage.getMessageText();

        soft.assertThat(messageText)
                .as("Message after adding should contain added speciality name")
                .contains(speciality.getName());

        var lastSpecialityCode = specialitiesPage.getLastSpecialityField("code");

        soft.assertThat(lastSpecialityCode)
                .as("After adding new speciality speciality with added code " +
                        "should be last in the table")
                .isEqualTo(speciality.getCode());

        soft.assertAll();

        specialitiesStep.deleteSpeciality(speciality);
    }

    @Test(groups = "positive")
    @Description("Test to verify speciality is deleted")
    public void verifyDeletingSpeciality() {

        var speciality = getNewValidSpeciality();

        specialitiesStep.addNewSpeciality(speciality);

        var specialitiesList = getSpecialitiesListByAPI(sessionId);
        assertThat(specialitiesList)
                .as("Before deleting list of specialities should contain recently added speciality")
                .contains(speciality);

        var lastSpecialityCode = specialitiesPage.getLastSpecialityField("code");

        assertThat(lastSpecialityCode)
                .as("Before deleting recently added speciality should be last in the table")
                .isEqualTo(speciality.getCode());

        specialitiesStep.deleteSpeciality(speciality);

        var soft = getSoftAssert();
        specialitiesList = getSpecialitiesListByAPI(sessionId);
        soft.assertThat(specialitiesList)
                .as("After deleting list of specialities shouldn't contain deleted speciality")
                .doesNotContain(speciality);

        var messageText = specialitiesPage.getMessageText();

        soft.assertThat(messageText)
                .as("Message after deleting should contain deleted speciality name")
                .contains(speciality.getName());

        lastSpecialityCode = specialitiesPage.getLastSpecialityField("code");

        soft.assertThat(lastSpecialityCode)
                .as("After deleting speciality last speciality in the table " +
                        "should not have deleted code")
                .isNotEqualTo(speciality.getCode());

        soft.assertAll();
    }

    @Test(groups = "positive")
    @Description("Test to verify speciality is edited")
    public void verifyEditingSpeciality() {

        var speciality = getNewValidSpeciality();

        specialitiesStep.addNewSpeciality(speciality);

        var specialitiesList = getSpecialitiesListByAPI(sessionId);
        assertThat(specialitiesList)
                .as("Before editing list of specialities should contain recently added speciality")
                .contains(speciality);

        var lastSpecialityName = specialitiesPage.getLastSpecialityField("name");

        assertThat(lastSpecialityName)
                .as("Before editing recently added speciality should be last in the table")
                .isEqualTo(speciality.getName());

        var previousName = speciality.getName();
        speciality.setName(previousName + "edited");

        specialitiesStep.editSpeciality(speciality);

        var soft = getSoftAssert();
        specialitiesList = getSpecialitiesListByAPI(sessionId);
        soft.assertThat(specialitiesList)
                .as("After editing list of specialities should contain changed speciality")
                .contains(speciality);

        var messageText = specialitiesPage.getMessageText();

        soft.assertThat(messageText)
                .as("Message after editing should contain speciality name before edit")
                .contains(previousName);

        lastSpecialityName = specialitiesPage.getLastSpecialityField("name");

        soft.assertThat(lastSpecialityName)
                .as("After editing speciality last speciality in the table should have name after edit")
                .isEqualTo(speciality.getName());

        soft.assertAll();

        specialitiesStep.deleteSpeciality(speciality);
    }
}
