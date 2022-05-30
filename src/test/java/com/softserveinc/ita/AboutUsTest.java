package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.LoginPage;
import com.softserveinc.ita.pageobjects.admin.AboutUsPage;
import com.softserveinc.ita.pageobjects.util.TestRunner;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class AboutUsTest extends TestRunner {

    private AboutUsPage aboutUsPage;

    @BeforeMethod (groups = {"positive", "negative"})
    public void openAboutUsPage() {
        aboutUsPage = new LoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .openAboutUsPage();
    }

    @Test (groups = "positive")
    @Description("Test to verify About Us page opening")
    public void verifyAboutUsPageOpening() {

        var expectedUrl = ABOUT_US_PAGE_URL;
        var currentUrl = getCurrentUrl();

        assertThat(currentUrl)
                .as("Page url should be " + expectedUrl)
                .isEqualTo(expectedUrl);
    }
}
