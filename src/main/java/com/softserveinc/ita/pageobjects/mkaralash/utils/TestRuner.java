package com.softserveinc.ita.pageobjects.mkaralash.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestRuner {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver
                .manage()
                .timeouts()
                .implicitlyWait(10, SECONDS);
        driver
                .manage()
                .window()
                .maximize();
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }

    @BeforeMethod
    public void open() {
        driver.get("https://www.google.com/");
        driver
                .manage()
                .timeouts()
                .pageLoadTimeout(10, SECONDS);
    }
}
