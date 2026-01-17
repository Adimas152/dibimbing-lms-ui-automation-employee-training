package dibimbing.tests.base;

import dibimbing.core.ConfigReader;
import dibimbing.core.DriverManager;
import dibimbing.pages.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static Properties config;

    @BeforeSuite(alwaysRun = true)
    public void loadConfig() {
        String env = System.getProperty("env"); // -Denv=staging
        env = (env == null || env.isEmpty()) ? "staging" : env;
        config = ConfigReader.loadProperties(env);
        log.info("Loaded config env: {}", env);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initDriver(browser);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(config.getProperty("test.url"));
    }

    protected void login() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(config);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}