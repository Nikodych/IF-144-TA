package com.softserveinc.ita.mkaralash;

import com.softserveinc.ita.pageobjects.mkaralash.pages.GoogleHomePage;
import com.softserveinc.ita.pageobjects.mkaralash.utils.TestRuner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaTest extends TestRuner {

    @Test
    public void rozetkaTest() {
        var googleHomePage = new GoogleHomePage(driver);

        String searchName = "rozetka";
        var googleSearchResultPage = googleHomePage
                .setSearchValue(searchName)
                .clickSearchButton();

        List<String> list = googleSearchResultPage.getResultList();

        list.forEach(l -> Assert.assertTrue(l
                .contains(searchName), l + "should contain " + searchName));

        String searchProduct = "какао";
        var isCartEmpty = googleSearchResultPage
                .openLinkBy(1)
                .setSearchValue(searchProduct)
                .clickSearchButton()
                .addProductToTheCartBy(1)
                .openCart()
                .isEmpty();

        Assert.assertFalse(isCartEmpty);
    }
}