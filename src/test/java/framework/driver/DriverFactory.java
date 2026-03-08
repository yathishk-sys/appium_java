package framework.driver;

import framework.config.BrowserStackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private DriverFactory() {
    }

    public static AndroidDriver createAndroidDriver() {
        BrowserStackConfig config = BrowserStackConfig.fromSystemOrEnv();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setApp(config.appCode());
        options.setDeviceName(config.device());
        options.setPlatformVersion(config.osVersion());
        options.setAutomationName("UiAutomator2");
        options.setNewCommandTimeout(Duration.ofSeconds(120));

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("projectName", config.projectName());
        bstackOptions.put("buildName", config.buildName());
        bstackOptions.put("sessionName", config.sessionName());
        bstackOptions.put("appiumVersion", "2.19.0");
        bstackOptions.put("debug", true);
        bstackOptions.put("networkLogs", true);

        options.setCapability("bstack:options", bstackOptions);

        return new AndroidDriver(remoteUrl(config), options);
    }

    private static URL remoteUrl(BrowserStackConfig config) {
        try {
            URI uri = URI.create(String.format(
                    "https://%s:%s@hub-cloud.browserstack.com/wd/hub",
                    config.username(),
                    config.apiKey()
            ));
            return uri.toURL();
        } catch (MalformedURLException ex) {
            throw new IllegalStateException("Failed to build BrowserStack hub URL", ex);
        }
    }
}
