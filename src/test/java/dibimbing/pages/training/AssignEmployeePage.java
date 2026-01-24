package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class AssignEmployeePage extends BasePage {
    private static final Logger log = LogManager.getLogger(AssignEmployeePage.class);

    @FindBy(id = "search-assigned-employee-input")
    private WebElement inputSearchListAssignedEmployee;

    @FindBy(id = "detail-assigned-employee-button")
    private WebElement btnDetailAssignEmployee;

    @FindBy(id = "button-delete-assigned-employee")
    private WebElement btnDeleteAssignEmployee;

    @FindBy(id = "modal-delete-assigned-employee-delete-button")
    private WebElement btnConfirmDeleteAssignEmployee;

    @FindBy(id = "button-update-assigned-employee")
    private WebElement btnUpdateAssignEmployee;

    @FindBy(id = "endDate")
    private WebElement inputUpdateDeadlineDate;

    @FindBy(id = "modal-update-assigned-employee-save-button")
    private WebElement btnSaveUpdateDeadlineDate;

    @FindBy(id = "assign-employee-button")
    private WebElement btnAssignEmployee;

    @FindBy(xpath = "(//input[@placeholder='Search name, ID...'])[1]")
    private WebElement inputSearchAssignedEmployeeToAssign;

    @FindBy(xpath = "(//button[@type='button'])[8]")
    private WebElement btnActionAssignEmployeeListTop;

    @FindBy(xpath = "(//input[@type='date'])[1]")
    private WebElement inputStarDate;

    @FindBy(xpath = "(//input[@type='date'])[2]")
    private WebElement inputDeadlineDate;

    @FindBy(xpath = "//button[normalize-space()='Assign Employee']")
    private WebElement btnSaveAssignEmployee;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement btnCancelAssignEmployee;

    public AssignEmployeePage(WebDriver driver) {
        super(driver);
    }

    /* =========================
       VERIFY PAGE
       ========================= */
    public void verifyAssignEmployeePageLoaded() {
        log.info("Verify Assign Employee page loaded");
        Assert.assertTrue(
                isDisplayed(inputSearchListAssignedEmployee) || isDisplayed(btnAssignEmployee),
                "Assign Employee page tidak terbuka / elemen utama tidak tampil"
        );
    }

    /* =========================
       LIST ASSIGNED EMPLOYEE
       ========================= */
    public void searchAssignedEmployeeInList(String keyword) {
        log.info("Search assigned employee in list: {}", keyword);
        waitForVisibility(inputSearchListAssignedEmployee);
        clearField(inputSearchListAssignedEmployee);
        type(inputSearchListAssignedEmployee, keyword);
    }

    public void clickDetailAssignedEmployee() {
        log.info("Click Detail Assigned Employee");
        waitForClickable(btnDetailAssignEmployee);
        safeClick(btnDetailAssignEmployee);
    }

    /* =========================
       DELETE ASSIGNED EMPLOYEE
       ========================= */
    public void clickDeleteAssignedEmployee() {
        log.info("Click Delete Assigned Employee");
        waitForClickable(btnDeleteAssignEmployee);
        safeClick(btnDeleteAssignEmployee);
    }

    public void confirmDeleteAssignedEmployee() {
        log.info("Confirm Delete Assigned Employee");
        waitForClickable(btnConfirmDeleteAssignEmployee);
        safeClick(btnConfirmDeleteAssignEmployee);
    }

    public void deleteAssignedEmployeeWithConfirmation() {
        log.info("Delete assigned employee with confirmation");
        clickDeleteAssignedEmployee();
        confirmDeleteAssignedEmployee();
    }

    /* =========================
       UPDATE DEADLINE ASSIGNED EMPLOYEE
       ========================= */
    public void clickUpdateAssignedEmployee() {
        log.info("Click Update Assigned Employee");
        waitForClickable(btnUpdateAssignEmployee);
        safeClick(btnUpdateAssignEmployee);
    }

    public void updateDeadlineDate(String yyyyMmDd) {
        log.info("Update deadline date: {}", yyyyMmDd);
        waitForVisibility(inputUpdateDeadlineDate);
        clearField(inputUpdateDeadlineDate);
        type(inputUpdateDeadlineDate, yyyyMmDd);
    }

    public void clickSaveUpdateDeadline() {
        log.info("Click Save Update Deadline");
        waitForClickable(btnSaveUpdateDeadlineDate);
        safeClick(btnSaveUpdateDeadlineDate);
    }

    public void updateAssignedEmployeeDeadline(String yyyyMmDd) {
        log.info("Update assigned employee deadline endDate={}", yyyyMmDd);
        clickUpdateAssignedEmployee();
        updateDeadlineDate(yyyyMmDd);
        clickSaveUpdateDeadline();
    }

    /* =========================
       ASSIGN EMPLOYEE (CREATE)
       ========================= */
    public void clickAssignEmployeeButton() {
        log.info("Click Assign Employee button");
        waitForClickable(btnAssignEmployee);
        safeClick(btnAssignEmployee);
    }

    public void searchEmployeeToAssign(String keyword) {
        log.info("Search employee to assign: {}", keyword);
        waitForVisibility(inputSearchAssignedEmployeeToAssign);
        clearField(inputSearchAssignedEmployeeToAssign);
        type(inputSearchAssignedEmployeeToAssign, keyword);
        waitForVisibility(btnActionAssignEmployeeListTop);
        waitForClickable(btnActionAssignEmployeeListTop);
    }

    public void clickActionAssignEmployeeTopResult() {
        log.info("wait");
        By lastRowHasText = By.xpath(
                "//p[normalize-space()='List Employee']" +
                        "/following::table[1]" +
                        "//tbody/tr[contains(@class,'css-0')][last()]" +
                        "/td[normalize-space()!='']"
        );
        waitForVisibility(lastRowHasText);
        log.info("Click action assign employee top result");
        click(btnActionAssignEmployeeListTop);
    }

    public void inputStartDate(String mmDdYyyy) {
        log.info("Input start date: {}", mmDdYyyy);
        setDateInput(inputStarDate, mmDdYyyy);
    }

    public void inputDeadlineDate(String yyyyMmDd) {
        log.info("Input deadline date: {}", yyyyMmDd);
        waitForVisibility(inputDeadlineDate);
        clearField(inputDeadlineDate);
        type(inputDeadlineDate, yyyyMmDd);
    }

    public void clickSaveAssignEmployee() {
        log.info("Click Save Assign Employee");
        waitForClickable(btnSaveAssignEmployee);
        safeClick(btnSaveAssignEmployee);
    }

    public void clickCancelAssignEmployee() {
        log.info("Click Cancel Assign Employee");
        waitForClickable(btnCancelAssignEmployee);
        safeClick(btnCancelAssignEmployee);
    }

    /**
     * Flow umum Assign Employee:
     * 1) klik Assign Employee
     * 2) search employee
     * 3) click action assign top result
     * 4) isi start date & deadline
     * 5) save
     */
    public void assignEmployeeFromTopResult(String keyword, String startDate, String deadlineDate) {
        log.info("Assign employee from top result. keyword={}, start={}, deadline={}",
                keyword, startDate, deadlineDate);

        clickAssignEmployeeButton();
        searchEmployeeToAssign(keyword);
        clickActionAssignEmployeeTopResult();

        inputStartDate(startDate);
        inputDeadlineDate(deadlineDate);

        clickSaveAssignEmployee();
    }

    /* =========================
       SAFE CLICK (fallback JS)
       ========================= */
    private void safeClick(WebElement el) {
        try {
            click(el);
        } catch (Exception e) {
            log.warn("Normal click failed, fallback to JS click. Reason: {}", e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

}
