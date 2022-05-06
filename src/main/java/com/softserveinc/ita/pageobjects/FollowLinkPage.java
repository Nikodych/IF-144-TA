package com.softserveinc.ita.pageobjects;

import Elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FollowLinkPage extends BasePage {

    private final Input searchInput = new Input(driver, By.name("search"));
    private final Button searchButton = new Button(driver, By.xpath("//button[contains (@class, 'search-form__submit')]"));
    private final Text searchResult = new Text(driver, By.xpath("//p[contains (@class, 'catalog-selection__label')]"));
    private final Ul lots = new Ul(driver, By.xpath("//ul[contains (@class, 'catalog-grid')]"));

    public FollowLinkPage(final WebDriver driver) {
        super(driver);
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

    @Override
    public boolean isPageOpened() {

        return new Img(driver, By.xpath("//img[@alt='Rozetka Logo']")).areElementsDisplayed();
    }
}
