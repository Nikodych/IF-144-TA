package com.softserveinc.ita.alukavenko;

import com.softserveinc.ita.pageobjects.alukavenko.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // TODO: 03.05.2022 replace with WebDriverManager
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().maximize();

        BasePage.setDriver(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
