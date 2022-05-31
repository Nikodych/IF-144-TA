package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import com.softserveinc.ita.pageobjects.models.SpecialityEntity;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialitiesTest extends TestRunner {

    private SpecialitiesPage specialitiesPage;

    @BeforeMethod(groups = {"positive", "negative"})
    public void openSpecialitiesPage() {
        specialitiesPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSpecialitiesPage();
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

        var speciality = new SpecialityEntity();

        var messageText = specialitiesPage
                .addNewSpeciality(speciality)
                .getMessageText();

        assertThat(messageText)
                .as("Message after adding should contain added speciality name")
                .contains(speciality.getName());

        var lastSpecialityCode = specialitiesPage
                .waitForProgressBarToDisappear()
                .getLastSpecialityCode();

        assertThat(lastSpecialityCode)
                .as("After adding new speciality speciality with added code " +
                        "should be last in the table")
                .isEqualTo(speciality.getCode());

        specialitiesPage.deleteSpeciality(speciality);
    }

    @Test(groups = "positive")
    @Description("Test to verify speciality is deleted")
    public void verifyDeletingLastSpeciality() {

        var speciality = new SpecialityEntity();

        var lastSpecialityCode = specialitiesPage
                .addNewSpeciality(speciality)
                .waitForProgressBarToAppear()
                .waitForProgressBarToDisappear()
                .getLastSpecialityCode();

        assertThat(lastSpecialityCode)
                .as("After adding new speciality speciality with added code " +
                        "should be last in the table")
                .isEqualTo(speciality.getCode());

        var messageText = specialitiesPage
                .deleteSpeciality(speciality)
                .waitForProgressBarToAppear()
                .getMessageText();

        assertThat(messageText)
                .as("Message after deleting should contain deleted speciality name")
                .contains(speciality.getName());

        lastSpecialityCode = specialitiesPage
                .waitForProgressBarToDisappear()
                .getLastSpecialityCode();

        assertThat(lastSpecialityCode)
                .as("After deleting speciality last speciality in the table " +
                        "should not have deleted code")
                .isNotEqualTo(speciality.getCode());
    }
}
