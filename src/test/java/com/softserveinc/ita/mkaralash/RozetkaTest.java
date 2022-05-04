package com.softserveinc.ita.mkaralash;

import com.softserveinc.ita.pageobjects.mkaralash.GoogleHomePage;
import com.softserveinc.ita.pageobjects.mkaralash.GoogleSearchResultPage;
import com.softserveinc.ita.pageobjects.mkaralash.RozetkaHomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RozetkaTest extends TestRuner {

    @Test
    public void rozetkaTest() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);

        String searchName = "rozetka";

        List<WebElement> list = googleHomePage
                .inputSearchName(searchName)
                .clickSearchButton()
                .getResultList();

        list.forEach(l -> Assert.assertTrue(l
                .getText()
                .toLowerCase()
                .contains(searchName), l.getText() + "should contain " + searchName));

        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(driver);
        googleSearchResultPage.clickLink(1);

        RozetkaHomePage rozetkaHomePage = new RozetkaHomePage(driver);
        boolean isCartEmpty = rozetkaHomePage
                .getRozetkaHeaderPage()
                .openCart()
                .isEmpty();
        Assert.assertTrue(isCartEmpty);
    }
}