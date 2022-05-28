package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public abstract class AddingFormModal {

    public boolean isAddButtonEnabled() {
        return $x("//button[@type='submit']")
                .shouldBe(visible)
                .isEnabled();
    }

    public boolean isAddingFormDisplayed() {
        return $x("//mat-dialog-container")
                .should(visible, ofSeconds(5))
                .isDisplayed();
    }
}
