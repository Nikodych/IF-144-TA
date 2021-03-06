package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

import com.softserveinc.ita.pageobjects.admin.DashboardPage;

public class LoginPage {

    public DashboardPage login(String username, String password) {
        $(name("username")).sendKeys(username);
        $(name("password")).sendKeys(password);
        $(xpath("//button[contains(@class,'mat-primary')]")).click();

        return new DashboardPage();
    }
}
