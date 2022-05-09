package com.softserveinc.ita.pageobjects.alukavenko;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.By;

public class RozetkaMainPage {

    public RozetkaHomeAppliancesPage goToHomeAppliancesCategory() {
        $(By.xpath("//a[@class = 'menu-categories__link' and @href='https://bt.rozetka.com.ua/']")).click();
        return page(RozetkaHomeAppliancesPage.class);
    }
}
