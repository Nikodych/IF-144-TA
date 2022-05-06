package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input extends AbstractElement {


    public Input(WebDriver driver, By locator) {
        super(driver, locator);
    }

    public void sendKeys(String text) {
        getClickableElement().sendKeys(text);
    }
}
