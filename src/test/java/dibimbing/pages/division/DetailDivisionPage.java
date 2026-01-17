package dibimbing.pages.division;

import dibimbing.pages.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class DetailDivisionPage extends BasePage {
    private static final Logger log = LogManager.getLogger(DetailDivisionPage.class);

    @FindBy(id = "edit-division-button")
    private WebElement btnEditDivision;

    @FindBy(id = "name")
    private WebElement inputDivisionName;

    @FindBy(id = "description")
    private WebElement inputDivisionDesc;

    @FindBy(id = "edit-division-confirm-button")
    private WebElement btnSaveEditDivision;

    @FindBy(id = "delete-division-button")
    private WebElement btnDeleteDivision;

    @FindBy(xpath = "(//p[normalize-space()='Required'])[1]")
    private WebElement divisionNameRequiredMsg;

    @FindBy(xpath = "(//p[normalize-space()='Required'])[2]")
    private WebElement divisionDescRequiredMsg;

    @FindBy(id = "edit-division-close-button")
    private WebElement btnCloseEditDivison;

    @FindBy(id = "delete-division-confirm-button")
    private WebElement btnConfirmDeleteDivision;

    @FindBy(id = "delete-division-cancel-button")
    private WebElement btnCancelDeleteDivision;

    public DetailDivisionPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDetailDivisionPageLoaded() {
        log.info("Verify Detail Division page loaded");
        Assert.assertTrue(
                isDisplayed(btnEditDivision),
                "Detail Division page tidak terbuka"
        );
    }

    public void verifyEditDivisionModalVisible() {
        log.info("Verify Edit Division modal visible");
        Assert.assertTrue(
                isDisplayed(inputDivisionName) && isDisplayed(inputDivisionDesc) && isDisplayed(btnSaveEditDivision),
                "Edit Division modal tidak tampil"
        );
    }

    /* =========================
       ACTION BUTTON
       ========================= */

    public void clickEditDivision() {
        log.info("Click Edit Division button");
        click(btnEditDivision);
    }

    public void clickSaveEditDivision() {
        log.info("Click Save Edit Division");
        click(btnSaveEditDivision);
    }

    public void clickDeleteDivision() {
        log.info("Click Delete Division button");
        click(btnDeleteDivision);
    }

    public void clickCloseEditDivision() {
        log.info("Click Close Edit Division modal");
        click(btnCloseEditDivison);
    }

    public void confirmDeleteDivision() {
        log.info("Confirm delete division");
        click(btnConfirmDeleteDivision);
    }

    public void cancelDeleteDivision() {
        log.info("Cancel delete division");
        click(btnCancelDeleteDivision);
    }

    /* =========================
       GET CURRENT VALUE
       ========================= */

    public String getCurrentDivisionName() {
        log.info("Get current Division Name");
        return inputDivisionName.getAttribute("value");
    }

    public String getCurrentDivisionDesc() {
        log.info("Get current Division Description");
        return inputDivisionDesc.getAttribute("value");
    }

    /* =========================
       CLEAR FIELD
       ========================= */

    public void clearDivisionName() {
        log.info("Clear Division Name");
        clearField(inputDivisionName);
    }

    public void clearDivisionDesc() {
        log.info("Clear Division Description");
        clearField(inputDivisionDesc);
    }

    /* =========================
       UPDATE FIELD
       ========================= */

    public void updateDivisionName(String name) {
        log.info("Update Division Name: {}", name);
        type(inputDivisionName, name);
    }

    public void updateDivisionDesc(String desc) {
        log.info("Update Division Description: {}", desc);
        type(inputDivisionDesc, desc);
    }

    public void updateDivisionFields(String name, String desc) {
        log.info("Update Division fields");
        if (name != null) updateDivisionName(name);
        if (desc != null) updateDivisionDesc(desc);
    }

    /* =========================
       REQUIRED VALIDATION
       ========================= */

    public void verifyDivisionNameRequiredVisible() {
        log.info("Verify Division Name required message visible");
        Assert.assertTrue(isDisplayed(divisionNameRequiredMsg),
                "Required message untuk Division Name tidak tampil");
    }

    public void verifyDivisionDescRequiredVisible() {
        log.info("Verify Division Desc required message visible");
        Assert.assertTrue(isDisplayed(divisionDescRequiredMsg),
                "Required message untuk Division Desc tidak tampil");
    }

    public boolean isDivisionNameRequiredVisible() {
        return isDisplayed(divisionNameRequiredMsg);
    }

    public boolean isDivisionDescRequiredVisible() {
        return isDisplayed(divisionDescRequiredMsg);
    }
}

