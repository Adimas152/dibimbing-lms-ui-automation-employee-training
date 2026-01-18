package dibimbing.pages.employee;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddEmployeeModal extends BasePage {
    private static final Logger log = LogManager.getLogger(AddEmployeeModal.class);

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement buttonCloseModal;

    @FindBy(id = "name")
    private WebElement inputEmployeeName;

    @FindBy(id = "employeeId")
    private WebElement inputEmployeeId;

    @FindBy(id = "email")
    private WebElement inputEmployeeEmail;

    @FindBy(id = "phoneNumber")
    private WebElement inputEmployeePhoneNumber;

    @FindBy(id = "division")
    private WebElement dropdownDivision;

    @FindBy(xpath= "//option[normalize-space()='Quality Assurance Data Test Division']")
    private WebElement divisionQualityAssuranceDataTestDivision;

    @FindBy(id = "add-division-button-ghost")
    private WebElement linkAddNewDivision;

    @FindBy(id = "employeeRole")
    private WebElement inputRole;

    @FindBy(xpath = "(//span[text()='▼'])[2]")
    private WebElement dropdownAngkatan;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2024 Ganjil'])[2]")
    private WebElement angkatanInputOption2024Ganjil;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2024 Genap'])[2]")
    private WebElement angkatanInputOption2024Genap;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2025 Ganjil'])[2]")
    private WebElement angkatanInputOptionGanjil;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2025 Genap'])[2]")
    private WebElement angkatanInputOption2025Genap;

    @FindBy(id = "add-employee-gender-radio-male-text")
    private WebElement radioGenderMale;

    @FindBy(id = "add-employee-gender-radio-female-text")
    private WebElement radioGenderFemale;

    @FindBy(id= "dateOfBirth")
    private WebElement inputBirthDate;

    @FindBy(id = "address")
    private WebElement textareaAddress;

    @FindBy(id = "nik")
    private WebElement inputNik;

    @FindBy(id = "npwp")
    private WebElement inputNpwp;

    @FindBy(id = "button-add-employee-submit")
    private WebElement buttonAddEmployee;

    @FindBy(xpath = "//input[@placeholder='Employee Name']/ancestor::div[1]//p[normalize-space()='Required']")
    private WebElement employeeNameRequiredMsg;

    @FindBy(xpath = "//input[@placeholder='Employee E-mail']/ancestor::div[1]//p[normalize-space()='Required']")
    private WebElement employeeEmailRequiredMsg;

    @FindBy(xpath = "//input[@placeholder='Employee ID']/ancestor::div[1]//p[normalize-space()='Required']")
    private WebElement employeeIdRequiredMsg;

    @FindBy(xpath = "//input[@placeholder='Employee phone number']/ancestor::div[1]//p[normalize-space()='Required']")
    private WebElement employeePhoneRequiredMsg;

    @FindBy(xpath = "//*[normalize-space()='Division']/following::p[normalize-space()='Required'][1]")
    private WebElement divisionRequiredMsg;

    @FindBy(xpath = "//input[@placeholder='Employee role']/ancestor::div[1]//p[normalize-space()='Required']")
    private WebElement roleRequiredMsg;

    public AddEmployeeModal(WebDriver driver){
        super(driver);
    }

    public void closeModal() {
        log.info("Close Add Employee modal");
        click(buttonCloseModal);
    }

    public void clickSubmitAddEmployee() {
        log.info("Click submit Add Employee");
        click(buttonAddEmployee);
    }

    /* =========================
       INPUT / FILL METHODS
       ========================= */

    public void inputEmployeeName(String name) {
        log.info("Input employee name: {}", name);
        type(inputEmployeeName, name);
    }

    public void inputEmployeeId(String employeeId) {
        log.info("Input employee ID: {}", employeeId);
        type(inputEmployeeId, employeeId);
    }

    public void inputEmployeeEmail(String email) {
        log.info("Input employee email: {}", email);
        type(inputEmployeeEmail, email);
    }

    public void inputEmployeePhoneNumber(String phone) {
        log.info("Input employee phone number: {}", phone);
        type(inputEmployeePhoneNumber, phone);
    }

    public void selectDivisionQualityAssurance() {
        log.info("Select division: Quality Assurance Data Test Division");
        click(dropdownDivision);
        click(divisionQualityAssuranceDataTestDivision);
    }

    public void clickAddNewDivisionLink() {
        log.info("Click Add New Division link");
        click(linkAddNewDivision);
    }

    public void inputRole(String role) {
        log.info("Input role: {}", role);
        type(inputRole, role);
    }

    public void selectAngkatan2024Ganjil() {
        log.info("Select angkatan: 2024 Ganjil");
        click(dropdownAngkatan);
        click(angkatanInputOption2024Ganjil);
    }

    public void selectAngkatan2024Genap() {
        log.info("Select angkatan: 2024 Genap");
        click(dropdownAngkatan);
        click(angkatanInputOption2024Genap);
    }

    public void selectAngkatan2025Ganjil() {
        log.info("Select angkatan: 2025 Ganjil");
        click(dropdownAngkatan);
        click(angkatanInputOptionGanjil);
    }

    public void selectAngkatan2025Genap() {
        log.info("Select angkatan: 2025 Genap");
        click(dropdownAngkatan);
        click(angkatanInputOption2025Genap);
    }

    public void selectGenderMale() {
        log.info("Select gender: Male");
        click(radioGenderMale);
    }

    public void selectGenderFemale() {
        log.info("Select gender: Female");
        click(radioGenderFemale);
    }

    public void inputBirthDate(String birthDate) {
        log.info("Input birth date: {}", birthDate);
        type(inputBirthDate, birthDate);
    }

    public void inputAddress(String address) {
        log.info("Input address");
        type(textareaAddress, address);
    }

    public void inputNik(String nik) {
        log.info("Input NIK: {}", nik);
        type(inputNik, nik);
    }

    public void inputNpwp(String npwp) {
        log.info("Input NPWP: {}", npwp);
        type(inputNpwp, npwp);
    }

    /* =========================
       HIGH-LEVEL FLOW METHODS
       ========================= */

    // Minimal mandatory fields
    public void fillMandatoryFields(String name, String employeeId, String email, String phone, String role) {
        inputEmployeeName(name);
        inputEmployeeId(employeeId);
        inputEmployeeEmail(email);
        inputEmployeePhoneNumber(phone);
        click(divisionQualityAssuranceDataTestDivision);
        inputRole(role);
    }

    public void fillAllFields(
            String name, String employeeId, String email, String phone,
            String role, String gender,
            String birthDate, String address, String nik, String npwp
    ) {
        inputEmployeeName(name);
        inputEmployeeId(employeeId);
        inputEmployeeEmail(email);
        inputEmployeePhoneNumber(phone);

        // pilih division
        selectDivisionQualityAssurance();
        inputRole(role);
        selectAngkatan2024Ganjil();

        if (gender != null && !gender.isBlank()) {
            if (gender.equalsIgnoreCase("Male")) selectGenderMale();
            else if (gender.equalsIgnoreCase("Female")) selectGenderFemale();
        }

        if (birthDate != null && !birthDate.isBlank()) inputBirthDate(birthDate);
        if (address != null && !address.isBlank()) inputAddress(address);
        if (nik != null && !nik.isBlank()) inputNik(nik);
        if (npwp != null && !npwp.isBlank()) inputNpwp(npwp);
    }

    public void submitCreateEmployee() {
        clickSubmitAddEmployee();
    }

    /* =========================
       REQUIRED VALIDATION ASSERTIONS
       ========================= */

    public void verifyNameRequiredMessageVisible() {
        log.info("Verify required message for Name visible");
        Assert.assertTrue(waitForVisibility(employeeNameRequiredMsg).isDisplayed(), "Required message Name tidak tampil");
    }

    public void verifyEmailRequiredMessageVisible() {
        log.info("Verify required message for Email visible");
        Assert.assertTrue(waitForVisibility(employeeEmailRequiredMsg).isDisplayed(), "Required message Email tidak tampil");
    }

    public void verifyEmployeeIdRequiredMessageVisible() {
        log.info("Verify required message for Employee ID visible");
        Assert.assertTrue(waitForVisibility(employeeIdRequiredMsg).isDisplayed(), "Required message Employee ID tidak tampil");
    }

    public void verifyPhoneRequiredMessageVisible() {
        log.info("Verify required message for Phone visible");
        Assert.assertTrue(waitForVisibility(employeePhoneRequiredMsg).isDisplayed(), "Required message Phone tidak tampil");
    }

    public void verifyDivisionRequiredMessageVisible() {
        log.info("Verify required message for Division visible");
        Assert.assertTrue(waitForVisibility(divisionRequiredMsg).isDisplayed(), "Required message Division tidak tampil");
    }

    public void verifyRoleRequiredMessageVisible() {
        log.info("Verify required message for Role visible");
        Assert.assertTrue(waitForVisibility(roleRequiredMsg).isDisplayed(), "Required message Role tidak tampil");
    }

    public void verifyAllRequiredMessagesVisible() {
        verifyNameRequiredMessageVisible();
        verifyEmployeeIdRequiredMessageVisible();
        verifyEmailRequiredMessageVisible();
        verifyPhoneRequiredMessageVisible();
        verifyDivisionRequiredMessageVisible();
        verifyRoleRequiredMessageVisible();
    }

}
