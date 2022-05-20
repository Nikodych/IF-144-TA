package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.FacultiesPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class FacultiesTest extends TestRunner {
    FacultiesPage facultiesPage;

    @BeforeMethod
    public void openFacultiesPage() {
        facultiesPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openFacultiesPage();
    }

    @Test(dataProvider = "searchValues")
    public void searchFieldWorksWithValidInput(String value, String expectedValue) {
        var listOfFaculties = facultiesPage
                .setValueInTheSearchField(value)
                .getFaculties();

        assertTrue(listOfFaculties
                .stream()
                .allMatch(it -> it.contains(expectedValue)));
    }

    @Test
    public void verifySearchFieldDoesntWorkWithInvalidInput() {
        var numberOfFaculties = facultiesPage
                .setValueInTheSearchField(INVALID)
                .getFaculties()
                .size();

        assertThat(numberOfFaculties).isEqualTo(0);
    }

    @DataProvider(name = "searchValues")
    public static Object[][] inputData() {
        return new Object[][]{{DESCRIPTION, "Інститут інформаційних технологій"},
                {FACULTY, "Інститут інформаційних технологій"}};
    }
}
