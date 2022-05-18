package com.softserveinc.ita.pageobjects.admin;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class MainMenu {

    public ProtocolPage openProtocolPage() {
        $(xpath("//a[@href = '/admin/protocol']")).click();

        return new ProtocolPage();
    }

    public String getLoggedUserName() {
        return $(xpath("//span[text()='d-tester']/following-sibling::button[1]")).getText();
    }
}
