package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class LoginPage {

    public DashboardPage login(String login, String password) {

        $(name("username")).sendKeys(login);
        $(name("password")).sendKeys(password);
        $(xpath("//button[@class='butt-wrap mat-flat-button mat-button-base mat-primary']")).click();

        return new DashboardPage();
    }
}
