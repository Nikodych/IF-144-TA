package Utils;

import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class TestRunner {

    @BeforeMethod
    public void openHomePage() {
            open("https://www.google.com/");
        }
}
