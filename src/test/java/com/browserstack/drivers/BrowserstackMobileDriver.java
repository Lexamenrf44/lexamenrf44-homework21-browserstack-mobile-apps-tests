package com.browserstack.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.browserstack.config.Credentials;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    public static URL getBrowserstackUrl() {
        try {
            return new URL("http://hub.browserstack.com/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities capability) {

        // Set your access credentials
        String user = Credentials.config.user();
        String key = Credentials.config.key();
        String app = Credentials.config.app();
        capability.setCapability("browserstack.user", user);
        capability.setCapability("browserstack.key", key);

        // Set URL of the application under test
        capability.setCapability("app", app);

        // Specify device and os_version for testing
        capability.setCapability("device", "Google Pixel 3");
        capability.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        capability.setCapability("project", "First Java Project");
        capability.setCapability("build", "browserstack-build-1");
        capability.setCapability("name", "first_test");


        return new AndroidDriver(getBrowserstackUrl(), capability);
    }

}
