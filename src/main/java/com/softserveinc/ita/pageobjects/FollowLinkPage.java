package com.softserveinc.ita.pageobjects;

import Elements.*;
import org.openqa.selenium.By;

public class FollowLinkPage extends AbstractBasePage {

    private final Input searchInput = new Input(By.name("search"));
    private final Button searchButton = new Button(By.xpath("//button[contains (@class, 'search-form__submit')]"));
    private final Text searchResult = new Text(By.xpath("//p[contains (@class, 'catalog-selection__label')]"));
    private final Ul lots = new Ul(By.xpath("//ul[contains (@class, 'catalog-grid')]"));

    public FollowLinkPage() {
        super(new Img(By.xpath("//img[@alt='Rozetka Logo']")));
    }

    public void inputText(String text) {
        searchInput.sendKeys(text);
    }

    public void clickSearchButton() {
        searchButton.clickElement();
    }

    public int getResult() {

        return Integer.parseInt(searchResult.getText().replaceAll("[^0-9]", ""));
    }

    public int getLotsCount() {

        return Integer.parseInt(lots.getAttribute("childElementCount"));
    }
}
