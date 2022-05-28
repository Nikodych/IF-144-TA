package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class AddingFacultyModal extends AddingFormModal {

    @Step("Adding faculty modal: Set faculty title")
    public AddingFacultyModal setFacultyTitle(String title) {
        $x("//input[@formcontrolname='faculty_name']")
                .should(appear, ofSeconds(5))
                .sendKeys(title);

        return this;
    }

    @Step("Adding faculty modal: Set faculty description")
    public AddingFacultyModal setFacultyDescription(String description) {
        $x("//textarea[@formcontrolname='faculty_description']")
                .should(appear, ofSeconds(5))
                .sendKeys(description);

        return this;
    }
}
