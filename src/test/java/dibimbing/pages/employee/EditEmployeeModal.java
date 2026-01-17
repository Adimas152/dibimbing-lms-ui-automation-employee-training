package dibimbing.pages.employee;

import dibimbing.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EditEmployeeModal extends BasePage {
    private static final Logger log = LogManager.getLogger(EditEmployeeModal.class);

    @FindBy(id = "edit-employee-close-button-modal")
    private WebElement btnCloseEditEmployeeModal;

    @FindBy(id = "edit-employee-name-input")
    private WebElement inputEmployeeName;

    @FindBy(id = "edit-employee-employee-id-input")
    private WebElement inputEmployeeId;

    @FindBy(id = "edit-employee-email-input")
    private WebElement inputEmployeeEmail;

    @FindBy(id = "edit-employee-phone-number-input")
    private WebElement inputEmployeePhoneNumber;

    @FindBy(id = "edit-employee-division-select")
    private WebElement dropdownDivision;

    @FindBy(xpath = "//option[normalize-space()='Quality Assurance Data Test Division Update']")
    private WebElement optionDivisionQualityAssuranceDataTestUpdate;

    @FindBy(id = "edit-employee-employee-role-input")
    private WebElement inputRole;

    @FindBy(xpath = "//span[text()='▼']")
    private WebElement dropdownAngkatan;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2024 Genap'])[2]")
    private WebElement angkatanInputOption2024Genap;

    @FindBy(id = "edit-employee-gender-radio-male")
    private WebElement radioGenderMale;

    @FindBy(id = "edit-employee-gender-radio-female")
    private WebElement radioGenderFemale;

    @FindBy(id = "edit-employee-birth-date-input")
    private WebElement inputBirthDate;

    @FindBy(id = "edit-employee-address-textarea")
    private WebElement textareaAddress;

    @FindBy(id = "edit-employee-nik-input")
    private WebElement inputNik;

    @FindBy(id = "edit-employee-npwp-input")
    private WebElement inputNpwp;

    @FindBy(id = "edit-employee-save-changes-button")
    private WebElement btnSaveChanges;

    public EditEmployeeModal(WebDriver driver){
        super(driver);
    }

    public void verifyEditEmployeeModalVisible() {
        log.info("Verify Edit Employee modal visible");
        Assert.assertTrue(
                isDisplayed(btnCloseEditEmployeeModal) && isDisplayed(btnSaveChanges),
                "Edit Employee modal tidak tampil"
        );
    }

    public void clickCloseEditEmployeeModal() {
        log.info("Click close Edit Employee modal");
        click(btnCloseEditEmployeeModal);
    }

    public void clickSaveChanges() {
        log.info("Click Save Changes");
        click(btnSaveChanges);
    }

/* =========================
   GET CURRENT VALUE (prefill)
   ========================= */

    public String getCurrentName() {
        log.info("Get current value: Name");
        return inputEmployeeName.getAttribute("value");
    }

    public String getCurrentEmployeeId() {
        log.info("Get current value: Employee ID");
        return inputEmployeeId.getAttribute("value");
    }

    public String getCurrentEmail() {
        log.info("Get current value: Email");
        return inputEmployeeEmail.getAttribute("value");
    }

    public String getCurrentPhone() {
        log.info("Get current value: Phone Number");
        return inputEmployeePhoneNumber.getAttribute("value");
    }

    public String getCurrentRole() {
        log.info("Get current value: Role");
        return inputRole.getAttribute("value");
    }

    public String getCurrentBirthDate() {
        log.info("Get current value: Birth Date");
        return inputBirthDate.getAttribute("value");
    }

    public String getCurrentAddress() {
        log.info("Get current value: Address");
        return textareaAddress.getAttribute("value");
    }

    public String getCurrentNik() {
        log.info("Get current value: NIK");
        return inputNik.getAttribute("value");
    }

    public String getCurrentNpwp() {
        log.info("Get current value: NPWP");
        return inputNpwp.getAttribute("value");
    }

/* =========================
   UPDATE FIELD (clear & type)
   ========================= */

    public void clearName() {
        log.info("Clear Name field");
        clearField(inputEmployeeName);
        // trigger blur supaya validasi jalan
        inputEmployeeName.sendKeys(Keys.TAB);
    }

    public void clearEmail() {
        log.info("Clear Email field");
        clearField(inputEmployeeEmail);
        // trigger blur supaya validasi jalan
        inputEmployeeEmail.sendKeys(Keys.TAB);
    }

    public void clearEmployeeId() {
        log.info("Clear employee id field");
        clearField(inputEmployeeId);
        // trigger blur supaya validasi jalan
        inputEmployeeId.sendKeys(Keys.TAB);
    }

    public void updateName(String name) {
        log.info("Update Name: {}", name);
        clearAndType(inputEmployeeName, name); // type() kamu sudah clear dulu
    }

    public void updateEmail(String email) {
        log.info("Update Email: {}", email);
        clearAndType(inputEmployeeEmail, email);
    }

    public void updateEmployeeId(String email) {
        log.info("Update employee id: {}", email);
        clearAndType(inputEmployeeId, email);
    }


    public void updatePhoneNumber(String phone) {
        log.info("Update Phone Number: {}", phone);
        clearAndType(inputEmployeePhoneNumber, phone);
    }

    public void updateRole(String role) {
        log.info("Update Role: {}", role);
        clearAndType(inputRole, role);
    }

    public void updateBirthDate(String birthDate) {
        log.info("Update Birth Date: {}", birthDate);
        clearAndType(inputBirthDate, birthDate);
    }

    public void updateAddress(String address) {
        log.info("Update Address");
        clearAndType(textareaAddress, address);
    }

    public void updateNik(String nik) {
        log.info("Update NIK: {}", nik);
        clearAndType(inputNik, nik);
    }

    public void updateNpwp(String npwp) {
        log.info("Update NPWP: {}", npwp);
        clearAndType(inputNpwp, npwp);
    }

/* =========================
   DROPDOWN / RADIO
   ========================= */

    public void selectDivisionQualityAssuranceDataTestUpdate() {
        log.info("Select division: Quality Assurance Data Test Division Update");
        click(dropdownDivision);
        click(optionDivisionQualityAssuranceDataTestUpdate);
    }

    public void selectAngkatan2024Genap() {
        log.info("Select angkatan: 2024 Genap");
        click(dropdownAngkatan);
        click(angkatanInputOption2024Genap);
    }

    public void selectGenderMale() {
        log.info("Select gender: Male");
        click(radioGenderMale);
    }

    public void selectGenderFemale() {
        log.info("Select gender: Female");
        click(radioGenderFemale);
    }

/* =========================
   HELPER: update only fields you want
   ========================= */

    public void updateEmployeeFields(
            String name,
            String email,
            String phone,
            String role,
            String birthDate,
            String address,
            String nik,
            String npwp
    ) {
        if (name != null && !name.isBlank()) updateName(name);
        if (email != null && !email.isBlank()) updateEmail(email);
        if (phone != null && !phone.isBlank()) updatePhoneNumber(phone);
        if (role != null && !role.isBlank()) updateRole(role);
        if (birthDate != null && !birthDate.isBlank()) updateBirthDate(birthDate);
        if (address != null && !address.isBlank()) updateAddress(address);
        if (nik != null && !nik.isBlank()) updateNik(nik);
        if (npwp != null && !npwp.isBlank()) updateNpwp(npwp);
    }

}
