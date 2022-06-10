package com.softserveinc.ita.pageobjects.modals;

import com.softserveinc.ita.pageobjects.admin.TestsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class AddingTestModal {

    @Step("Adding test modal form: Set test`s name to {testName}")
    public AddingTestModal setTestName(String testName) {
        setInputField(1, testName);

        return this;
    }

    @Step("Adding test modal form: Set test`s subject to {testSubject}")
    public AddingTestModal setTestSubject(String testSubject) {
        $x(" //mat-select[@name='subject_id']").click();
        $x(format("//mat-option/span[contains(text(),'%s')]", testSubject))
                .should(appear, ofSeconds(5))
                .click();

        return this;
    }

    @Step("Adding test modal form: Set test`s number of tasks to {numberOfTasks}")
    public AddingTestModal setNumberOfTasks(String numberOfTasks) {
        setInputField(3, numberOfTasks);

        return this;
    }

    @Step("Adding test modal form: Set test`s amount of time (in minutes) to {amountOfTimes}")
    public AddingTestModal setAmountOfTime(String amountOfTimes) {
        setInputField(4, amountOfTimes);

        return this;
    }

    @Step("Adding test modal form: Set test`s amount of attempts to {numberOfAttempts}")
    public AddingTestModal setNumberOfAttempts(String numberOfAttempts) {
        setInputField(5, numberOfAttempts);

        return this;
    }

    @Step("Adding test modal form: Set test`s state as \"Включений\" ")
    public AddingTestModal setStateAsTurnedOn() {
        $x("//mat-radio-button[1]").click();

        return this;
    }

    @Step("Adding test modal form: Set test`s state as \"Виключений\" ")
    public AddingTestModal setStateAsTurnedOff() {
        $x("//mat-radio-button[2]").click();

        return this;
    }

    @Step("Adding test modal form: Added test")
    public TestsPage addSubjectTest() {
        $x("//app-test-add//button/span[contains(text(),'Додати')]").click();

        return new TestsPage();
    }

    private void setInputField(int index, String value) {
        String INPUT_FIELD_TEMPLATE = "//mat-form-field[%s]//input";
        $x(format(INPUT_FIELD_TEMPLATE,index)).setValue(value);
    }
}