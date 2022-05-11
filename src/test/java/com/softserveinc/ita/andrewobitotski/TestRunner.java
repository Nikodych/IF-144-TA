package com.softserveinc.ita.andrewobitotski;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Configuration.*;

public abstract class TestRunner {

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        browser = "chrome";
        driverManagerEnabled = true;
        browserSize = "1920x1080";
        headless = false;
    }
}
