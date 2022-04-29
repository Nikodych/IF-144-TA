package com.softserveinc.ita.alukavenko;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

public class RozetkaTest {

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
    public void verifyGoogleSearch() {

        driver.get("https://www.google.com/");
        driver.findElement(name("q")).sendKeys("Rozetka");
        driver.findElement(xpath("//input[@name = 'btnK']")).click();

        Assert.assertTrue(driver.findElements(xpath("//cite[text()='https://rozetka.com.ua']")).size() > 0);

    }

    @Test
    public void verifyBTSubCategoryQuantity() {

        driver.get("https://rozetka.com.ua/");

        driver.findElement(xpath("//a[@class = 'menu-categories__link' and @href='https://bt.rozetka.com.ua/']")).click();

        Assert.assertEquals(driver.findElements(xpath("//div[@class='tile-cats']")).size(), 10);
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
    }

}
