package com.softserveinc.ita.pageobjects.andrewobitotski;

import static com.codeborne.selenide.Selenide.open;
import static com.softserveinc.ita.utils.ReadDataFileValues.URL_MOYO_HOMEPAGE;

public class MoyoHomePage {

    public MoyoHomePage() {
        open(URL_MOYO_HOMEPAGE);
    }

    public MoyoSearchModal searchOnMoyo() {
        return new MoyoSearchModal();
    }
}
