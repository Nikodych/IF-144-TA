package com.softserveinc.ita.pageobjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

  WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void implicitWait(long timeToWait){
    driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
  }
}
