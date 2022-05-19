package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.FacultiesPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class FacultiesTest extends TestRunner {
    FacultiesPage facultiesPage;

    @BeforeMethod
    public void openFacultiesPage() {
        facultiesPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openFacultiesPage();
    }

    @Test
    public void verifyFacultiesPageOpening() {
        var expectedUrl = FACULTIES_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }

    @Test
    public void verifySearchFieldFindsByTheFacultyName() {
        var listOfFaculties = facultiesPage
                .fillSearchField("Інститут інформаційних технологій")
                .getFaculties();

        assertThat(listOfFaculties
                .stream()
                .allMatch(it -> it.contains("Інститут інформаційних технологій")));
    }

    @Test
    public void searchFieldFindsByDescription() {
        var listOfFaculties = facultiesPage
                .fillSearchField("факультет справжніх")
                .getFaculties();

        assertThat(listOfFaculties
                .stream()
                .allMatch(it -> it.contains("факультет справжніх")));
    }

    @Test
    public void searchFieldFindsNothingWithNonValidValue(){

    }
}
