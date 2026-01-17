package dibimbing.pages.division;

import dibimbing.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DivisionPage extends BasePage {
    private static final Logger log = LogManager.getLogger(DivisionPage.class);

    @FindBy(id = "tabs-admin-employee--tab-1")
    private WebElement DivisionTab;

    @FindBy(id = "add-division-button")
    private WebElement btnAddDivision;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement inputDivisionName;

    @FindBy(id = "description")
    private WebElement inputDivisionDesc;

    @FindBy(id = "add-division-confirm-button")
    private WebElement btnSaveAddDivision;

    @FindBy(id = "add-division-close-button")
    private WebElement btnCloseAddDivison;

    @FindBy(xpath = "//input[@placeholder='Search division...']")
    private WebElement divisionSerachInput;

    @FindBy(xpath = "(//p[normalize-space()='Required'])[1]")
    private WebElement divisionNameRequiredMsg;

    @FindBy(xpath = "(//p[normalize-space()='Required'])[2]")
    private WebElement divisionDescRequiredMsg;


    public DivisionPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDivisionTabVisible() {
        log.info("Verify Division tab visible");
        Assert.assertTrue(isDisplayed(DivisionTab), "Division tab tidak tampil");
    }

    public void verifyAddDivisionModalVisible() {
        log.info("Verify Add Division modal visible");
        Assert.assertTrue(
                isDisplayed(inputDivisionName) && isDisplayed(inputDivisionDesc) && isDisplayed(btnSaveAddDivision),
                "Add Division modal tidak tampil / elemen utama tidak muncul"
        );
    }

    /* =========================
       ACTION - TAB / OPEN MODAL
       ========================= */

    public void clickDivisionTab() {
        log.info("Click Division tab");
        click(DivisionTab);
    }

    public void clickAddDivision() {
        log.info("Click Add Division button");
        click(btnAddDivision);
    }

    public void clickSaveAddDivision() {
        log.info("Click Save Add Division");
        click(btnSaveAddDivision);
    }

    public void clickCloseAddDivision() {
        log.info("Click Close Add Division modal");
        click(btnCloseAddDivison);
    }

    /* =========================
       INPUT - ADD DIVISION FORM
       ========================= */


    public void inputDivisionName(String name) {
        log.info("Input Division Name: {}", name);
        type(inputDivisionName, name);
    }

    public void inputDivisionDesc(String desc) {
        log.info("Input Division Description: {}", desc);
        type(inputDivisionDesc, desc);
    }

    public void fillDivisionForm(String name, String desc) {
        log.info("Fill Division form");
        inputDivisionName(name);
        inputDivisionDesc(desc);
        clickSaveAddDivision();
    }

    /* =========================
       SEARCH
       ========================= */

    public void searchDivisionByName(String keyword) {
        log.info("Search division by keyword: {}", keyword);
        type(divisionSerachInput, keyword);
    }

    public void openDivisionDetailByName(String name) {
        log.info("Open division detail by name: {}", name);

        searchDivisionByName(name);

        By rowByName = By.xpath("//tr[.//td[normalize-space()='" + name + "']]");
        waitForVisibility(rowByName);

        By detailLink = By.xpath("//tr[.//td[normalize-space()='" + name + "']]//a[normalize-space()='Detail']");
        click(driver.findElement(detailLink));
    }


    /* =========================
       REQUIRED VALIDATION
       ========================= */

    public void verifyDivisionNameRequiredVisible() {
        log.info("Verify Division Name required message visible");
        Assert.assertTrue(isDisplayed(divisionNameRequiredMsg), "Required message untuk Division Name tidak tampil");
    }

    public void verifyDivisionDescRequiredVisible() {
        log.info("Verify Division Desc required message visible");
        Assert.assertTrue(isDisplayed(divisionDescRequiredMsg), "Required message untuk Division Desc tidak tampil");
    }

    public boolean isDivisionNameRequiredVisible() {
        return isDisplayed(divisionNameRequiredMsg);
    }

    public boolean isDivisionDescRequiredVisible() {
        return isDisplayed(divisionDescRequiredMsg);
    }


}
