package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AddingSubjectModal {

    @Step("Adding subject modal: Set subject title")
    public AddingSubjectModal setSubjectTitle(String title) {
        $x("//input[@formcontrolname='subject_name']").sendKeys(title);

        return this;
    }

    @Step("Adding subject modal: Set subject description")
    public AddingSubjectModal setSubjectDescription(String description) {
        $x("//textarea[@formcontrolname='subject_description']").sendKeys(description);

        return this;
    }

    public boolean isAddButtonEnabled() {
        return $x("//button[@type='submit']")
                .shouldBe(visible)
                .isEnabled();
    }
}
