package dibimbing.pages.training;

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

public class DetailTrainingPage extends BasePage {
    private static final Logger log = LogManager.getLogger(DetailTrainingPage.class);

    @FindBy(id = "update-training-button")
    private WebElement btnEditTraining;

    @FindBy(id = "title")
    private WebElement inputTrainingName;

    @FindBy(id = "description")
    private WebElement inputTrainingDesc;

    @FindBy(id = "update-training-submit-button")
    private WebElement btnSaveEditTraining;

    @FindBy(id = "update-training-close-button")
    private WebElement btnCloseEditTraining;

    @FindBy(xpath = "(//button[normalize-space()='Content Chapter'])[1]")
    private WebElement tabContentChapter;

    @FindBy(xpath = "(//button[normalize-space()='Assigned Employee'])[1]")
    private WebElement tabAssignedEmployee;

    @FindBy(id = "title-feedback")
    private WebElement trainingNameRequiredMsg;

    public DetailTrainingPage(WebDriver driver) {
        super(driver);
    }

    public void verifyDetailTrainingPageLoaded() {
        log.info("Verify Detail Training page loaded");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isDetailLoaded = wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOf(btnEditTraining),
                        ExpectedConditions.visibilityOf(tabContentChapter),
                        ExpectedConditions.visibilityOf(tabAssignedEmployee)
                )
        );

        Assert.assertTrue(
                isDetailLoaded,
                "Detail Training page tidak terbuka / elemen utama tidak tampil"
        );
    }

    public void verifyEditTrainingFormVisible() {
        log.info("Verify Edit Training form visible");
        Assert.assertTrue(
                isDisplayed(btnSaveEditTraining),
                "Form edit training tidak tampil"
        );
    }

    /* =========================
       ACTION BUTTONS
       ========================= */

    public void clickEditTraining() {
        log.info("Click Edit/Update Training button");
        click(btnEditTraining);
    }

    public void clickSaveEditTraining() {
        log.info("Click Save Edit Training");
        click(btnSaveEditTraining);
    }

    public void clickCloseEditTraining() {
        log.info("Click Close Edit Training");
        click(btnCloseEditTraining);
    }

    /* =========================
       TAB NAVIGATION
       ========================= */

    public void openTabContentChapter() {
        log.info("Open tab: Content Chapter");
        click(tabContentChapter);
    }

    public void openTabAssignedEmployee() {
        log.info("Open tab: Assigned Employee");
        click(tabAssignedEmployee);
    }

    public void verifyTabContentChapterVisible() {
        log.info("Verify tab visible: Content Chapter");
        Assert.assertTrue(isDisplayed(tabContentChapter), "Tab Content Chapter tidak tampil");
    }

    public void verifyTabAssignedEmployeeVisible() {
        log.info("Verify tab visible: Assigned Employee");
        Assert.assertTrue(isDisplayed(tabAssignedEmployee), "Tab Assigned Employee tidak tampil");
    }

    /* =========================
       GET CURRENT VALUE (PREFILL)
       ========================= */

    public String getCurrentTrainingName() {
        log.info("Get current Training Name");
        return inputTrainingName.getAttribute("value");
    }

    public String getCurrentTrainingDesc() {
        log.info("Get current Training Description");
        return inputTrainingDesc.getAttribute("value");
    }

    /* =========================
       CLEAR + UPDATE FIELD
       ========================= */

    public void clearTrainingName() {
        log.info("Clear Training Name");
        clearField(inputTrainingName); // kamu sudah pakai clearField di class lain
    }

    public void clearTrainingDesc() {
        log.info("Clear Training Desc");
        clearField(inputTrainingDesc);
    }

    public void updateTrainingName(String name) {
        log.info("Update Training Name: {}", name);
        type(inputTrainingName, name); // type() kamu sudah clear element di method BasePage
    }

    public void updateTrainingDesc(String desc) {
        log.info("Update Training Desc");
        type(inputTrainingDesc, desc);
    }

    public void updateTrainingFields(String name, String desc) {
        log.info("Update Training fields");
        if (name != null) updateTrainingName(name);
        if (desc != null) updateTrainingDesc(desc);
    }

    /* =========================
       REQUIRED VALIDATION
       ========================= */

    public void verifyTrainingNameRequiredVisible() {
        log.info("Verify Training Name required message visible");
        Assert.assertTrue(
                isDisplayed(trainingNameRequiredMsg),
                "Required message untuk Training Name tidak tampil"
        );
    }

    public boolean isTrainingNameRequiredVisible() {
        log.info("Check Training Name required message visible");
        return isDisplayed(trainingNameRequiredMsg);
    }

    public String getTrainingNameRequiredText() {
        log.info("Get Training Name required text");
        return getText(trainingNameRequiredMsg);
    }


}
