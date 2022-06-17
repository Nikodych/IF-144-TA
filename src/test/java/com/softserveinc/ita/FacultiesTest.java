package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.FacultiesPage;
import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import com.softserveinc.ita.steps.FacultyStep;
import com.softserveinc.ita.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserveinc.ita.repos.FacultyRepo.*;
import static com.softserveinc.ita.util.DataProvider.*;
import static com.softserveinc.ita.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class FacultiesTest extends TestRunner {

    private FacultiesPage facultiesPage;
    private final FacultyStep facultyStep = new FacultyStep();
    private final AddingFormModal addingFacultyForm = new AddingFormModal();

    @BeforeMethod(groups = {"positive", "negative"})
    public void openFacultiesPage() {
        facultiesPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openFacultiesPage();
    }

    @Test(groups = "positive")
    @Description("Test to verify Faculties page opening")
    public void verifyFacultiesPageOpening() {

        var expectedUrl = FACULTIES_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test(groups = "positive", dataProvider = "validInputData")
    @Description("Test to verify search field on the faculties page should work with valid input")
    public void verifySearchFieldWorksWithValidInput(String value, String expectedValue) {
        var listOfFaculties = facultiesPage
                .performSearch(value)
                .getFaculties();

        assertThat(listOfFaculties)
                .as("Search of the faculty performs if valid faculty name or description is entered")
                .allMatch(it -> it.contains(expectedValue));
    }

    @Test(groups = "negative", dataProvider = "invalidInputData")
    @Description("Test to verify search field on the faculties page should not work with invalid input")
    public void verifySearchFieldDoesntWorkWithInvalidInput(String value, int expectedValue) {
        var numberOfFaculties = facultiesPage
                .performSearch(value)
                .getFaculties()
                .size();

        assertThat(numberOfFaculties)
                .as("Search of the faculty doesn't perform if invalid data is entered")
                .isEqualTo(expectedValue);
    }

    @Test(groups = "positive")
    @Description("Test to verify adding faculty form appears after pressing on add faculty button")
    public void verifyAddingFacultyFormIsDisplayed() {
        var actualResult = facultiesPage
                .openAddingNewForm()
                .isAddingFormDisplayed();

        assertThat(actualResult)
                .as("Adding faculty form should appear after clicking on 'Add faculty' button")
                .isTrue();
    }

    @Test(groups = "positive")
    @Description("Test to verify submit button is enabled when both fields filled with valid data")
    public void verifySubmitButtonIsEnabledWithValidData() {
        facultyStep.openAndFillFacultyFields(getValidFaculty());

        assertThat(addingFacultyForm.isAddButtonEnabled())
                .as("When both fields filled with valid data 'Add faculty' button should be enabled")
                .isTrue();
    }

    @Test(groups = "negative")
    @Description("Test to verify submit button is not enabled when name of faculty is invalid")
    public void verifySubmitButtonIsNotEnabledWithInvalidTitle() {
        facultyStep.openAndFillFacultyFields(getFacultyWithInvalidName());

        assertThat(addingFacultyForm.isAddButtonEnabled())
                .as("When name of faculty is invalid 'Add faculty' button shouldn't be enabled.")
                .isFalse();
    }

    @Test(groups = "negative")
    @Description("Test to verify submit button is not enabled when description of faculty is invalid")
    public void verifyAddFacultyButtonIsNotEnabledWithInvalidDescription() {
        facultyStep.openAndFillFacultyFields(getFacultyWithInvalidDescription());

        assertThat(addingFacultyForm.isAddButtonEnabled())
                .as("When description is invalid 'Add faculty' button shouldn't be enabled.")
                .isFalse();
    }

    @DataProvider(name = "validInputData")
    public static Object[][] validInputData() {
        return new Object[][]{{"факультет справжніх", "Інститут інформаційних технологій"},
                {"Інститут інформаційних технологій", "Інститут інформаційних технологій"}};
    }

    @DataProvider(name = "invalidInputData")
    public Object[][] invalidInputData() {
        return new Object[][]{{"#@&$%^", 0},
                {"123", 0},
                {"asdf", 0},
                {"неіснуючий факультет", 0}};
    }
}