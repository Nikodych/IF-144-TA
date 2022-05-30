package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.softserveinc.ita.pageobjects.util.DataProvider.SUBJECT_DESCRIPTION;
import static com.softserveinc.ita.pageobjects.util.DataProvider.SUBJECT_NAME;
import static java.time.Duration.ofSeconds;

public class AddingSubjectModal extends AddingFormModal {

    @Step("Adding subject modal: Set subject title")
    public AddingSubjectModal setSubjectTitle(String title) {
        inputField(SUBJECT_NAME)
                .should(appear, ofSeconds(5))
                .sendKeys(title);

        return this;
    }

    @Step("Adding subject modal: Set subject description")
    public AddingSubjectModal setSubjectDescription(String description) {
        textAreaField(SUBJECT_DESCRIPTION)
                .should(appear, ofSeconds(5))
                .sendKeys(description);

        return this;
    }
}
