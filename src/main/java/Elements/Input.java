package Elements;

import org.openqa.selenium.By;

public class Input extends AbstractBaseElement{
    public Input(By element) {
        super(element);
    }

    public void sendKeys(String text){
        waitAndGetElement().sendKeys(text);
    }
}
