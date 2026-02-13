package dibimbing.pages.employee;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class EmployeeListPage extends BasePage {
    private static final Logger log = LogManager.getLogger(EmployeeListPage.class);

    @FindBy(id = "tabs-admin-employee--tab-0")
    private WebElement employeeListTab;

    @FindBy(id = "employee-list-count")
    private WebElement employeeCountBadge;

    @FindBy(xpath = "//input[@placeholder='Search name, e-mail, phone...']")
    private WebElement employeeSearchInput;

    @FindBy(xpath = "//p[text()='Filter by Angkatan']")
    private WebElement angkatanFilterDropdown;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2024 Ganjil'])[1]")
    private WebElement angkatanFilterOption2024Ganjil;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2024 Genap'])[1]")
    private WebElement angkatanFilterOption2024Genap;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2025 Ganjil'])[1]")
    private WebElement angkatanFilterOptionGanjil;

    @FindBy(xpath = "(//button[@role='menuitem' and normalize-space()='2025 Genap'])[1]")
    private WebElement angkatanFilterOption2025Genap;

    @FindBy(id = "menu-button-admin-employee-action")
    private WebElement actionDropdownButton;

    @FindBy(xpath = "//button[@role='menuitem' and normalize-space()='Download']")
    private WebElement downloadEmployeeMenuItem;

    @FindBy(xpath = "//button[@role='menuitem' and @data-action='import']")
    private WebElement importEmployeeMenuItem;

    @FindBy(xpath = "//button[@role='menuitem' and @data-action='transfer']")
    private WebElement transferEmployeeMenuItem;

    @FindBy(id = "button-add-employee")
    private WebElement addEmployeeButton;

    @FindBy(id = "copy-employee-email-0-icon")
    private WebElement copyEmployeeEmail;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public void verifyEmployeeListPageLoaded() {
        log.info("Verify Employee List Page loaded");
        Assert.assertTrue(isDisplayed(employeeListTab), "Employee List tab tidak tampil");
        Assert.assertTrue(isDisplayed(addEmployeeButton), "Button Add Employee tidak tampil");
        Assert.assertTrue(isDisplayed(employeeSearchInput), "Search Input tidak tampil");
    }

    public void verifySearchInputLoaded() {
        log.info("Verify Search Input loaded");
        Assert.assertTrue(isDisplayed(employeeSearchInput), "Employee search input tidak tampil");
    }

    public void clickAddEmployee() {
        log.info("Click Add Employee button");
        click(addEmployeeButton);
    }


    public void searchEmployeeByName(String name) {
        log.info("Search employee by name: {}", name);

        wait.until(ExpectedConditions.elementToBeClickable(employeeSearchInput));

        click(employeeSearchInput);          // paksa fokus
        employeeSearchInput.clear();         // reset value
        employeeSearchInput.sendKeys(name);  // JANGAN pakai type()
    }

    public void openActionDropdown() {
        log.info("Open action dropdown");
        click(actionDropdownButton);
    }

    public void clickDownloadEmployee() {
        openActionDropdown();
        click(downloadEmployeeMenuItem);
    }

    public void clickImportEmployee() {
        openActionDropdown();
        click(importEmployeeMenuItem);
    }

    public void clickTransferEmployee() {
        openActionDropdown();
        click(transferEmployeeMenuItem);
    }

    public void selectAngkatan(String angkatan) {
        log.info("Select angkatan: {}", angkatan);
        click(angkatanFilterDropdown);

        WebElement option = driver.findElement(By.xpath(
                "//button[@role='menuitem' and normalize-space()='" + angkatan + "']"
        ));

        click(option);
    }

    public String getEmployeeCountBadge() {
        return getText(employeeCountBadge);
    }

    public void verifyEmployeeRowByNameVisible(String name) {
        By rowByName = By.xpath("//tr[.//td[normalize-space()='" + name + "']]");
        Assert.assertTrue(isDisplayed(waitForVisibility(rowByName)),
                "Employee tidak muncul di hasil search. Name: " + name);
    }

    public void openEmployeeDetailByName(String name) {
        log.info("Open employee detail by name: {}", name);

        searchEmployeeByName(name);

        By rowByName = By.xpath("//tr[.//td[normalize-space()='" + name + "']]");
        waitForVisibility(rowByName);

        By detailLink = By.xpath("//tr[.//td[normalize-space()='" + name + "']]//a[normalize-space()='Detail']");
        click(driver.findElement(detailLink));
    }

}
