package com.softserveinc.ita.andrewobitotski;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.softserveinc.ita.pageobjects.andrewobitotski.pageobjects.BasePage.setDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static java.time.Duration.ofSeconds;

public abstract class TestRunner {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(ofSeconds(15));
        setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
