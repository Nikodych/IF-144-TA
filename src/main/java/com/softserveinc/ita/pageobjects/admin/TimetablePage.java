package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import java.time.LocalDate;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class TimetablePage extends MainMenu<TimetablePage> {

    private final LocalDate dateTime = LocalDate.now();

    @Step("Timetable page: Added new timetable")
    public TimetablePage addTimetable() {
        $x("//button[contains(@class, 'mat-stroked-button')]").click();
        $x("//app-time-table-add-dialog").shouldBe(visible);

        return this;
    }

    @Step("Timetable page: Set group")
    public TimetablePage setGroupBy(int index) {
        $x("//mat-select[@formcontrolname='group_id']").click();
        $x(format("//div[contains(@class, 'mat-select-panel')]/mat-option[%s]", index)).click();

        return this;
    }

    @Step("Timetable page: Set start date")
    public TimetablePage setStartDate() {
        $x("//input[@formcontrolname='start_date']").sendKeys(dateTime.toString());

        return this;
    }

    @Step("Timetable page: Set end date")
    public TimetablePage setEndDate() {
        $x("//input[@formcontrolname='end_date']").sendKeys(dateTime.toString());

        return this;
    }

    @Step("Timetable page: Set start time")
    public TimetablePage setStartTime() {
        var time = "09:35";

        $x("//input[@formcontrolname='start_time']").sendKeys(time);

        return this;
    }

    @Step("Timetable page: Set end time")
    public TimetablePage setEndTime() {
        var time = "10:35";

        $x("//input[@formcontrolname='end_time']").sendKeys(time);

        return this;
    }

    @Step("Timetable page: Submitted adding new timetable")
    public TimetablePage submitAdding() {
        $x("//button[@type='submit']").click();

        return this;
    }

    @Step("Timetable page: Deleted timetable")
    public TimetablePage deleteTimetableByGroup(String group) {
        pickIconMenu(group, "delete");

        return this;
    }

    @Step("Timetable page: Confirmed deleting timetable")
    public TimetablePage confirmDeletingTimetable() {
        $x("//app-confirm//span[contains(text(),'Confirm') or contains(text(),'Підтвердити')]")
                .shouldBe(visible)
                .click();

        return this;
    }

    public boolean hasTimetable(String group) {
        return $$x("//td[contains(@class, 'mat-column-group')]")
                .shouldHave(sizeGreaterThanOrEqual(0))
                .texts()
                .contains(group);
    }

    private void pickIconMenu(String group, String menuItem) {
        $$x("//tbody//tr//td")
                .findBy(exactText(group))
                .parent()
                .$x(format(".//mat-icon[@aria-label='%s']", menuItem))
                .click();
    }
}
