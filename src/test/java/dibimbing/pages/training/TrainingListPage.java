package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(xpath = "//button[@id='button-detail-training-0']")
    private WebElement btnDetailTrainingTopResult;

    @FindBy(xpath = "(//button[starts-with(@id,'button-detail-training-') and normalize-space()='Detail'])[1]")
    private WebElement btnDetailTrainingFirst;

    @FindBy(xpath = "(//tbody//tr)[1]")
    private WebElement firstRow;

    public TrainingListPage(WebDriver driver) {
        super(driver);
    }

    public void waitTrainingSearchResultLoaded() {
        log.info("Wait training search result loaded");

        // tunggu row pertama muncul (lebih stabil daripada nunggu tombol detail langsung)
        waitForVisibility(firstRow);

        // optional: pastikan tombol detail udah bisa diklik
        waitForVisibility(btnDetailTrainingFirst);
        waitForClickable(btnDetailTrainingFirst);
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

    public void verifyDetailTrainingTopResultVisible() {
        log.info("Verify Detail Training Top Result button visible");
        Assert.assertTrue(
                isDisplayed(btnDetailTrainingTopResult),
                "Button Detail Training (top result) tidak tampil / elemen tidak muncul"
        );
    }


    /* =========================
       ACTIONS
       ========================= */

    public void clickDetailTopSearchResult() {
        log.info("Click Detail on top search result (index 0)");
        waitForVisibility(btnDetailTrainingTopResult);
        waitForClickable(btnDetailTrainingTopResult);
        click(btnDetailTrainingTopResult);
    }

    public void clickv2DetailTopSearchResult() {
        log.info("Click Detail on top search result (index 0) v2");
        btnDetailTrainingTopResult.click();
    }



    public void clickAddTraining() {
        log.info("Click Add Training button");
        click(btnAddTraining);
    }

    public void clickdetailTrainingOne() {
        log.info("Click Detail Training paling atas");
        click(btnDetailTrainingTopResult);
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
        type(trainingSearchInput, keyword);
        By detailTop = By.id("button-detail-training-0");
        waitForVisibility(detailTop);
        waitMillis(500);
    }

    public void openTrainingDetailByName(String name) {
        log.info("Open training detail by name: {}", name);

        searchTrainingByName(name);

        By rowByName = By.xpath("//tr[.//td[normalize-space()='" + name + "']]");
        waitForVisibility(rowByName);

//        By detailLink = By.xpath("//tr[.//td[normalize-space()='" + name + "']]//a[normalize-space()='Detail']");
//        waitForVisibility(driver.findElement(detailLink));
//        click(driver.findElement(detailLink));
    }

    public void openTrainingDetailFromTopResult(String keyword) {
        log.info("Open training detail from top result for keyword: {}", keyword);
        searchTrainingByName(keyword);
        // verify
        waitForVisibility(btnDetailTrainingTopResult);
        verifyDetailTrainingTopResultVisible();
        waitForClickable(btnDetailTrainingTopResult);
        click(btnDetailTrainingTopResult);
        clickDetailTopSearchResult();
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

