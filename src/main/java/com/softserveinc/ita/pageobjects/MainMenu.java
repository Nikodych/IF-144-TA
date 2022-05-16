package com.softserveinc.ita.pageobjects;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.xpath;

public class MainMenu {

    public ProtocolPage openProtocolPage() {
        $(xpath("//a[@href = '/admin/protocol']")).click();

        return new ProtocolPage();
    }
}
