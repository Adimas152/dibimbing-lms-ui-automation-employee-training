package dibimbing.pages.training;

import dibimbing.pages.BasePage;
import dibimbing.pages.division.DetailDivisionPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class TrainingListPage extends BasePage {
    private static final Logger log = LogManager.getLogger(TrainingListPage.class);

    @FindBy(id = "search-training-input")
    private WebElement trainingSearchInput;

    @FindBy(id = "add-training-button")
    private WebElement btnAddTraining;

    @FindBy(id = "title")
    private WebElement inputTrainingName;

    @FindBy(id = "description")
    private WebElement inputTrainingDesc;

    @FindBy(id = "add-training-submit-button")
    private WebElement btnConfirmAddTraining;

    @FindBy(id = "add-training-close-button")
    private WebElement btnCloseAddTraining;

    @FindBy(id = "title-feedback")
    private WebElement trainingNameRequiredMsg;

    private final By detailTrainingTopButton = By.id("button-detail-training-0");

    // ===== optional: row hasil paling atas (buat verify search result) =====
    private final By topRow = By.xpath("//tbody/tr[1]");
    private final By topRowTrainingNameCell = By.xpath("//tbody/tr[1]/td[1]"); // kolom training biasanya td pertama


    public TrainingListPage(WebDriver driver) {
        super(driver);
    }

    public void verifyTrainingListPageLoaded() {
        log.info("Verify Training List page loaded");
        Assert.assertTrue(
                isDisplayed(btnAddTraining) && isDisplayed(trainingSearchInput),
                "Training List page tidak terbuka / elemen utama tidak tampil"
        );
    }

    public void verifyAddTrainingModalVisible() {
        log.info("Verify Add Training modal visible");
        Assert.assertTrue(
                isDisplayed(btnConfirmAddTraining),
                "Add Training modal tidak tampil / elemen utama tidak muncul"
        );
    }

    /* =========================
       ACTIONS
       ========================= */

    public void clickAddTraining() {
        log.info("Click Add Training button");
        click(btnAddTraining);
    }

    public void clickdetailTrainingOne() {
        log.info("Click Detail Training paling atas");
        WebElement detailBtn = waitForClickable(detailTrainingTopButton);
        scrollToElement(detailBtn);
        click(detailBtn);
    }



    public void clickConfirmAddTraining() {
        log.info("Click Confirm Add Training");
        click(btnConfirmAddTraining);
    }

    public void clickCloseAddTraining() {
        log.info("Click Close Add Training modal");
        click(btnCloseAddTraining);
    }

    /* =========================
       INPUTS
       ========================= */

    public void inputTrainingName(String name) {
        log.info("Input Training Name: {}", name);
        type(inputTrainingName, name);
    }

    public void inputTrainingDesc(String desc) {
        log.info("Input Training Description");
        type(inputTrainingDesc, desc);
    }

    public void fillTrainingForm(String name, String desc) {
        log.info("Fill Training form");
        inputTrainingName(name);
        inputTrainingDesc(desc);
        clickConfirmAddTraining();
    }

    /* =========================
       SEARCH
       ========================= */

    public void searchTrainingByName(String keyword) {
        log.info("Search training by keyword: {}", keyword);

        // clear + type (lebih stabil dari type() biasa)
        waitForVisibility(trainingSearchInput);
        trainingSearchInput.click();
        trainingSearchInput.clear();
        trainingSearchInput.sendKeys(keyword);

        // verify tengah: hasil search (row paling atas) muncul
        log.info("Verify search result row displayed");
        waitForVisibility(topRow);
        Assert.assertTrue(driver.findElement(topRow).isDisplayed(),
                "Hasil search training tidak muncul");
    }

    public void verifyTopSearchResultContains(String expectedKeyword) {
        log.info("Verify top search result contains keyword: {}", expectedKeyword);

        waitForVisibility(topRowTrainingNameCell);
        String topName = driver.findElement(topRowTrainingNameCell).getText().trim();

        Assert.assertTrue(topName.contains(expectedKeyword),
                "Top search result tidak sesuai. Actual: " + topName);
    }

    public void clickDetailTrainingTopResult() {
        log.info("Click Detail on top search result");

        // verify tengah: tombol detail clickable (ini pengganti jeda manual)
        WebElement detailBtn = waitForClickable(detailTrainingTopButton);
        scrollToElement(detailBtn);

        String beforeUrl = driver.getCurrentUrl();

        try {
            detailBtn.click();
        } catch (Exception e) {
            log.warn("Normal click failed, fallback to JS click. Reason: {}", e.getMessage());
            jsClick(detailBtn);
        }

        // verify setelah klik: pindah halaman (wajib biar ga flakey)
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(beforeUrl)));
        log.info("Success click Detail, URL changed");
    }

    public void openTrainingDetailTopResultByKeyword(String keyword) {
        log.info("Open training detail (top result) by keyword: {}", keyword);

        searchTrainingByName(keyword);
        verifyTopSearchResultContains(keyword); // optional, kalau keyword full name
        clickDetailTrainingTopResult();
    }




//    public void openTrainingDetailByName(String name) {
//        log.info("Open training detail by name (top result): {}", name);
//
//        // search dulu
//        searchTrainingByName(name);
//
//        // tunggu tombol detail paling atas muncul & clickable
//        waitForVisibility(detailTrainingOne);
//        click(detailTrainingOne);
//
//        log.info("Clicked Detail on top search result");
//    }

    public void openTrainingDetailTopResult(String keyword) {
        log.info("Open training detail (top result) by keyword: {}", keyword);

        searchTrainingByName(keyword);

        // tunggu tombolnya muncul & clickable
        WebElement detailBtn = waitForClickable(detailTrainingTopButton);

        // scroll biar pasti kelihatan
        scrollToElement(detailBtn);

        // click normal dulu
        try {
            detailBtn.click();
            log.info("Clicked Detail on top search result");
        } catch (Exception e) {
            // fallback: JS click (kalau ketutup overlay / intercept)
            log.warn("Normal click failed, fallback to JS click. Reason: {}", e.getMessage());
            jsClick(detailBtn);
        }
    }


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
