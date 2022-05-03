package com.softserveinc.ita.mkaralash;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestRuner {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        //  Installing chrome driver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }
}
