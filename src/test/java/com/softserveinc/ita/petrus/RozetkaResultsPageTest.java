package com.softserveinc.ita.petrus;

import com.softserveinc.ita.petrus.page_objects.RozetkaHomePage;
import com.softserveinc.ita.util.TestRunner;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RozetkaResultsPageTest extends TestRunner {
    public static final int EXPECTED_AMOUNT = 60;
    public static final String ROZETKA_FIND_GOODS = "mavic";

    @Test
    @Description("Check if amount of goods equals expected amount")
    void checkAmountOfGoods() {
        var rozetkaHomePage = new RozetkaHomePage();
        var amountOfGoods = rozetkaHomePage
                .findGoods(ROZETKA_FIND_GOODS)
                .findAmountOfGoods();

        Assert.assertEquals(amountOfGoods, EXPECTED_AMOUNT);
    }
}
