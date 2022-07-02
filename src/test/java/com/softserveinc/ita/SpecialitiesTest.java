package com.softserveinc.ita;

import com.softserveinc.ita.apisteps.SpecialitiesApiStep;
import com.softserveinc.ita.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import io.restassured.http.Cookie;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.refresh;
import static com.softserveinc.ita.models.SpecialityEntity.getNewValidSpeciality;
import static com.softserveinc.ita.util.ApiUtil.*;
import static com.softserveinc.ita.util.AuthApiUtil.authAsAdmin;
import static com.softserveinc.ita.util.AuthApiUtil.getSessionsCookie;
import static com.softserveinc.ita.util.DataProvider.API_LOGOUT_PATH;
import static com.softserveinc.ita.util.DataProvider.SPECIALITIES_PAGE_URL;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialitiesTest extends TestRunner {

    private SpecialitiesPage specialitiesPage;
    private Cookie sessionId;
    private SpecialitiesApiStep specialitiesApiStep;
    private final List<SpecialityEntity> addedSpecialities = new ArrayList<>();

    @BeforeClass(groups = {"positive", "negative"})
    public void setUpSpecialitiesTest() {
        sessionId = getSessionsCookie(authAsAdmin());
        specialitiesApiStep = new SpecialitiesApiStep(sessionId);
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
        specialitiesApiStep.verifySpecialityNotExist(speciality);

        specialitiesStep.addNewSpeciality(speciality);

        var messageText = specialitiesPage.getMessageText();

        speciality = specialitiesApiStep.findSpeciality(speciality);
        addedSpecialities.add(speciality);

        var soft = getSoftAssert();
        soft.assertThat(messageText)
                .as("Message after adding should contain added speciality name")
                .contains(speciality.getName());

        var lastSpecialityCode = specialitiesPage.getLastSpecialityField("code");

        soft.assertThat(lastSpecialityCode)
                .as("After adding new speciality speciality with added code " +
                        "should be last in the table")
                .isEqualTo(speciality.getCode());

        soft.assertAll();
    }

    @Test(groups = "positive")
    @Description("Test to verify speciality is deleted")
    public void verifyDeletingSpeciality() {

        var speciality = specialitiesApiStep.createNewSpeciality();
        addedSpecialities.add(speciality);
        refresh();
        specialitiesPage.waitForProgressBarToDisappear();

        specialitiesStep.deleteSpeciality(speciality);

        specialitiesApiStep.verifySpecialityNotExist(speciality);

        var messageText = specialitiesPage.getMessageText();

        var soft = getSoftAssert();
        soft.assertThat(messageText)
                .as("Message after deleting should contain deleted speciality name")
                .contains(speciality.getName());

        var lastSpecialityCode = specialitiesPage.getLastSpecialityField("code");

        soft.assertThat(lastSpecialityCode)
                .as("After deleting speciality last speciality in the table " +
                        "should not have deleted code")
                .isNotEqualTo(speciality.getCode());

        soft.assertAll();
    }

    @Test(groups = "positive")
    @Description("Test to verify speciality is edited")
    public void verifyEditingSpeciality() {

        var speciality = specialitiesApiStep.createNewSpeciality();
        addedSpecialities.add(speciality);
        refresh();
        specialitiesPage.waitForProgressBarToDisappear();

        var previousName = speciality.getName();
        speciality.setName(previousName + "edited");

        specialitiesStep.editSpeciality(speciality);

        speciality = specialitiesApiStep.findSpeciality(speciality);
        addedSpecialities.add(speciality);

        var messageText = specialitiesPage.getMessageText();

        var soft = getSoftAssert();
        soft.assertThat(messageText)
                .as("Message after editing should contain speciality name before edit")
                .contains(previousName);

        var lastSpecialityName = specialitiesPage.getLastSpecialityField("name");

        soft.assertThat(lastSpecialityName)
                .as("After editing speciality last speciality in the table should have name after edit")
                .isEqualTo(speciality.getName());

        soft.assertAll();
    }

    @AfterClass
    public void tearDown() {
        for (SpecialityEntity speciality : addedSpecialities) {
            if (getSpecialityByID(speciality.getId(), sessionId) != null) {
                deleteSpeciality(sessionId, speciality);
            }
        }
        performGetRequest(sessionId, API_LOGOUT_PATH);
    }
}
