package dibimbing.pages.components;

import dibimbing.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

    @FindBy(xpath = "//p[contains(text(),'Success delete division')]")
    private WebElement toastSuccessDeleteDivision;

    // ===== ERROR/FAILED TOASTS =====
    @FindBy(xpath = "//p[normalize-space()='Harap isi field yang wajib diisi.']")
    private WebElement toastErrorCreateEmployeeRequiredFields;

    @FindBy(xpath = "//p[normalize-space()='Please fill required field']")
    private WebElement toastErrorUpdateEmployeeRequiredFields;

    @FindBy(xpath = "//p[contains(text(),'Success create division')]")
    private WebElement toastSuccessCreateDivision;

    @FindBy(xpath = "//p[contains(text(),'Success update division')]")
    private WebElement toastSuccessUpdateDivision;

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

    public String getSuccessCreateDivisionText() {
        log.info("Get toast text: Success create division");
        return getText(toastSuccessCreateDivision);
    }

    public String getSuccessUpdateDivisionText() {
        log.info("Get toast text: Success update division");
        return getText(toastSuccessUpdateDivision);
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

    public String getSuccessDeleteDivisionText() {
        log.info("Get toast text: Success delete division");
        return getText(toastSuccessDeleteDivision);
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

    public void verifySuccessCreateDivisionVisible() {
        log.info("Verify toast visible: Success create division");
        Assert.assertTrue(waitForVisibility(toastSuccessCreateDivision).isDisplayed(),
                "Toast success create division tidak tampil");
    }

    public void verifySuccessUpdateDivisionVisible() {
        log.info("Verify toast visible: Success update division");
        Assert.assertTrue(waitForVisibility(toastSuccessUpdateDivision).isDisplayed(),
                "Toast success update division tidak tampil");
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

    public boolean isSuccessCreateDivisionToastVisible() {
        log.info("Check toast visible: Success create division");
        return isDisplayed(toastSuccessCreateDivision);
    }

    public boolean isSuccessUpdateDivisionToastVisible() {
        log.info("Check toast visible: Success update division");
        return isDisplayed(toastSuccessUpdateDivision);
    }

    public boolean isSuccessDeleteDivisionToastVisible() {
        log.info("Check toast visible: Success delete division");
        return isDisplayed(toastSuccessDeleteDivision);
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
