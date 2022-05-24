package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class MainMenu {
    private final String MENU_ITEM_PATH_TEMPLATE = "//a[@href='/admin/%s']";

    @Step("Main menu: Opened protocol page")
    public ProtocolPage openProtocolPage() {
        $x(format(MENU_ITEM_PATH_TEMPLATE, "protocol")).click();

        return new ProtocolPage();
    }

    @Step("Main menu: Opened faculties page")
    public FacultiesPage openFacultiesPage() {
        $x(format(MENU_ITEM_PATH_TEMPLATE, "faculties")).click();

        return new FacultiesPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]").getText();
    }
}
