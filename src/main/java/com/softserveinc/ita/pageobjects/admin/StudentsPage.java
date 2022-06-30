package com.softserveinc.ita.pageobjects.admin;

import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;
import static java.time.Duration.ofSeconds;

@Getter
public class StudentsPage extends MainMenu<StudentsPage> {
    private final EntityTable table = new EntityTable();

    @Step("Students page: Got pop-up message text")
    public String getMessageText() {
        return $x("//simple-snack-bar/span")
                .should(appear, ofSeconds(5))
                .getText();
    }

    @Step("Students page: Got student's grade book id")
    public String getStudentsGradeBookId(String searchValue) {
        refresh();

        table.findTablePageWithSearchValue(searchValue);

            return  $x("//table")
                    .$$x(".//tr")
                    .findBy(text(searchValue))
                    .$x(".//td[contains(@class,'gradebookID')]")
                    .getText();
    }
}
