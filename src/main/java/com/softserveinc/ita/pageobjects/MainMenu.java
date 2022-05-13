package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class MainMenu {

    public ProtocolPage openProtocolPage() {
        $(By.xpath("//a[@href = '/admin/protocol']")).click();
        return new ProtocolPage();
    }
}
