package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.pageobjects.modals.AddingTestModal;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class TestsPage extends MainMenu<TestsPage> {

    @Step("Subject's Tests page: Opened adding test form")
    public AddingTestModal openAddingTestForm() {
        $x("//button[@aria-label='add']").click();

        return new AddingTestModal();
    }

    public boolean isExpectedSubjectTestsPage(String subject) {
        return $x(format("//h3/span[contains(text(),'%s')]", subject))
                .should(appear, ofSeconds(5))
                .exists();
    }

    public List<String> getNamesOfTests() {
        return $$x("//td[contains(@class, 'mat-column-name')]").texts();
    }

    public boolean isExpectedNameOfTestFound(String expectedNameOfTest) {
        return getNamesOfTests().contains(expectedNameOfTest);
    }
}