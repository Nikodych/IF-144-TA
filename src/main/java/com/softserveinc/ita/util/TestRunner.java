package com.softserveinc.ita.util;

import com.softserveinc.ita.steps.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.softserveinc.ita.util.DataProvider.LOGIN_PAGE_URL;
import static com.softserveinc.ita.util.SelenoidDriverProvider.setUpBrowser;
import static com.softserveinc.ita.util.SystemPropertyHelper.isRemote;
import static java.time.Duration.*;

public abstract class TestRunner {
    public final StudentsStep studentsStep = new StudentsStep();
    public final FacultyStep facultyStep = new FacultyStep();
    public final GroupsStep groupsStep = new GroupsStep();
    public final SpecialitiesStep specialitiesStep = new SpecialitiesStep();
    public final SubjectStep subjectStep = new SubjectStep();
    public final TestsSteps testsSteps = new TestsSteps();

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass(groups = {"positive", "negative"})
    public void setUp(@Optional("chrome") String browserName, @Optional("101") String browserVersion) {
        if (isRemote()) {
            setUpBrowser(browserName, browserVersion);
            browser = SelenoidDriverProvider.class.getName();
        } else {
            browser = "chrome";
            browserSize = "1920x1080";
            pageLoadTimeout = ofSeconds(10).toMillis();
            timeout = ofSeconds(10).toMillis();
        }

        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeMethod(groups = {"positive", "negative"})
    public void openLoginPage() {
        open(LOGIN_PAGE_URL);
    }

    public static SoftAssertions getSoftAssert() {
        return new SoftAssertions();
    }
}
