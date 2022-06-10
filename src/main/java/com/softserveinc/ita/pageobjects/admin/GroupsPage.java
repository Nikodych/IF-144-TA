package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.pageobjects.modals.AddingFormModal;

import static com.codeborne.selenide.Selenide.$x;

public class GroupsPage extends MainMenu {
    public AddingFormModal openAddingForm() {
        $x("//button/span[contains(text(), 'Додати нову групу')]").click();
        return new AddingFormModal();
    }
}
