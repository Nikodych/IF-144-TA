package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.models.QuestionEntity;
import com.softserveinc.ita.pageobjects.modals.DeletingFormModal;
import io.qameta.allure.Step;
import lombok.Getter;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class QuestionsPage extends MainMenu<TestsPage> {
    @Getter
    private final EntityTable table = new EntityTable();

    public boolean isExpectedTestQuestionsPage(String test) {
        return $x(format("//h3[contains(text(),'%s')]", test))
                .should(appear, ofSeconds(5))
                .exists();
    }

    @Step("Test's Question page: Opened adding question page")
    public AddingNewQuestionPage openAddingNewQuestionPage() {
        $("[aria-label=\"add\"]").click();

        return new AddingNewQuestionPage();
    }

    @Step("Test's Question page: Deleting question")
    public AddingNewQuestionPage deleteQuestion (QuestionEntity question) {
        var searchValue = question.getTextOfQuestion();

        $$x("//tbody//tr//td")
                .findBy(exactText(searchValue))
                .parent()
                .$x(".//mat-icon[@aria-label='delete']")
                .should(enabled)
                .click();

        new DeletingFormModal().confirmModal();
        waitTillProgressBarDisappears();

        return new AddingNewQuestionPage();
    }

    public List<String> getTextsOfQuestions() {
        return $$x("//td[contains(@class, 'mat-column-question_text')]").texts();
    }

    public boolean isExpectedTextOfQuestionFound(String expectedNameOfQuestion) {
        table.findTablePageWithSearchValue(expectedNameOfQuestion);

        return getTextsOfQuestions().contains(expectedNameOfQuestion);
    }
}