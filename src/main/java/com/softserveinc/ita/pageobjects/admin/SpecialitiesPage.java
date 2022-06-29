package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

@Getter
public class SpecialitiesPage extends MainMenu<SpecialitiesPage> {

    private final EntityTable table = new EntityTable();

    @Step("Speciality page: Got last speciality %fieldName")
    public String getLastSpecialityField(String fieldName) {
        table.goToTablePage("last");

        return $x("//table")
                .$$x(".//tr")
                .last()
                .$x(format(".//td[contains(@class,'%s')]", fieldName))
                .getText();
    }

    @Step("Speciality page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear, ofSeconds(5))
                .getText();
    }
}
