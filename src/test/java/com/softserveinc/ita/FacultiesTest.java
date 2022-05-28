package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.FacultiesPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class FacultiesTest extends TestRunner {

    private FacultiesPage facultiesPage;

    @BeforeMethod
    public void openFacultiesPage() {
        facultiesPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openFacultiesPage();
    }

    @Test
    @Description("Test to verify Faculties page opening")
    public void verifyFacultiesPageOpening() {

        var expectedUrl = FACULTIES_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test(dataProvider = "searchValues")
    public void verifySearchFieldWorksWithValidInput(String value, String expectedValue) {
        var listOfFaculties = facultiesPage
                .performSearch(value)
                .getFaculties();

        assertThat(listOfFaculties)
                .as("Search of the faculty performs if valid faculty name or description is entered")
                .allMatch(it -> it.contains(expectedValue));
    }

    @Test
    public void verifySearchFieldDoesntWorkWithInvalidInput() {
        var numberOfFaculties = facultiesPage
                .performSearch("#@&$%^")
                .getFaculties()
                .size();

        assertThat(numberOfFaculties)
                .as( "Search of the faculty doesn't perform if invalid data is entered")
                .isEqualTo(0);
    }

    @Test
    public void verifyAddingFacultyFormIsDisplayed() {
        var actualResult = facultiesPage
                .openAddingFacultyForm()
                .isAddingFormDisplayed();

        assertThat(actualResult)
                .as("Adding faculty form should appear after clicking on 'Add faculty' button")
                .isTrue();
    }

    @Test
    public void verifySubmitButtonIsEnabledWithValidData() {
        var actualResult = openAndFillSubjectFields("факультет", "опис факультету");

        assertThat(actualResult)
                .as("When both fields filled with valid data 'Add faculty' button should be enabled")
                .isTrue();
    }

    @Test
    public void verifyAddFacultyButtonIsNotEnabledWithInvalidTitle() {
        var actualResult = openAndFillSubjectFields("?невалідний факультет", "опис факультету");

        assertThat(actualResult)
                .as("When name of faculty is invalid 'Add faculty' button shouldn't be enabled.")
                .isFalse();

    }

    @Test
    public void verifyAddFacultyButtonIsNotEnabledWithInvalidDescription() {
        var actualResult = openAndFillSubjectFields("факультет", "опиs факультету");

        assertThat(actualResult)
                .as("When description is invalid 'Add faculty' button shouldn't be enabled.")
                .isFalse();

    }

    private boolean openAndFillSubjectFields(String title, String description) {
        return facultiesPage
                .openAddingFacultyForm()
                .setFacultyTitle(title)
                .setFacultyDescription(description)
                .isAddButtonEnabled();
    }

    @DataProvider(name = "searchValues")
    public static Object[][] inputData() {
        return new Object[][]{{"факультет справжніх", "Інститут інформаційних технологій"},
                {"Інститут інформаційних технологій", "Інститут інформаційних технологій"}};
    }
}