package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class MainMenu {

    private final String MENU_ITEM_PATH_TEMPLATE = "//a[@href='/admin/%s']";

    public ProtocolPage openProtocolPage() {
        var menuItemName = "protocol";
        $x(format(MENU_ITEM_PATH_TEMPLATE, menuItemName)).click();

        return new ProtocolPage();
    }

    @Step("Main menu: Opened subjects page")
    public SubjectsPage openSubjectsPage() {
        var menuItemName = "subjects";
        $x(format(MENU_ITEM_PATH_TEMPLATE, menuItemName)).click();

        return new SubjectsPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]").getText();
    }
}
