package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Selenide.$x;

public class SubjectsPage extends MainMenu {

    public AddingSubjectModal openAddingSubjectForm() {
        $x("//button[contains(@class, 'addSubject')]").click();

        return new AddingSubjectModal();
    }
}
