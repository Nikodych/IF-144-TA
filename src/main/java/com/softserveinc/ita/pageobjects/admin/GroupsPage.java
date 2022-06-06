package com.softserveinc.ita.pageobjects.admin;

import com.codeborne.selenide.Condition;
import com.softserveinc.ita.models.EntityTable;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

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
        $x("//input[@placeholder='Шифр групи']").setValue(value);

        return this;
    }

    @Step("Group page: Set group speciality")
    public GroupsPage setSpeciality(String value) {
        $x("//mat-select[@placeholder='Виберіть спеціальність']").click();
        $$x("//mat-option").find(exactText(value)).click();

        return this;
    }

    @Step("Group page: Set group faculty")
    public GroupsPage setFaculty(String value) {
        $x("//mat-select[@placeholder='Виберіть факультет/інститут']").click();
        $$x("//mat-option").find(exactText(value)).click();

        return this;
    }

    @Step("Group page: Confirmed in modal window")
    public GroupsPage confirmModal() {
        $x("//app-group-add-edit-dialog//button/span[contains(text(),'Додати')]").click();

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
