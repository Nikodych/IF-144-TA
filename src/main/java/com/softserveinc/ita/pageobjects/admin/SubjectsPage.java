package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.pageobjects.modals.AddingFormModal;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

@Getter
public class SubjectsPage extends MainMenu<SubjectsPage> {

    private final EntityTable table = new EntityTable();

    public boolean hasSubject(String subject) {
        return $$x("//td[contains(@class, 'mat-column-subject_name')]")
                .shouldHave(sizeGreaterThanOrEqual(0))
                .texts()
                .contains(subject);
    }

    @Step("Subjects page: Set search value")
    public SubjectsPage setSearchValue(String subject) {
        var searchField = "//mat-form-field[contains(@class, 'filter')]//input";

        $x(searchField).clear();
        $x(searchField).sendKeys(subject);

        return this;
    }

    @Step("Subjects page: Deleted subject")
    public SubjectsPage deleteSubjectByName(String subject) {
        pickIconMenu(subject, "delete");

        return this;
    }

    @Step("Subjects page: Confirmed deleting subject")
    public SubjectsPage confirmDeletingSubject() {
        $x("//app-confirm//button[1]")
                .shouldBe(visible)
                .click();

        return this;
    }

    @Step("Subjects page: Edited subject")
    public AddingFormModal editSubject(String subject) {
        pickIconMenu(subject, "edit");
        waitUntilModalVisible();

        return new AddingFormModal();
    }

    @Step("Subjects page: Opened timetable of subject")
    public TimetablePage openTimetablePage(String subject) {
        pickIconMenu(subject, "date_range");

        return new TimetablePage();
    }

    private void waitUntilModalVisible() {
        $x("//app-subjects-create-modal").shouldBe(visible);
    }

    private void pickIconMenu(String subject, String menuItem) {
        $$x("//tbody//tr//td")
                .findBy(exactText(subject))
                .parent()
                .$x(format(".//mat-icon[contains(@class, '%s')]", menuItem))
                .click();
    }
}
