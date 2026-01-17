package dibimbing.pages.components;

import dibimbing.pages.BasePage;
import dibimbing.pages.division.DivisionListPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ToastComponent extends BasePage {
    private static final Logger log = LogManager.getLogger(ToastComponent.class);

    // ===== SUCCESS TOASTS =====
    @FindBy(xpath = "//p[normalize-space()='Success create employee']")
    private WebElement toastSuccessCreateEmployee;

    @FindBy(xpath = "//p[normalize-space()='Success update employee']")
    private WebElement toastSuccessUpdateEmployee;

    @FindBy(xpath = "//p[contains(text(),'Succes delete employee')]")
    private WebElement toastSuccessDeleteEmployee;

    @FindBy(xpath = "//p[contains(text(),'Succes activate employee account')]")
    private WebElement toastSuccessActivateEmployee;

    @FindBy(xpath = "//p[contains(text(),'Succes inactivate employee account')]")
    private WebElement toastSuccessInactivateEmployee;

    // ===== ERROR/FAILED TOASTS =====
    @FindBy(xpath = "//p[normalize-space()='Harap isi field yang wajib diisi.']")
    private WebElement toastErrorCreateEmployeeRequiredFields;

    @FindBy(xpath = "//p[normalize-space()='Please fill required field']")
    private WebElement toastErrorUpdateEmployeeRequiredFields;

    public ToastComponent(WebDriver driver) {
        super(driver);
    }

    /* =========================
       WAIT / GET TEXT
       ========================= */

    public String getSuccessCreateEmployeeText() {
        log.info("Get toast text: Success create employee");
        return getText(toastSuccessCreateEmployee);
    }

    public String getSuccessUpdateEmployeeText() {
        log.info("Get toast text: Success update employee");
        return getText(toastSuccessUpdateEmployee);
    }

    public String getSuccessDeleteEmployeeText() {
        log.info("Get toast text: Success delete employee");
        return getText(toastSuccessDeleteEmployee);
    }

    public String getSuccessActivateEmployeeText() {
        log.info("Get toast text: Success activate employee account");
        return getText(toastSuccessActivateEmployee);
    }

    public String getSuccessInactivateEmployeeText() {
        log.info("Get toast text: Success inactivate employee account");
        return getText(toastSuccessInactivateEmployee);
    }

    public String getErrorCreateEmployeeRequiredFieldsText() {
        log.info("Get toast text: Harap isi field yang wajib diisi.");
        return getText(toastErrorCreateEmployeeRequiredFields);
    }

    /* =========================
       VERIFICATION METHODS
       ========================= */

    public void verifySuccessCreateEmployeeVisible() {
        log.info("Verify toast visible: Success create employee");
        Assert.assertTrue(waitForVisibility(toastSuccessCreateEmployee).isDisplayed(),
                "Toast success create employee tidak tampil");
    }

    public void verifySuccessUpdateEmployeeVisible() {
        log.info("Verify toast visible: Success update employee");
        Assert.assertTrue(waitForVisibility(toastSuccessUpdateEmployee).isDisplayed(),
                "Toast success update employee tidak tampil");
    }

    public void verifySuccessDeleteEmployeeVisible() {
        log.info("Verify toast visible: Success update employee");
        Assert.assertTrue(waitForVisibility(toastSuccessDeleteEmployee).isDisplayed(),
                "Toast success delete employee tidak tampil.");
    }

    public void verifySuccessActivateEmployeeVisible() {
        log.info("Verify toast visible: Success activate employee account");
        Assert.assertTrue(
                waitForVisibility(toastSuccessActivateEmployee).isDisplayed(),
                "Toast success activate employee account tidak tampil"
        );
    }

    public void verifySuccessInactivateEmployeeVisible() {
        log.info("Verify toast visible: Success inactivate employee account");
        Assert.assertTrue(
                waitForVisibility(toastSuccessInactivateEmployee).isDisplayed(),
                "Toast success inactivate employee account tidak tampil"
        );
    }

    public void verifyErrorCreateEmployeeRequiredFieldsVisible() {
        log.info("Verify toast visible: Required fields error");
        Assert.assertTrue(waitForVisibility(toastErrorCreateEmployeeRequiredFields).isDisplayed(),
                "Toast error required fields tidak tampil");
    }

    public boolean isSuccessCreateEmployeeToastVisible() {
        return isDisplayed(toastSuccessCreateEmployee);
    }

    public boolean isSuccessActivateEmployeeToastVisible() {
        log.info("Check toast visible: Success activate employee account");
        return isDisplayed(toastSuccessActivateEmployee);
    }

    public boolean isSuccessInactivateEmployeeToastVisible() {
        log.info("Check toast visible: Success inactivate employee account");
        return isDisplayed(toastSuccessInactivateEmployee);
    }

    public boolean isSuccessUpdateEmployeeToastVisible() {
        log.info("Check toast visible: Success update employee");
        return isDisplayed(toastSuccessUpdateEmployee);
    }

    public boolean isErrorUpdateEmployeeRequiredFieldsVisible() {
        log.info("Check toast visible: Please fill required field");
        return isDisplayed( toastErrorUpdateEmployeeRequiredFields);
    }


}
