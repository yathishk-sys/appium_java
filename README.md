# Appium Java BrowserStack Framework

Lightweight Appium Java test framework designed for BrowserStack App Automate.

## Compatibility

- JDK: **17**
- Maven: **3.9.3**
- Appium server on BrowserStack: **2.19.0** (set via capability)
- Appium Java Client: **9.2.3**

## Project Structure

```text
src/test/java
├── framework
│   ├── config
│   │   └── BrowserStackConfig.java
│   └── driver
│       ├── BaseTest.java
│       └── DriverFactory.java
└── tests
    └── BrowserStackSmokeTest.java
```

## Required BrowserStack Inputs

You said you have:
- username
- api_key
- app_code (`bs://...`)

The framework reads those values from JVM properties first, then environment variables.

### Option A: JVM properties

```bash
mvn test \
  -Dbs.username="<YOUR_USERNAME>" \
  -Dbs.apiKey="<YOUR_API_KEY>" \
  -Dbs.appCode="<YOUR_APP_CODE>"
```

### Option B: Environment variables

```bash
export BROWSERSTACK_USERNAME="<YOUR_USERNAME>"
export BROWSERSTACK_API_KEY="<YOUR_API_KEY>"
export BROWSERSTACK_APP_CODE="<YOUR_APP_CODE>"
mvn test
```

## Optional Overrides

You can also override device/session metadata:

- `bs.device` / `BROWSERSTACK_DEVICE` (default: `Google Pixel 7`)
- `bs.osVersion` / `BROWSERSTACK_OS_VERSION` (default: `13.0`)
- `bs.projectName` / `BROWSERSTACK_PROJECT`
- `bs.buildName` / `BROWSERSTACK_BUILD`
- `bs.sessionName` / `BROWSERSTACK_SESSION`

Example:

```bash
mvn test \
  -Dbs.username="<YOUR_USERNAME>" \
  -Dbs.apiKey="<YOUR_API_KEY>" \
  -Dbs.appCode="<YOUR_APP_CODE>" \
  -Dbs.device="Samsung Galaxy S23" \
  -Dbs.osVersion="13.0" \
  -Dbs.projectName="My Project" \
  -Dbs.buildName="Build-001" \
  -Dbs.sessionName="Login smoke"
```

## Notes

- Hub URL is generated as `https://<username>:<apiKey>@hub-cloud.browserstack.com/wd/hub`.
- `bstack:options` includes `appiumVersion=2.19.0`, debug, and network logs.
