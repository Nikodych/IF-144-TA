package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.pageobjects.modals.AddingSpecialtyModal;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$x;

public class SpecialitiesPage extends MainMenu {

    @Step("Subjects page: Opened adding subject form")
    public AddingSpecialtyModal openAddingSpecialtyForm() {
        $x("//button[contains(@class,'addButton')]").click();

        return new AddingSpecialtyModal();
    }

    @Step("Speciality page: Got last speciality code")
    public String getLastSpecialityCode() {
        var buttonNavigationLast = $x("//button[contains(@class,'paginator-navigation-last')]");

        if (buttonNavigationLast.isEnabled()) {
            buttonNavigationLast.click();
        }

        return $x("//table")
                .$$x(".//tr")
                .last()
                .$x(".//td[contains(@class,'code')]")
                .getText();
    }

    @Step("Speciality page: Waited for progress bar to disappear")
    public SpecialitiesPage waitForProgressBarToDisappear() {
        $x("//mat-progress-bar").should(disappear);

        return this;
    }

    @Step("Speciality page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear)
                .getText();
    }
}
