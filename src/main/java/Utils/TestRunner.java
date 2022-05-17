package Utils;

import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.BeforeMethod;

public class TestRunner {

  @BeforeMethod
  public void openGooglePage() {
    open("https://google.com.ua");
  }

}
