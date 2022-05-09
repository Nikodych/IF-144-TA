package com.softserveinc.ita.pageobjects.alukavenko;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import java.util.List;
import org.openqa.selenium.By;

public class GoogleSearchResultsPage {

    public ElementsCollection results() {
        return $$(By.xpath("//cite/../../h3"))
                .exclude(Condition.attribute("offsetHeight", "0"));
    }

    public List<String> getSearchResultTitles() {
        return results().texts();
    }
}
