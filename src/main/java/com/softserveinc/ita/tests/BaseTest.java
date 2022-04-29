package com.softserveinc.ita.tests;

import static com.softserveinc.ita.utils.Properties.BASE_URL;

import com.softserveinc.ita.pageobjects.HomePage;
import com.softserveinc.ita.pageobjects.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  private WebDriver driver;
//  private final static String BASE_URL = "https://google.com.ua/";

  @BeforeMethod
  public void testsSetUp() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get(BASE_URL);
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

  public WebDriver getDriver() {
    return driver;
  }

  public HomePage getHomePage(){
    return new HomePage(getDriver());
  }

  public SearchResultsPage getSearchResultPage(){
    return new SearchResultsPage(getDriver());
  }

}
