package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.softserveinc.ita.pageobjects.util.DataProvider.FACULTY_DESCRIPTION;
import static com.softserveinc.ita.pageobjects.util.DataProvider.FACULTY_NAME;
import static java.time.Duration.ofSeconds;

public class AddingFacultyModal extends AddingFormModal {

    @Step("Adding faculty modal: Set faculty title")
    public AddingFacultyModal setFacultyTitle(String title) {
        inputField(FACULTY_NAME)
                .should(appear, ofSeconds(5))
                .sendKeys(title);

        return this;
    }

    @Step("Adding faculty modal: Set faculty description")
    public AddingFacultyModal setFacultyDescription(String description) {
        textAreaField(FACULTY_DESCRIPTION)
                .should(appear, ofSeconds(5))
                .sendKeys(description);

        return this;
    }
}
