package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;

import java.time.LocalDate;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class TimeTablePage extends MainMenu<TimeTablePage> {

    private final EntityTable table = new EntityTable();
    private final LocalDate dateTime = LocalDate.now();

    @Step("Timetable page: Added new timetable")
    public TimeTablePage addTimeTable() {
        $x("//button[contains(@class, 'mat-stroked-button')]").click();
        $x("//app-time-table-add-dialog").shouldBe(visible);

        return this;
    }

    @Step("Timetable page: Set group")
    public TimeTablePage setGroupBy(int index) {
        $x("//mat-select[@formcontrolname='group_id']").click();
        $x(format("//div[contains(@class, 'mat-select-panel')]/mat-option[%s]", index)).click();

        return this;
    }

    @Step("Timetable page: Set start date")
    public TimeTablePage setStartDate() {
        $x("//input[@formcontrolname='start_date']").sendKeys(dateTime.toString());

        return this;
    }

    @Step("Timetable page: Set end date")
    public TimeTablePage setEndDate() {
        $x("//input[@formcontrolname='end_date']").sendKeys(dateTime.toString());

        return this;
    }

    @Step("Timetable page: Set start time")
    public TimeTablePage setStartTime() {
        var time = "09:35";

        $x("//input[@formcontrolname='start_time']").sendKeys(time);

        return this;
    }

    @Step("Timetable page: Set end time")
    public TimeTablePage setEndTime() {
        var time = "10:35";

        $x("//input[@formcontrolname='end_time']").sendKeys(time);

        return this;
    }

    @Step("Timetable page: Deleted timetable")
    public TimeTablePage deleteTimetableByGroup(String group) {
        table.performActionWithRowByValue(group, "delete");

        return this;
    }

    @Step("Timetable page: Confirmed deleting timetable")
    public TimeTablePage confirmDeletingTimetable() {
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
}
