package Utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;

public class TestRunner {

    @BeforeMethod
    public final void setUP() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "maximized";
    }

    @BeforeMethod
    public final void tearDown() {
        Selenide.closeWebDriver();
    }
}
