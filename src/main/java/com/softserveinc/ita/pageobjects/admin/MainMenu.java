package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.openqa.selenium.By.xpath;

public class MainMenu {

    public ProtocolPage openProtocolPage() {
        $(xpath("//a[@href = '/admin/protocol']")).click();

        return new ProtocolPage();
    }

    public SubjectsPage openSubjectsPage() {
        $(xpath("//a[@href = '/admin/subjects']")).click();

        return new SubjectsPage();
    }

    public String getLoggedUserName() {
        return $x("//mat-toolbar/button[1]").getText();
    }
}
