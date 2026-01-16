package dibimbing.pages.employee;

import dibimbing.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class AddEmployeeModalPage extends BasePage {
    private static final Logger log = LogManager.getLogger(AddEmployeeModalPage.class);

    @FindBy(id = "name")
    private WebElement employeeNameInput;

    @FindBy(id = "employeeId")
    private WebElement employeeIdInput;

    @FindBy(id = "email")
    private WebElement employeeEmailInput;

    @FindBy(id = "phoneNumber")
    private WebElement employeePhoneNumberInput;

    @FindBy(id = "division")
    private WebElement dropdownDivision;

    @FindBy(xpath = "//*[@id=\"add-employee-division-select-option-22\"]")
    private WebElement dropdownOptionDivisionQa;

    @FindBy(id = "employeeRole")
    private WebElement employeeRoleInput;

    @FindBy(id = "button-add-employee-submit")
    private WebElement buttonAddEmployeeSubmit;

    @FindBy(xpath = "//input[@id='email']/ancestor::div[1]//p[normalize-space(.)='Required']")
    private WebElement errorEmailRequired;

    public AddEmployeeModalPage(WebDriver driver){
        super(driver);
    }

    public void verifyFormAddEmployeeModalVisible () {
        log.info("Verify form add employee (btn submit) visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(buttonAddEmployeeSubmit));
        Assert.assertTrue(buttonAddEmployeeSubmit.isDisplayed(), "Button submit add employee tidak tampil");
    }

    public void addNewEmployee(String name, String empId, String email, String phone, String role) {
        log.info("Form input add New Employee");
        employeeNameInput.clear();
        employeeNameInput.sendKeys(name);
        employeeIdInput.clear();
        employeeIdInput.sendKeys(empId);
        employeeEmailInput.clear();
        employeeEmailInput.sendKeys(email);
        employeePhoneNumberInput.clear();
        employeePhoneNumberInput.sendKeys(phone);
        dropdownDivision.click();
        dropdownOptionDivisionQa.click();
        employeeRoleInput.clear();
        employeeRoleInput.sendKeys(role);
        buttonAddEmployeeSubmit.click();
    }

    public void verifyEmailRequiredErrorVisible () {
        log.info("Verify Email Required Error visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(errorEmailRequired));
        Assert.assertTrue(errorEmailRequired.isDisplayed(), "Button submit add employee tidak tampil");
    }




}
