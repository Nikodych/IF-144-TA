package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class SpecialitiesPage extends MainMenu {

    @Step("Speciality page: Added new speciality")
    public SpecialitiesPage addNewSpeciality(String code, String name) {
        $x("//button[contains(@class,'addButton')]").click();
        $x("//input[@formcontrolname='speciality_code']").setValue(code);
        $x("//input[@formcontrolname='speciality_name']").setValue(name);
        $$x("//button/span")
                .findBy(Condition.text(" Підтвердити "))
                .click();
        return this;
    }

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

    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear)
                .getText();
    }
}
