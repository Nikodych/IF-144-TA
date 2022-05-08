package com.softserveinc.ita.mkaralashselenide;

import com.softserveinc.ita.pageobjects.mkaralashselenide.pages.GoogleHomePage;
import com.softserveinc.ita.pageobjects.mkaralashselenide.utils.TestRunner;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RozetkaTest extends TestRunner {
    @Test
    public void rozetkaTest() {
        var googleHomePage = new GoogleHomePage();

        String searchName = "rozetka";
        var googleSearchResultPage = googleHomePage
                .setSearchValue(searchName)
                .clickSearchButton();

        List<String> resultList = googleSearchResultPage.getResultList();
        resultList
                .forEach(result -> assertThat(result)
                        .as("All description should contain " + searchName)
                        .containsIgnoringCase(searchName));

        String searchProduct = "какао";
        var isCartEmpty = googleSearchResultPage
                .openLinkBy(1)
                .setSearchValue(searchProduct)
                .clickSearchButton()
                .addProductToTheCartBy(1)
                .openCart()
                .isEmpty();

        assertThat(isCartEmpty)
                .as("Cart should not be empty")
                .isFalse();
    }
}
