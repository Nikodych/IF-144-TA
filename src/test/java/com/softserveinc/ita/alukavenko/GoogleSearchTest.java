package com.softserveinc.ita.alukavenko;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.alukavenko.GoogleMainPage;
import com.softserveinc.ita.pageobjects.alukavenko.GoogleSearchResultsPage;
import java.util.List;
import org.assertj.core.api.Condition;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    @Test
    public void verifySearchResults() {
        String searchString = "rozetka";

        GoogleMainPage searchPage = open("https://www.google.com/", GoogleMainPage.class);
        GoogleSearchResultsPage resultsPage = searchPage.search(searchString);
        List<String> titles = resultsPage.getSearchResultTitles();

        Condition<String> containsSearchString = new Condition<>(s -> s.toLowerCase()
                .contains(searchString)
                || s.toLowerCase()
                .contains("розетка"),
                searchString);
        assertThat(titles).as("Result titles should contain search string")
                .have(containsSearchString);
    }
}
