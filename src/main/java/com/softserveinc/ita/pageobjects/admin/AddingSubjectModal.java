package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class AddingSubjectModal {

    @Step("Adding subject modal: Set subject title")
    public AddingSubjectModal setSubjectTitle(String title) {
        $x("//input[@formcontrolname='subject_name']")
                .should(appear, ofSeconds(5))
                .sendKeys(title);

        return this;
    }

    @Step("Adding subject modal: Set subject description")
    public AddingSubjectModal setSubjectDescription(String description) {
        $x("//textarea[@formcontrolname='subject_description']")
                .should(appear, ofSeconds(5))
                .sendKeys(description);

        return this;
    }

    public void addNewSubject() {
        $x("//button[@type='submit']").click();
    }

    public boolean isAddButtonEnabled() {
        return $x("//button[@type='submit']")
                .shouldBe(visible)
                .isEnabled();
    }
}
