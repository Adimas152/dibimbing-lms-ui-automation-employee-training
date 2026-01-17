package dibimbing.pages.employee;

import dibimbing.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DetailEmployeePage extends BasePage {
    private static final Logger log = LogManager.getLogger(DetailEmployeePage.class);

    @FindBy(id = "delete-employee-button")
    private WebElement btnDeleteEmployee;

    @FindBy(id = "activation-employee-button")
    private WebElement btnStatusEmployee;

    @FindBy(id = "resend-email-button")
    private WebElement btnResendEmailAccount;

    @FindBy(id = "edit-employee-button")
    private WebElement btnEditEmployee;

    @FindBy(xpath = "//div[text()='Active']")
    private WebElement badgeEmployeeStatusActive;

    @FindBy(xpath = "//div[text()='Inactive']")
    private WebElement badgeEmployeeStatusInactive;

    @FindBy(id = "activation-employee-confirm-button")
    private WebElement btnConfirmStatusEmployee;

    @FindBy(id = "activation-employee-cancel-button")
    private WebElement btnCancelStatusEmployee;

    @FindBy(id = "activation-employee-close-button")
    private WebElement btnCloseStatusEmployeeModal;

    @FindBy(id = "cancel-delete-button")
    private WebElement btnCancelDeleteEmployee;

    @FindBy(id = "confirm-delete-button")
    private WebElement btnConfirmDeleteEmployee;

    @FindBy(id = "delete-employee-close-button")
    private WebElement btnCloseDeleteEmployeeModal;

    public DetailEmployeePage(WebDriver driver){
        super(driver);
    }


    public void verifyDetailEmployeePageLoaded() {
        log.info("Verify Detail Employee Page loaded");
        Assert.assertTrue(
                isDisplayed(btnEditEmployee) || isDisplayed(btnDeleteEmployee),
                "Detail Employee page tidak terbuka / elemen utama tidak tampil"
        );
    }

    public void clickDeleteEmployee() {
        log.info("Click Delete Employee button");
        click(btnDeleteEmployee);
    }

    public void clickStatusEmployeeButton() {
        log.info("Click Activate/Inactive button");
        click(btnStatusEmployee);
    }

    public void clickResendEmailAccount() {
        log.info("Click Resend Email Account button");
        click(btnResendEmailAccount);
    }

    public void clickEditEmployee() {
        log.info("Click Edit Employee button");
        click(btnEditEmployee);
    }

    /* =========================
       STATUS BADGE CHECK
       ========================= */

    public boolean isEmployeeStatusActive() {
        log.info("Check employee status: Active");
        return isDisplayed(badgeEmployeeStatusActive);
    }

    public boolean isEmployeeStatusInactive() {
        log.info("Check employee status: Inactive");
        return isDisplayed(badgeEmployeeStatusInactive);
    }

    public void verifyEmployeeStatusActive() {
        log.info("Verify employee status is Active");
        Assert.assertTrue(isEmployeeStatusActive(), "Status employee bukan Active");
    }

    public void verifyEmployeeStatusInactive() {
        log.info("Verify employee status is Inactive");
        Assert.assertTrue(isEmployeeStatusInactive(), "Status employee bukan Inactive");
    }

    /* =========================
       ACTIVATION MODAL ACTIONS
       ========================= */

    public void confirmChangeStatusEmployee() {
        log.info("Confirm change status employee");
        click(btnConfirmStatusEmployee);
    }

    public void cancelChangeStatusEmployee() {
        log.info("Cancel change status employee");
        click(btnCancelStatusEmployee);
    }

    public void closeChangeStatusEmployeeModal() {
        log.info("Close activation status modal");
        click(btnCloseStatusEmployeeModal);
    }

    public void verifyActivationModalVisible() {
        log.info("Verify activation modal visible");
        Assert.assertTrue(
                isDisplayed(btnConfirmStatusEmployee) && isDisplayed(btnCancelStatusEmployee),
                "Activation modal tidak tampil"
        );
    }

    /* =========================
       DELETE MODAL ACTIONS
       ========================= */

    public void confirmDeleteEmployee() {
        log.info("Confirm delete employee");
        click(btnConfirmDeleteEmployee);
    }

    public void cancelDeleteEmployee() {
        log.info("Cancel delete employee");
        click(btnCancelDeleteEmployee);
    }

    public void closeDeleteEmployeeModal() {
        log.info("Close delete employee modal");
        click(btnCloseDeleteEmployeeModal);
    }

    public void verifyDeleteModalVisible() {
        log.info("Verify delete modal visible");
        Assert.assertTrue(
                isDisplayed(btnConfirmDeleteEmployee) && isDisplayed(btnCancelDeleteEmployee),
                "Delete modal tidak tampil"
        );
    }

}
