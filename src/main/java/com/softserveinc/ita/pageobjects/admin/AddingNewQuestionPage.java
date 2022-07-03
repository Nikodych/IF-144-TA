package com.softserveinc.ita.pageobjects.admin;

import com.softserveinc.ita.models.AnswerEntity;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class AddingNewQuestionPage extends MainMenu<AddingNewQuestionPage> {

    @Step("Adding New Question page: Filled in text of question")
    public AddingNewQuestionPage fillTextOfQuestion(String textOfQuestion) {
        var textField = $("textarea[formcontrolname='question_text']");
        textField.clear();
        textField.sendKeys(textOfQuestion);

        return this;
    }

    @Step("Adding New Question page: Set type of question")
    public AddingNewQuestionPage setTypeOfQuestion(int indexOfType) {
        setValueFromDropdownList("type", indexOfType);

        return this;
    }

    @Step("Adding New Question page: Set level of question")
    public AddingNewQuestionPage setLevelOfQuestion(int indexOfLevel) {
        setValueFromDropdownList("level", indexOfLevel);

        return this;
    }

    @Step("Adding New Question page: Added answer for question")
    public AddingNewQuestionPage addAnswerForQuestion(AnswerEntity answer) {
        $x("//button/span[contains(text(),'Додати відповідь')]").click();
        var answerInputField = $("input[formcontrolname='answer_text']");
        answerInputField.clear();
        answerInputField.sendKeys(answer.getTextOfAnswer());
        if (answer.isAnswerRight())
            $("mat-checkbox[formcontrolname='true_answer']").click();

        return this;
    }

    @Step("Adding New Question page: Created new question")
    public QuestionsPage createQuestion() {
        $("button[type='submit']").click();

        return new QuestionsPage();
    }

    private void setValueFromDropdownList(String nameOfList, int indexOfValue) {
        $(format("mat-select[formcontrolname='%s']", nameOfList)).click();
        $(format("mat-option[value='%s']", indexOfValue))
                .should(appear, ofSeconds(5))
                .click();
    }
}