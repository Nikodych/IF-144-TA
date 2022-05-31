package com.softserveinc.ita.pageobjects.modals;

import com.softserveinc.ita.pageobjects.admin.SubjectsPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

public class AddingSubjectModal extends BaseModal<AddingSubjectModal>{

    @Step("Adding subject modal: Added new subject")
    public SubjectsPage addNewSubject() {
        $x("//button[@type='submit']").click();

        return new SubjectsPage();
    }
}
