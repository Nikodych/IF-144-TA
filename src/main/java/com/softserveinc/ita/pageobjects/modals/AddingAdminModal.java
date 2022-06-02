package com.softserveinc.ita.pageobjects.modals;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class AddingAdminModal extends BaseModal<AddingAdminModal>{
    @Override
    public boolean isAddButtonEnabled() {
        return $x("./span[contains(text(),'Підтвердити')]")
                .should(visible, ofSeconds(5))
                .isEnabled();
    }

    @Override
    @Step("Adding admin modal: Added new admin")
    public void addNewItem() {
        $x("./span[contains(text(),'Підтвердити')]")
                .should(appear,ofSeconds(5))
                .click();
    }
}
