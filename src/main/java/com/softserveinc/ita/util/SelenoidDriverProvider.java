package com.softserveinc.ita.util;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class SelenoidDriverProvider implements WebDriverProvider {

    private static String browserName;
    private static String browserVersion;

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        var desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("browserName", browserName);
        desiredCapabilities.setCapability("browserVersion", browserVersion);
        desiredCapabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true,
                "videoCodec", "mpeg4",
                "enableLog", true
        ));

        try {
            return new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    desiredCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUpBrowser(String name, String version) {
        browserName = name;
        browserVersion = version;
    }
}