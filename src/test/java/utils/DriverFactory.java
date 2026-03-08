package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
    public static final String USERNAME = "yathishk_caJBsy";
    public static final String ACCESS_KEY = "1ACR76TePCVkdoM9Cyfj";
    public static final String APP_URL = "bs://30381818b222f8494b426a57a2f8b862e4716b40";

    public static AndroidDriver createDriver() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName("Google Pixel 7");
        options.setPlatformVersion("13.0");
        options.setApp(APP_URL);
        // options.setCapability("app", "bs://sample.app");

        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", USERNAME);
        bstackOptions.put("accessKey", ACCESS_KEY);
        bstackOptions.put("buildName", "BrowserStack Build");
        bstackOptions.put("sessionName", "First Mobile Test");

        options.setCapability("bstack:options", bstackOptions);
        // options.setCapability("app", "bs://30381818b222f8494b426a57a2f8b862e4716b40");

        try {
            return new AndroidDriver(
                    new URL("https://hub.browserstack.com/wd/hub"),
                    options
            );
        } catch (Exception e) {
            System.err.println("Error creating driver: " + e.getMessage());
            throw e;
        }
    }
}