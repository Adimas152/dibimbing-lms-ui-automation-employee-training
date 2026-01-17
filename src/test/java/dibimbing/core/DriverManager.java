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
        if (browser == null || browser.isBlank()) browser = "chrome";

        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();

                String githubActions = System.getenv("GITHUB_ACTIONS");
                if ("true".equalsIgnoreCase(githubActions)) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    webDriver = new ChromeDriver(options);
                } else {
                    webDriver = new ChromeDriver();
                }
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();

                String githubActions = System.getenv("GITHUB_ACTIONS");
                if ("true".equalsIgnoreCase(githubActions)) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("-headless");
                    webDriver = new FirefoxDriver(options);
                } else {
                    webDriver = new FirefoxDriver();
                }
            }
            default -> throw new IllegalArgumentException(
                    "Browser not supported: " + browser + " (use: chrome/firefox)"
            );
        }

        driver.set(webDriver);
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
