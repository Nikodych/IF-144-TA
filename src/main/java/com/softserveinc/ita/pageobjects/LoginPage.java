package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class LoginPage {

    public DashboardPage login(String login, String password) {

        $(By.name("username")).sendKeys(login);
        $(By.name("password")).sendKeys(password);
        $(By.xpath("//button[@class='butt-wrap mat-flat-button mat-button-base mat-primary']")).click();

        return new DashboardPage();
    }
}
