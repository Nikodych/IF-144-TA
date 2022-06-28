package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class GroupsPage extends MainMenu<GroupsPage> {
    private final EntityTable table = new EntityTable();

    @Step("Group page: Got last group name")
    public String getLastGroupName() {
        table.goToTablePage("last");

        return $x("//table")
                .$$x(".//tr")
                .last()
                .$x(".//td[contains(@class,'name')]")
                .getText();
    }

    @Step("Group page: Students page of {group} opened")
    public StudentsPage openStudentsPage(String group) {
        performSearchOf(group);
        table.performActionWithRowByValue(group, "supervisor_account");

        return new StudentsPage();
    }

    @Step("Groups page: Performed search of {group}")
    public GroupsPage performSearchOf(String group) {
        var searchField = $x("//input[contains(@placeholder, 'Filter')]");

        searchField.clear();
        searchField.sendKeys(group);

        return this;
    }
}
