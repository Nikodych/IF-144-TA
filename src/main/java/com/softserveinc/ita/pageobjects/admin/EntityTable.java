package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class EntityTable {
    private final String NAVIGATION_BUTTON_PATH_TEMPLATE = "//button[contains(@Class,'paginator-navigation-%s')]";

    @Step("Table: Switched table page")
    public void goToTablePage(String direction) {
        var buttonNavigation = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, direction));

        if (buttonNavigation.isEnabled()) {
            buttonNavigation.click();
        }
    }

    @Step("Table: Deleted table row")
    public void deleteRowByValue(String searchValue) {
        performActionWithRowByValue(searchValue, "delete");
    }

    @Step("Table: Edit table row")
    public void editRowByValue(String searchValue) {
        performActionWithRowByValue(searchValue, "edit");
    }

    @Step("Table: Searched for value in table texts")
    public boolean isSearchValueInTableTexts(String searchValue) {
        var tableRows = $$x("//table//tr//td");

        return tableRows
                .texts()
                .contains(searchValue);
    }

    @Step("Table: Searched for page containing value")
    public void findTablePageWithSearchValue(String searchValue) {
        goToTablePage("first");

        var buttonNavigationNext = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, "next"));
        var isSearchValueOnCurrentPage = false;

        while (buttonNavigationNext.isEnabled() && !isSearchValueOnCurrentPage) {
            goToTablePage("next");
            buttonNavigationNext
                    .$x(".//div[contains(@class,'round')]/div[@class='mat-ripple-element']")
                    .should(disappear);

            isSearchValueOnCurrentPage = isSearchValueInTableTexts(searchValue);
        }
    }

    public boolean checkIfRowWithSearchValueIsPresent(String searchValue) {
        goToTablePage("first");

        var buttonNavigationNext = $x(format(NAVIGATION_BUTTON_PATH_TEMPLATE, "next"));
        var isSearchValueOnCurrentPage = false;

        while (buttonNavigationNext.isEnabled() ) {
            goToTablePage("next");
            buttonNavigationNext
                    .$x(".//div[contains(@class,'round')]/div[@class='mat-ripple-element']")
                    .should(disappear);

            isSearchValueOnCurrentPage = isSearchValueInTableTexts(searchValue);
        }
        return isSearchValueOnCurrentPage;
    }

    public void performActionWithRowByValue(String searchValue, String actionToPerform) {
        $$x("//tbody//tr//td")
                .findBy(exactText(searchValue))
                .should(exist)
                .parent()
                .$x(format(".//*[contains(@class, '%s') or @aria-label='%s']", actionToPerform, actionToPerform))
                .should(enabled)
                .click();
    }
}
