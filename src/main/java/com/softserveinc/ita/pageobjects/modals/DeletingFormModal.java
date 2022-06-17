package com.softserveinc.ita.pageobjects.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class DeletingFormModal {
    @Step("Deleting form modal: Confirmed in modal window")
    public void confirmModal() {
        $x("//app-confirm//button/span[contains(text(),'Підтвердити')]")
                .should(appear, ofSeconds(5))
                .should(enabled)
                .doubleClick(); // sometimes one click doesn't work, maybe because element is not focused
    }

    @Step("Deleting form modal: Canceled in modal window")
    public void cancelModal() {
        $x("//app-confirm//button/span[contains(text(),'Відмінити')]")
                .should(appear, ofSeconds(5))
                .should(enabled)
                .click();
    }
}
