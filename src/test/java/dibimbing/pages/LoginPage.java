package dibimbing.pages;


import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoginPage extends BasePage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(id = "input-username-or-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "button-sign-in")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void login(String email, String password) {
        log.info("Logging in with email: {}", email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailInput));

        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginButton.click();
    }


    // overload: tinggal panggil loginPage.login()
    public void login(Properties config) {
        String email = config.getProperty("test.email");
        String password = config.getProperty("test.password");
        login(email, password);
    }
}
