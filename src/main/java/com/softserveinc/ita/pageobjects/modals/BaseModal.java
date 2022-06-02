package com.softserveinc.ita.pageobjects.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.softserveinc.ita.pageobjects.util.DataProvider.INPUT_TEMPLATE;
import static com.softserveinc.ita.pageobjects.util.DataProvider.TEXTAREA_TEMPLATE;
import static java.lang.String.format;
import static java.time.Duration.*;

public class BaseModal<T extends BaseModal<T>> {
    @Step("Adding form modal: Set {formControlName}")
    public T setValueFor(String formControlName, String value) {
        $x(format(INPUT_TEMPLATE, formControlName))
                .should(appear, ofSeconds(5))
                .sendKeys(value);

        return (T) this;
    }

    @Step("Adding form modal: Set {formControlName}")
    public T setDescriptionFor(String formControlName, String description) {
        $x(format(TEXTAREA_TEMPLATE, formControlName))
                .should(appear, ofSeconds(5))
                .sendKeys(description);

        return (T) this;
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

    @Step("Adding form modal: added new item.")
    public void addNewItem() {
        $x("//button[@type='submit']").click();
    }
}
