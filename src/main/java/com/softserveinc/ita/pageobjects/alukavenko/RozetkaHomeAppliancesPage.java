package com.softserveinc.ita.pageobjects.alukavenko;

import static com.codeborne.selenide.Selenide.$$;

import org.openqa.selenium.By;

public class RozetkaHomeAppliancesPage {
    public int getSubCategoriesQuantity() {
        return $$(By.xpath("//div[@class='tile-cats']")).size();
    }
}
