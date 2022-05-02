package com.softserveinc.ita.pageobjects.andrewobitotski;

import com.softserveinc.ita.utils.ReadConfigFileValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MoyoHomePage extends AbstractBasePage {

    public MoyoHomePage(WebDriver driver) {
        driver.get(ReadConfigFileValues.URL_MOYO_HOMEPAGE);
        PageFactory.initElements(driver, this);
    }

    public MoyoSearchModal searchOnMoyo() {
        return new MoyoSearchModal(driver);
    }
}
