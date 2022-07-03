package com.softserveinc.ita.pageobjects.modals;

import com.softserveinc.ita.models.FormFields;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.requireNonNull;

public class AddingAndEditingFormModal {

    @Step("Form modal: Set {value} for {field}")
    public AddingAndEditingFormModal setValueFor(FormFields field, String value) {
        var fieldElement = field
                .getName()
                .should(appear, ofSeconds(5));

        var fieldClass = requireNonNull(fieldElement.getAttribute("class"));

        if (fieldClass.startsWith("mat-select")) { //it's dropdown menu, not input field
            fieldElement.click();

            $$x("//mat-option")
                    .find(exactText(value))
                    .should(exist)
                    .click();
        } else {
            // first click in field then send keys, as another element may be focused
            if (!fieldElement.is(focused)) {
                fieldElement.doubleClick(); // one click sometimes doesn't change the focus for some reason
            }

            fieldElement
                    .shouldBe(focused)
                    .clear();
            fieldElement.sendKeys(value);
        }

        return this;
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

    @Step("Form modal: Confirmed in modal window")
    public void confirmModal() {
        $x("//form//button[@type='submit' or ./span[contains(text(),'Додати')] or ./span[contains(text(),' Підтвердити ')] or ./span[contains(text(),'Confirm')]]")
                .should(appear, ofSeconds(5))
                .click();
    }

    @Step("Form modal: Canceled in modal window")
    public void cancelModal() {
        $x("//button/span[contains(text(), 'Відмінити')] " +
                "| //button/span[contains(text(), 'Скасувати')] " +
                "| //button/span[contains(text(), 'Cancel')] " +
                "| //mat-icon[contains(@class, 'closeForm')]")
                .should(appear, ofSeconds(5))
                .click();
    }
}
