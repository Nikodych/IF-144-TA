package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;

public class QuestionsPage extends MainMenu<TestsPage>{

    private final EntityTable table = new EntityTable();

    public boolean isExpectedTestQuestionsPage(String test) {
        return $x(format("//h3[contains(text(),'%s')]", test))
                .should(appear, ofSeconds(5))
                .exists();
    }
}