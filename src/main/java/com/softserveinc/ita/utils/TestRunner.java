package com.softserveinc.ita.utils;

import static java.util.concurrent.TimeUnit.SECONDS;

import com.softserveinc.ita.pageobjects.GooglePage;
import com.softserveinc.ita.pageobjects.RozetkaPage;
import com.softserveinc.ita.pageobjects.RozetkaSearchResultsPage;
import com.softserveinc.ita.pageobjects.SearchResultsPage;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestRunner {

  private WebDriver driver;

  @BeforeMethod
  public void setUp() throws IOException {
    ConfigReader.readProp();
    System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(60, SECONDS);
    driver.get(ConfigReader.prop.getProperty("base.url"));
  }

  @AfterMethod
  public void tearDown() {
    driver.quit();
  }

  public GooglePage getGooglePage() {
    return new GooglePage(driver);
  }

  public SearchResultsPage getSearchResultPage() {
    return new SearchResultsPage(driver);
  }

  public RozetkaPage getRozetkaPage() {
    return new RozetkaPage(driver);
  }

  public RozetkaSearchResultsPage getRozetkaSearchResultsPage() {
    return new RozetkaSearchResultsPage(driver);
  }

}
