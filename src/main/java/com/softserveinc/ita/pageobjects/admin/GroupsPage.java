package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class GroupsPage extends MainMenu {
    private EntityTable table = new EntityTable();

    @Step("Group page: Added new group")
    public GroupsPage openAddingNewForm() {
        $x("//button//span//mat-icon[contains(text(),'add')]").click();
        $x("//app-group-add-edit-dialog").should(appear);

        return this;
    }

    @Step("Group page: Set group code")
    public GroupsPage setCode(String value) {
        var codeField = $x("//input[@placeholder='Шифр групи']");
        codeField.should(exist);

        // first click in field then send keys, as another element may be focused
        if (!codeField.is(focused)) {
            codeField.doubleClick(); // one click sometimes doesn't change the focus for some reason
        }

        codeField
                .shouldBe(focused)
                .sendKeys(value);

        return this;
    }

    @Step("Group page: Set group speciality")
    public GroupsPage setSpeciality(String value) {
        $x("//mat-select[@placeholder='Виберіть спеціальність']")
                .should(visible)
                .click();
        $$x("//mat-option")
                .find(exactText(value))
                .should(exist)
                .click();

        return this;
    }

    @Step("Group page: Set group faculty")
    public GroupsPage setFaculty(String value) {
        $x("//mat-select[@placeholder='Виберіть факультет/інститут']")
                .should(visible)
                .click();
        $$x("//mat-option")
                .find(exactText(value))
                .should(exist)
                .click();

        return this;
    }

    @Step("Group page: Confirmed in modal window")
    public GroupsPage confirmModal() {
        $x("//app-group-add-edit-dialog//button/span[contains(text(),'Додати')]" +
                "| //app-confirm//button/span[contains(text(),'Підтвердити')]")
                .parent()
                .should(enabled)
                .click();

        return this;
    }

    @Step("Group page: Got last group name")
    public String getLastGroupName() {
        table.goToTablePage("last");

        return $x("//table")
                .$$x(".//tr")
                .last()
                .$x(".//td[contains(@class,'name')]")
                .getText();
    }
}
