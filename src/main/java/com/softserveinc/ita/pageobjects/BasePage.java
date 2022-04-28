package com.softserveinc.ita.pageobjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class BasePage {

  WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public void implicitWait(long timeToWait){
    driver.manage().timeouts().implicitlyWait(timeToWait, TimeUnit.SECONDS);
  }
}
