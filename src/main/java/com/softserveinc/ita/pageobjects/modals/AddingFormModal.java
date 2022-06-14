package com.softserveinc.ita.pageobjects.modals;

import com.codeborne.selenide.SelenideElement;
import com.softserveinc.ita.models.AddingFormFields;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class AddingFormModal {

    private final SelenideElement progressBar = $x("//mat-progress-bar");

    @Step("Adding form modal: Set {value} for {field}")
    public AddingFormModal setValueFor(AddingFormFields field, String value) {
        field
                .getName()
                .should(appear, ofSeconds(5))
                .sendKeys(value);

        return this;
    }
    //TODO: choose options in group adding form: specialty id, faculty id

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

    @Step("Adding form modal: Confirmed in modal window")
    public void confirmModal() {
        $x("//button[@type='submit' or ./span[contains(text(),'Додати')] or ./span[contains(text(),' Підтвердити ')] or ./span[contains(text(),'Confirm')]]")
                .should(appear, ofSeconds(5))
                .click();
        waitTillProgressbarDisappear();
    }

    @Step("Adding form modal: Canceled in modal window")
    public void cancelModal() {
        $x("//button/span[contains(text(), 'Відмінити')] | //button/span[contains(text(), 'Скасувати')] | //button/span[contains(text(), 'Cancel')] ")
                .should(appear, ofSeconds(5))
                .click();
    }

    private void waitTillProgressbarDisappear() {
        progressBar.should(appear, ofSeconds(5));
        progressBar.should(disappear, ofSeconds(5));
    }
}
