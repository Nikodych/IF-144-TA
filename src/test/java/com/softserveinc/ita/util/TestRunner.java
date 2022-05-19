package com.softserveinc.ita.util;

import com.softserveinc.ita.petrus.page_objects.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.softserveinc.ita.petrus.page_objects.BasePage.*;
import static io.github.bonigarcia.wdm.WebDriverManager.*;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TestRunner {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(1000, SECONDS);
        driver.manage().timeouts().implicitlyWait(1000, SECONDS);
        setDriver(driver);
    }

    @AfterClass
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
