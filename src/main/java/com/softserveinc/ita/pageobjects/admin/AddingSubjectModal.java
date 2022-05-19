package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AddingSubjectModal {

    public AddingSubjectModal writeSubjectTitle() {
        $x("//input[@formcontrolname='subject_name']").sendKeys("Предмет");

        return this;
    }

    public AddingSubjectModal writeSubjectDescription() {
        $x("//textarea[@formcontrolname='subject_description']").sendKeys("Опис предмету");

        return this;
    }

    public boolean isAddButtonEnabled() {
        return $x("//button[@type='submit']")
                .shouldBe(visible)
                .isEnabled();
    }
}
