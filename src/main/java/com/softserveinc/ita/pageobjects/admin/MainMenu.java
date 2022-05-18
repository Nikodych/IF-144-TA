package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class MainMenu {

    public ProtocolPage openProtocolPage() {
        $(xpath("//a[@href = '/admin/protocol']")).click();

        return new ProtocolPage();
    }

    public String getLoggedUserName() {
        return $$(xpath("//button[@class = 'mat-menu-trigger mat-button mat-button-base']"))
                .first() //two buttons are expected: first one is logged user and the second one is language
                .getText();
    }
}
