package com.softserveinc.ita.petrus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestRunner {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfProperties.getProperty("loginPage"));
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }

}


