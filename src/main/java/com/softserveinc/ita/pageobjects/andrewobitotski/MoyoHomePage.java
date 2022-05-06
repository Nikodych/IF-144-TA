package com.softserveinc.ita.pageobjects.andrewobitotski;

import org.openqa.selenium.WebDriver;

import static com.softserveinc.ita.utils.ReadConfigFileValues.URL_MOYO_HOMEPAGE;
import static org.openqa.selenium.support.PageFactory.initElements;

public class MoyoHomePage extends BasePage {

    public MoyoHomePage(WebDriver driver) {
        driver.get(URL_MOYO_HOMEPAGE);
        initElements(driver, this);
    }

    public MoyoSearchModal searchOnMoyo() {
        return new MoyoSearchModal(driver);
    }
}
