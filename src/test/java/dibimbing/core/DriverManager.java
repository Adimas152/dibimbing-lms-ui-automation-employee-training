package dibimbing.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        if (browser == null || browser.isBlank()) {
            browser = "chrome";
        }

        boolean isCI = "true".equalsIgnoreCase(System.getenv("GITHUB_ACTIONS"));
        WebDriver webDriver;

        switch (browser.toLowerCase()) {

            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = buildChromeOptions(isCI);
                webDriver = new ChromeDriver(options);
            }

            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = buildFirefoxOptions(isCI);
                webDriver = new FirefoxDriver(options);
            }

            default -> throw new IllegalArgumentException(
                    "Browser not supported: " + browser + " (use: chrome / firefox)"
            );
        }

        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    private static ChromeOptions buildChromeOptions(boolean isCI) {
        ChromeOptions options = new ChromeOptions();

        // 🌍 PAKSA LOCALE (INI KUNCI DATE FORMAT)
        options.addArguments("--lang=id-ID");

        // 🧱 Common hardening
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");

        if (isCI) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        return options;
    }

    private static FirefoxOptions buildFirefoxOptions(boolean isCI) {
        FirefoxOptions options = new FirefoxOptions();

        // 🌍 Locale Firefox
        options.addPreference("intl.accept_languages", "id-ID");

        if (isCI) {
            options.addArguments("-headless");
        }

        return options;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}