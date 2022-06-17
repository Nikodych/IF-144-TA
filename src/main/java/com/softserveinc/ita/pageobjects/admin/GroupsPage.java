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
}
