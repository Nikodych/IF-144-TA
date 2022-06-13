package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class GroupsPage extends MainMenu {
    @Step("Groups page: opened adding groups form")
    public AddingFormModal openAddingForm() {
        $x("//app-group//*[@aria-label='add']/ancestor::button").click();

        return new AddingFormModal();
    }
}
