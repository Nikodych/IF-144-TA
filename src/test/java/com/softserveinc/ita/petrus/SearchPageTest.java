package com.softserveinc.ita.petrus;

import com.softserveinc.ita.petrus.page_objects.GoogleHomePage;
import com.softserveinc.ita.petrus.page_objects.SearchPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchPageTest extends TestRunner{


    private static final int EXPECTED_AMOUNT_OF_GOODS = 36;
    private static final String SEARCH_PAGE = "rozetka";
    public static final String GOODS = "mavic 3";
    SearchPage searchPage;
    @BeforeClass
    void getHomePage(){
        GoogleHomePage homePage = new GoogleHomePage(driver);
        homePage.search(SEARCH_PAGE);
        searchPage = homePage.clickOnFirstTitle();
    }

    @Test
    void checkAmountOfGoods(){
     searchPage.searchGoods(GOODS);
     searchPage.searchButton.click();

        Assert.assertEquals(searchPage.amountOfGoods(), EXPECTED_AMOUNT_OF_GOODS);
    }


}
