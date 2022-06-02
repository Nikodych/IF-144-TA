package com.softserveinc.ita.pageobjects.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class AddingSpecialtyModal extends BaseModal<AddingSpecialtyModal> {
    @Override
    @Step("Adding specialty modal: Added new specialty")
    public void addNewItem() {
        $x("//button/span[contains(text(),'Підтвердити')]")
                .should(appear,ofSeconds(5))
                .click();
    }

}
