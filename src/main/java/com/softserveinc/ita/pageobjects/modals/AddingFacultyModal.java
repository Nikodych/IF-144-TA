package com.softserveinc.ita.pageobjects.modals;

import com.softserveinc.ita.pageobjects.admin.FacultiesPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AddingFacultyModal extends BaseModal<AddingFacultyModal>{
    @Step("Adding faculty modal: Added new faculty")
    public FacultiesPage addNewFaculty() {
        $x("//button[@type='submit']").click();

        return new FacultiesPage();
    }
}
