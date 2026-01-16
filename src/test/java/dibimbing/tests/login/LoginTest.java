package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.pages.DasboardPage.DashboardPage;
import dibimbing.pages.auth.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Login", groups = {"regression", "test-log"})
    public void tc001_testLogin() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        loginPage.login(config.getProperty("test.email"), config.getProperty("test.password"));
        dashboardPage.verifyEmployeeMenuVisible();

    }
}
