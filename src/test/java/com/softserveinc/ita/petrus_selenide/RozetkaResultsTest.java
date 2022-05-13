package com.softserveinc.ita.petrus_selenide;

import com.softserveinc.ita.petrus_selenide.page_objects.RozetkaHomePage;
import com.softserveinc.ita.petrus_selenide.util.TestRunner;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RozetkaResultsTest extends TestRunner {
    @Test
    public void VerifyPageContainsExpectedNumberOfGoods() {
        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage();
        var numberOfGoods = rozetkaHomePage
                .findGoods("mavic")
                .numberOfGoods();

        assertThat(numberOfGoods).isEqualTo(60);
    }
}
