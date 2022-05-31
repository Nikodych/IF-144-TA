package com.softserveinc.ita.pageobjects.modals;

import com.softserveinc.ita.pageobjects.admin.SpecialitiesPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AddingSpecialtyModal extends BaseModal<AddingSpecialtyModal>{

    @Step("Adding specialty modal: Added new specialty")
    public SpecialitiesPage addNewSpecialty() {
        $x("//button/span[contains(text(),'Підтвердити')]").click();

        return new SpecialitiesPage();
    }

}
