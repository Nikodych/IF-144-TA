package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.pageobjects.util.DataProvider.*;
import static java.lang.String.*;
import static java.time.Duration.ofSeconds;

public abstract class AddingFormModal {

    public SelenideElement inputField(String formControlName) {
        return $x(format(INPUT_TEMPLATE, formControlName));
    }

    public SelenideElement textAreaField(String formControlName) {
        return $x(format(TEXTAREA_TEMPLATE, formControlName));
    }

    public SelenideElement matSelectField(String formControlName) {
        return $x(format(MAT_SELECT_TEMPLATE, formControlName));
    }

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
