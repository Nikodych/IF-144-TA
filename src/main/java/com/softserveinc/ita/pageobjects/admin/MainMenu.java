package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class MainMenu {

    private final String MENU_ITEM_PATH_TEMPLATE = "//a[@href='/admin/%s']";

    public ProtocolPage openProtocolPage() {
        $x(format(MENU_ITEM_PATH_TEMPLATE, "protocol")).click();

        return new ProtocolPage();
    }

    @Step("Main menu: Opened subjects page")
    public SubjectsPage openSubjectsPage() {
        $x(format(MENU_ITEM_PATH_TEMPLATE, "subjects")).click();

        return new SubjectsPage();
    }

    @Step("Main menu: Opened faculties page")
    public FacultiesPage openFacultiesPage() {
        $x(format(MENU_ITEM_PATH_TEMPLATE, "faculties")).should(appear, ofSeconds(5)).click();

        return new FacultiesPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]").should(appear, ofSeconds(5)).getText();
    }
}
