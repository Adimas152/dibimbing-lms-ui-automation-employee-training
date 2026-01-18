package dibimbing.tests.base;

import dibimbing.core.ConfigReader;
import dibimbing.core.DriverManager;
import dibimbing.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void loadConfig() {
        log.info("BASE_URL loaded: {}", ConfigReader.get("BASE_URL"));
        log.info("BROWSER loaded: {}", ConfigReader.getOrDefault("BROWSER", "chrome"));
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("") String browser) {
        String selectedBrowser = (browser != null && !browser.isBlank())
                ? browser
                : ConfigReader.getOrDefault("BROWSER", "chrome");

        DriverManager.initDriver(selectedBrowser);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ConfigReader.get("BASE_URL"));
    }

    protected void login() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(
                ConfigReader.get("EMAIL"),
                ConfigReader.get("PASSWORD")
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
