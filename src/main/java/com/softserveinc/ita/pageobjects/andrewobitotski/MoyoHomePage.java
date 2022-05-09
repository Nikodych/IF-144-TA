package com.softserveinc.ita.pageobjects.andrewobitotski;

import static com.softserveinc.ita.utils.ReadDataFileValues.URL_MOYO_HOMEPAGE;
import static org.openqa.selenium.support.PageFactory.initElements;

public class MoyoHomePage extends BasePage {

    public MoyoHomePage() {
        driver.get(URL_MOYO_HOMEPAGE);
        initElements(driver, this);
    }

    public MoyoSearchModal searchOnMoyo() {
        return new MoyoSearchModal();
    }
}
