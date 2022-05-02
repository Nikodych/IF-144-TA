package com.softserveinc.ita;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class SampleTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        //  Installing chrome driver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void demoTest() {
        driver.get("https://www.google.com/");
        driver.findElement(name("q")).sendKeys("Wikipedia");
        driver.findElement(xpath("//input[@name = 'btnK']")).click();
        driver.findElement(xpath("//a[contains(@href, 'wikipedia.org')]")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("wikipedia"));
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }
}
