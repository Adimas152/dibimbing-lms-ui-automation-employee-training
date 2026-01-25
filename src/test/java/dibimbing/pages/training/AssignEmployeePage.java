package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.Keys;

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

    private By deadlineDateInput =
            By.xpath("(//input[@type='date'])[2]");

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

//    public void updateDeadlineDate(String yyyyMmDd) {
//        log.info("Update deadline date: {}", yyyyMmDd);
//        waitForVisibility(inputUpdateDeadlineDate);
//        clearField(inputUpdateDeadlineDate);
//        type(inputUpdateDeadlineDate, yyyyMmDd);
//    }

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

//    public void inputStartDate(String mmDdYyyy) {
//        log.info("Input start date: {}", mmDdYyyy);
//        setDateInput(inputStarDate, mmDdYyyy);
//    }

//    public void inputDeadlineDate(String yyyyMmDd) {
//        log.info("Input deadline date (HTML): {}", yyyyMmDd);
//        setDateInput(inputDeadlineDate, yyyyMmDd);
//    }

//    public void inputDeadlineDate(String date) {
//        log.info("Input deadline date: {}", date);
//
//        WebElement deadline = inputDeadlineDate; // @FindBy kamu
//
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].value = arguments[1];" +
//                        "arguments[0].dispatchEvent(new Event('change'));" +
//                        "arguments[0].dispatchEvent(new Event('blur'));",
//                deadline,
//                date
//        );
//
//        // 🔒 Safety assert (WAJIB pas debug)
//        String actual = deadline.getAttribute("value");
//        log.info("Actual deadline value in DOM: {}", actual);
//    }


//    public void inputDeadlineDate(String ddMmYyyy) {
//        log.info("Input deadline date: {}", ddMmYyyy);
//        waitForVisibility(inputDeadlineDate);
//        clearField(inputDeadlineDate);
//        type(inputDeadlineDate, ddMmYyyy);
//    }

//    public void inputDeadlineDate(String yyyyMmDd) {
//        log.info("Set deadline date via JS (CI safe): {}", yyyyMmDd);
//        waitForVisibility(inputDeadlineDate);
//        setDateViaJs(inputDeadlineDate, yyyyMmDd);
//    }

//    public void inputDeadlineDate(String yyyyMmDd) {
//        log.info("Input deadline date via REAL USER FLOW: {}", yyyyMmDd);
//
//        // split yyyy-MM-dd
//        String[] parts = yyyyMmDd.split("-");
//        String year = parts[0];
//        String month = parts[1];
//        String day = parts[2];
//
//        WebElement deadline =
//                wait.until(ExpectedConditions.elementToBeClickable(
//                        By.xpath("(//input[@type='date'])[2]")
//                ));
//
//        deadline.click();
//        deadline.sendKeys(Keys.chord(Keys.CONTROL, "a"));
//        deadline.sendKeys(Keys.DELETE);
//
//        // ⚠️ INPUT SEPERTI USER
//        deadline.sendKeys(day);
//        deadline.sendKeys(month);
//        deadline.sendKeys(year);
//
//        // blur → commit React state
//        deadline.sendKeys(Keys.TAB);
//
//        log.info("Deadline after user input = {}", deadline.getAttribute("value"));
//    }

//    public void inputDeadlineDate(String yyyyMmDd) {
//        log.info("Set deadline date via NATIVE setter (React-safe): {}", yyyyMmDd);
//
//        WebElement deadline = wait.until(
//                ExpectedConditions.presenceOfElementLocated(
//                        By.xpath("(//input[@type='date'])[2]")
//                )
//        );
//
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        js.executeScript("""
//        const input = arguments[0];
//        const value = arguments[1];
//
//        const nativeSetter = Object.getOwnPropertyDescriptor(
//            window.HTMLInputElement.prototype,
//            'value'
//        ).set;
//
//        nativeSetter.call(input, value);
//
//        input.dispatchEvent(new Event('input', { bubbles: true }));
//        input.dispatchEvent(new Event('change', { bubbles: true }));
//    """, deadline, yyyyMmDd);
//
//        log.info("Deadline after set = {}", deadline.getAttribute("value"));
//    }

    public void inputDeadlineDate(String yyyyMmDd) {
        log.info("Set deadline date via NATIVE setter (React-safe): {}", yyyyMmDd);

        WebElement deadline = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//input[@type='date'])[2]")
                )
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
        const input = arguments[0];
        const value = arguments[1];

        const nativeSetter = Object.getOwnPropertyDescriptor(
            window.HTMLInputElement.prototype,
            'value'
        ).set;

        nativeSetter.call(input, value);

        input.dispatchEvent(new Event('input', { bubbles: true }));
        input.dispatchEvent(new Event('change', { bubbles: true }));
    """, deadline, yyyyMmDd);

        log.info("Deadline after set = {}", deadline.getAttribute("value"));
    }
    public void updateDeadlineDate(String yyyyMmDd) {
        log.info("Update deadline date (RESET + SET): {}", yyyyMmDd);

        WebElement deadline = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//input[@type='date'])[1]") // modal update hanya 1 input
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("""
        const el = arguments[0];
        const value = arguments[1];

        const nativeSetter = Object.getOwnPropertyDescriptor(
            window.HTMLInputElement.prototype,
            'value'
        ).set;

        // 1️⃣ RESET REACT STATE
        nativeSetter.call(el, '');
        el.dispatchEvent(new Event('input', { bubbles: true }));
        el.dispatchEvent(new Event('change', { bubbles: true }));

        // 2️⃣ SET VALUE BARU
        nativeSetter.call(el, value);
        el.dispatchEvent(new Event('input', { bubbles: true }));
        el.dispatchEvent(new Event('change', { bubbles: true }));
        el.dispatchEvent(new Event('blur', { bubbles: true }));
    """, deadline, yyyyMmDd);

        // VALIDASI (WAJIB)
        String actual = deadline.getAttribute("value");
        log.info("Updated deadline DOM value = {}", actual);
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
//    public void assignEmployeeFromTopResult(String keyword, String startDate, String deadlineDate) {
//        log.info("Assign employee from top result. keyword={}, start={}, deadline={}",
//                keyword, startDate, deadlineDate);
//
//        clickAssignEmployeeButton();
//        searchEmployeeToAssign(keyword);
//        clickActionAssignEmployeeTopResult();
//
//        inputStartDate(startDate);
//        inputDeadlineDate(deadlineDate);
//
//        clickSaveAssignEmployee();
//    }

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
