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

    // ===== ERROR/FAILED TOASTS =====
    @FindBy(xpath = "//p[normalize-space()='Harap isi field yang wajib diisi.']")
    private WebElement toastErrorCreateEmployeeRequiredFields;

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

    public void verifyErrorCreateEmployeeRequiredFieldsVisible() {
        log.info("Verify toast visible: Required fields error");
        Assert.assertTrue(waitForVisibility(toastErrorCreateEmployeeRequiredFields).isDisplayed(),
                "Toast error required fields tidak tampil");
    }


    public boolean isSuccessCreateEmployeeToastVisible() {
        return isDisplayed(toastSuccessCreateEmployee);
    }


}
