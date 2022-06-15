package com.softserveinc.ita.mkaralash;

import com.softserveinc.ita.pageobjects.mkaralash.pages.GoogleHomePage;
import com.softserveinc.ita.pageobjects.mkaralash.pages.RozetkaHomePage;
import com.softserveinc.ita.pageobjects.mkaralash.utils.TestRuner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RozetkaTest extends TestRuner {

    @Test
    public void rozetkaTest() {
        var searchName = "rozetka";
        var googleSearchResultPage = new GoogleHomePage(driver)
                .setSearchValue(searchName)
                .clickSearchButton();

        var list = googleSearchResultPage.getResultList();
        list.forEach(l -> Assert.assertTrue(l
                .contains(searchName), l + "should contain " + searchName));

        googleSearchResultPage.openLinkBy(1);

        var searchProduct = "какао";
        var isCartEmpty = new RozetkaHomePage(driver)
                .setSearchValue(searchProduct)
                .clickSearchButton()
                .addProductToTheCartBy(1)
                .openCart()
                .isEmpty();

        Assert.assertFalse(isCartEmpty);
    }
}