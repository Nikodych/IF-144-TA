package com.softserveinc.ita;

import com.codeborne.selenide.Selenide;
import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.sleep;
import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecialitiesTest extends TestRunner {

    SpecialitiesPage specialitiesPage;

    @BeforeMethod
    public void openSpecialitiesPage() {
        specialitiesPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openSpecialitiesPage();
    }

    @Test
    public void verifySpecialitiesPageOpening() {

        var expectedUrl = SPECIALITIES_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test
    public void verifyNewSpecialityIsAdded() {

        var randCode = new Random().nextInt(99999);
        var specialityCode = Integer.toString(randCode); // only numbers, no more than 5 symbols
        var specialityName = "test"+randCode;

        specialitiesPage.addNewSpeciality(specialityCode, specialityName);

        sleep(3000); // time for page to reload

        var lastSpecialityCode = specialitiesPage.getLastSpecialityCode();

        assertThat(lastSpecialityCode)
                .as("After adding new speciality speciality with added code " +
                        "should be last in the table")
                .isEqualTo(specialityCode);
    }
}
