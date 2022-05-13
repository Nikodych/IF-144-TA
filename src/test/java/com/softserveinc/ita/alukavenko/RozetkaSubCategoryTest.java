package com.softserveinc.ita.alukavenko;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import com.softserveinc.ita.pageobjects.alukavenko.RozetkaMainPage;
import org.testng.annotations.Test;

public class RozetkaSubCategoryTest {

    @Test
    public void verifyHomeAppliancesSubCategoriesQuantity() {

        int expectedSubCategoryQuantity = 10;

        RozetkaMainPage rozetkaMainPage = open("https://rozetka.com.ua/", RozetkaMainPage.class);
        int subcategoriesQuantity = rozetkaMainPage.goToHomeAppliancesCategory()
                .getSubCategoriesQuantity();

        assertThat(subcategoriesQuantity).as("Sub categories quantity should be equal: "
                        + expectedSubCategoryQuantity)
                .isEqualTo(expectedSubCategoryQuantity);
    }
}
