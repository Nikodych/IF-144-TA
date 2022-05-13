package com.softserveinc.ita.petrus_selenide;

import com.softserveinc.ita.petrus_selenide.page_objects.GoogleHomePage;
import com.softserveinc.ita.petrus_selenide.util.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class GoogleResultsTest extends TestRunner {

    @Test
    public void VerifyEachTitleContainsExpectedContent() {
        var googleHomePage = new GoogleHomePage();
        var titlesList = googleHomePage.search("rozetka").getTitles();

        assertThat(titlesList).allMatch(t -> t.contains("rozetka") || t.contains("розетка"));
    }
}
