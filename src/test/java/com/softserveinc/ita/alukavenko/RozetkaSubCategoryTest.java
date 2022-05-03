package com.softserveinc.ita.alukavenko;

import com.softserveinc.ita.pageobjects.alukavenko.RozetkaMainPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RozetkaSubCategoryTest extends BaseTest {

    private static final int EXPECTED_SUB_CATEGORY_QUANTITY = 10;

    @Test
    public void verifyBTSubCategoryQuantity() {

        int subcategoriesQuantity = new RozetkaMainPage().goToCategoryBT().getSubCategoriesQuantity();

        assertEquals(subcategoriesQuantity, EXPECTED_SUB_CATEGORY_QUANTITY, "Sub categories quantity should be: "+EXPECTED_SUB_CATEGORY_QUANTITY);

    }

}
