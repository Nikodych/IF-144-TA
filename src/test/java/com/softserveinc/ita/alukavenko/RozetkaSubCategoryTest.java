package com.softserveinc.ita.alukavenko;

import com.softserveinc.ita.alukavenko.Utils.TestRunner;
import com.softserveinc.ita.pageobjects.alukavenko.RozetkaMainPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RozetkaSubCategoryTest extends TestRunner {

    private static final int EXPECTED_SUB_CATEGORY_QUANTITY = 10;

    @Test
    public void verifyHomeAppliancesSubCategoriesQuantity() {
        int subcategoriesQuantity = new RozetkaMainPage()
                .goToHomeAppliancesCategory()
                .getSubCategoriesQuantity();

        assertEquals(subcategoriesQuantity, EXPECTED_SUB_CATEGORY_QUANTITY,
                "Sub categories quantity should be: " + EXPECTED_SUB_CATEGORY_QUANTITY);
    }
}
