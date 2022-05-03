package com.softserveinc.ita.mkaralash;

import com.softserveinc.ita.pageobjects.mkaralash.GoogleHomePage;
import com.softserveinc.ita.pageobjects.mkaralash.GoogleSearchResultPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class RozetkaTest extends TestRuner {

    @Test
    public void verifyThatResultsContainSearchName() {
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
    }
}