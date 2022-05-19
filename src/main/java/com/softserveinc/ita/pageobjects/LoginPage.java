package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;
import static com.softserveinc.ita.pageobjects.util.DataProvider.DASHBOARD_PAGE_URL;
import static com.softserveinc.ita.pageobjects.util.WindowTabHelper.getCurrentUrl;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

import com.softserveinc.ita.pageobjects.admin.DashboardPage;

public class LoginPage {

    public DashboardPage login(String username, String password) {

            $(name("username")).sendKeys(username);
            $(name("password")).sendKeys(password);
        do {
            $(xpath("//button[contains(@class,'mat-primary')]")).click();
        } while (getCurrentUrl().equals(DASHBOARD_PAGE_URL));

        return new DashboardPage();
    }
}
