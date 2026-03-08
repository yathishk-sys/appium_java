package framework.config;

import java.util.Objects;

public final class BrowserStackConfig {
    private final String username;
    private final String apiKey;
    private final String appCode;
    private final String device;
    private final String osVersion;
    private final String projectName;
    private final String buildName;
    private final String sessionName;

    private BrowserStackConfig(
            String username,
            String apiKey,
            String appCode,
            String device,
            String osVersion,
            String projectName,
            String buildName,
            String sessionName
    ) {
        this.username = username;
        this.apiKey = apiKey;
        this.appCode = appCode;
        this.device = device;
        this.osVersion = osVersion;
        this.projectName = projectName;
        this.buildName = buildName;
        this.sessionName = sessionName;
    }

    public static BrowserStackConfig fromSystemOrEnv() {
        return new BrowserStackConfig(
                required("bs.username", "BROWSERSTACK_USERNAME"),
                required("bs.apiKey", "BROWSERSTACK_API_KEY"),
                required("bs.appCode", "BROWSERSTACK_APP_CODE"),
                optional("bs.device", "BROWSERSTACK_DEVICE", "Google Pixel 7"),
                optional("bs.osVersion", "BROWSERSTACK_OS_VERSION", "13.0"),
                optional("bs.projectName", "BROWSERSTACK_PROJECT", "Appium Java Framework"),
                optional("bs.buildName", "BROWSERSTACK_BUILD", "Local Build"),
                optional("bs.sessionName", "BROWSERSTACK_SESSION", "Smoke Test")
        );
    }

    private static String required(String property, String env) {
        String value = optional(property, env, null);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing required BrowserStack credential/config. Set -D" + property + " or env " + env
            );
        }
        return value;
    }

    private static String optional(String property, String env, String defaultValue) {
        String propValue = System.getProperty(property);
        if (propValue != null && !propValue.isBlank()) {
            return propValue.trim();
        }

        String envValue = System.getenv(env);
        if (envValue != null && !envValue.isBlank()) {
            return envValue.trim();
        }

        return defaultValue;
    }

    public String username() {
        return username;
    }

    public String apiKey() {
        return apiKey;
    }

    public String appCode() {
        return appCode;
    }

    public String device() {
        return device;
    }

    public String osVersion() {
        return osVersion;
    }

    public String projectName() {
        return projectName;
    }

    public String buildName() {
        return buildName;
    }

    public String sessionName() {
        return sessionName;
    }

    @Override
    public String toString() {
        return "BrowserStackConfig{" +
                "username='" + username + '\'' +
                ", apiKey='" + mask(apiKey) + '\'' +
                ", appCode='" + appCode + '\'' +
                ", device='" + device + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", projectName='" + projectName + '\'' +
                ", buildName='" + buildName + '\'' +
                ", sessionName='" + sessionName + '\'' +
                '}';
    }

    private String mask(String secret) {
        Objects.requireNonNull(secret, "secret");
        if (secret.length() <= 4) {
            return "****";
        }
        return "*".repeat(secret.length() - 4) + secret.substring(secret.length() - 4);
    }
}
