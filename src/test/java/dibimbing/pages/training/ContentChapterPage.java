package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ContentChapterPage extends BasePage {
    private static final Logger log = LogManager.getLogger(ContentChapterPage.class);

    @FindBy(id = "add-chapter-icon-button")
    private WebElement btnAddChapter;

    @FindBy(id = "title")
    private WebElement inputChapterName;

    @FindBy(id = "description")
    private WebElement inputChapterDesc;

    @FindBy(id = "add-chapter-submit-button")
    private WebElement btnConfirmAddChapter;

    @FindBy(id = "add-chapter-close-button")
    private WebElement btnCloseAddChapter;

    @FindBy(id = "title-feedback")
    private WebElement chapterNameRequiredMsg;


    @FindBy(xpath = "(//div[starts-with(@id,'chapter-item')])[1]")
    private WebElement firstChapterItem;

    @FindBy(id = "update-chapter-button-1")
    private WebElement btnEditChapter;

    @FindBy(id = "update-chapter-submit-button-1")
    private WebElement btnSaveEditChapter;

    @FindBy(xpath = "(//button[normalize-space()='Add Content'])[1]")
    private WebElement addContentButton;

    private final By btnAddContentBy =
            By.xpath("//button[normalize-space()='Add Content']");

    @FindBy(xpath = "//span[text()='Video']")
    private WebElement videoContentType;

    @FindBy(xpath = "//span[text()='Article']")
    private WebElement articleContentType;

    @FindBy(xpath = "//span[text()='Test']")
    private WebElement testContentType;

    @FindBy(xpath = "//input[@placeholder='Content title']")
    private WebElement inputContentTitle;

    @FindBy(css = "div.ck.ck-editor__editable[contenteditable='true']")
    private WebElement inputContentDesc;

    @FindBy(id="button-upload-media")
    private WebElement uploadMediaButton;

    @FindBy(className = "css-14t4u77")
    private WebElement selectMediaFile;

    @FindBy(className = "css-14t4u77")
    private WebElement selectArticleFile;

    @FindBy(xpath = "(//div[contains(@class,'chakra-modal__body')]//div[contains(@class,'chakra-stack')]//div[contains(@style,'position: absolute') and contains(@style,'z-index')])[1]")
    private WebElement firstSelectableMediaOverlay;


    @FindBy(xpath = "//button[text()='Choose Media']")
    private WebElement chooseMediaButton;

    @FindBy(xpath = "//input[@placeholder='Estimated Video Duration']")
    private WebElement estimatedVideoDurationInput;

    @FindBy(xpath = "//input[@placeholder='Read Duration']")
    private WebElement readDurationInput;

    @FindBy(xpath = "//input[@placeholder='Test Duration']")
    private WebElement testDurationInput;

    @FindBy(xpath ="//label[.//p[text()='Shuffle Question']]")
    private WebElement shuffleQuestionCheckbox;

    @FindBy(xpath = "//button[starts-with(@id, 'submit-button-') and text()='Add Content']")
    private WebElement submitContentButton;

    private final By btnSubmitAddContentBy =
            By.xpath("//button[starts-with(@id,'submit-button-') and normalize-space()='Add Content']");

    @FindBy(xpath = "//button[normalize-space()='Detail']")
    private WebElement btnDetailContent;



    public ContentChapterPage(WebDriver driver) {
        super(driver);
    }



    /* =========================
      VERIFY PAGE / SECTION
      ========================= */
    public void verifyContentChapterSectionLoaded() {
        log.info("Verify Content Chapter section loaded");
        Assert.assertTrue(
                isDisplayed(btnAddChapter),
                "Content Chapter section tidak tampil / tombol Add Chapter tidak ditemukan"
        );
    }

    public void clickFirstChapterItem() {
        log.info("Click first chapter item (chapter paling atas) with retry");

        By firstChapterBy = By.xpath("(//div[starts-with(@id,'chapter-item')])[1]");

        int maxRetry = 3;
        for (int i = 1; i <= maxRetry; i++) {
            try {
                WebElement el = waitForVisibility(firstChapterBy);
                waitForClickable(firstChapterBy);
                el.click();
                return;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                log.warn("Stale element when clicking first chapter item. Retry {}/{}", i, maxRetry);
                waitMillis(300);
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                log.warn("Click intercepted. Fallback JS click. Retry {}/{}", i, maxRetry);
                WebElement el = driver.findElement(firstChapterBy);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
                return;
            }
        }

        // last attempt (hard fail kalau masih gagal)
        WebElement el = driver.findElement(firstChapterBy);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public void selectMediaByName(String fileName) {
        By card = By.xpath("//div[contains(@class,'chakra-modal__body')]//*[normalize-space()='" + fileName + "']/ancestor::div[contains(@class,'chakra-stack')][1]");
        WebElement el = waitForVisibility(card);
        try {
            waitForClickable(el);
            el.click();
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }



    public void waitMediaModalClosed() {
        // sesuaikan locator modal kalau kamu punya
        // kalau belum ada, minimal tunggu tombol chooseMediaButton hilang
        log.info("Wait media modal closed");
        waitForVisibility(chooseMediaButton); // butuh method waitForInvisibility di BasePage
    }

    /* =========================
       CHAPTER - ADD
       ========================= */
    public void clickBtnDetailContent() {
        log.info("Click Detail Content");
        click(btnDetailContent);
    }

    public void clickAddChapter() {
        log.info("Click Add Chapter");
        click(btnAddChapter);
    }

    public void verifyAddChapterModalVisible() {
        log.info("Verify Add Chapter modal visible");
        Assert.assertTrue(
                isDisplayed(inputChapterName) && isDisplayed(inputChapterDesc) && isDisplayed(btnConfirmAddChapter),
                "Modal Add Chapter tidak tampil"
        );
    }

    public void inputChapterName(String name) {
        log.info("Input Chapter Name: {}", name);
        type(inputChapterName, name);
    }

    public void inputChapterDesc(String desc) {
        log.info("Input Chapter Description");
        type(inputChapterDesc, desc);
    }

    public void clickConfirmAddChapter() {
        log.info("Click Confirm Add Chapter");
        click(btnConfirmAddChapter);
    }

    public void clickCloseAddChapter() {
        log.info("Click Close Add Chapter");
        click(btnCloseAddChapter);
    }

    public void fillChapterForm(String name, String desc) {
        log.info("Fill Chapter form");
        inputChapterName(name);
        inputChapterDesc(desc);
    }

    public void submitCreateChapter(String name, String desc) {
        log.info("Submit create chapter");
        verifyAddChapterModalVisible();
        fillChapterForm(name, desc);
        clickConfirmAddChapter();
    }



    /* =========================
       CHAPTER - VALIDATION
       ========================= */
    public void verifyChapterNameRequiredVisible() {
        log.info("Verify Chapter Name required visible");
        Assert.assertTrue(
                isDisplayed(chapterNameRequiredMsg),
                "Required message untuk Chapter Name tidak tampil"
        );
    }

    public boolean isChapterNameRequiredVisible() {
        return isDisplayed(chapterNameRequiredMsg);
    }

    /* =========================
       CHAPTER - EDIT (berdasarkan id yg kamu punya)
       ========================= */
    public void clickEditChapter() {
        log.info("Click Edit Chapter");
        click(btnEditChapter);
    }

    public void clickSaveEditChapter() {
        log.info("Click Save Edit Chapter");
        click(btnSaveEditChapter);
    }

    /* =========================
       CONTENT - OPEN ADD CONTENT
       ========================= */
//    public void clickAddContent() {
//        log.info("Click Add Content");
//        click(addContentButton);
//    }

    public void clickAddContent() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(btnAddContentBy)
        );
        btn.click();
    }

    public void verifyAddContentFormVisible() {
        log.info("Verify Add Content form visible");
        Assert.assertTrue(
                isDisplayed(inputContentTitle) && isDisplayed(submitContentButton),
                "Form Add Content tidak tampil"
        );
    }

    /* =========================
       CONTENT - SELECT TYPE
       ========================= */
    public void selectContentTypeVideo() {
        log.info("Select content type: Video");
        click(videoContentType);
    }

    public void selectContentTypeArticle() {
        log.info("Select content type: Article");
        click(articleContentType);
    }

    public void selectContentTypeTest() {
        log.info("Select content type: Test");
        click(testContentType);
    }

    /* =========================
       CONTENT - INPUT BASIC
       ========================= */
    public void inputContentTitle(String title) {
        log.info("Input Content Title: {}", title);
        type(inputContentTitle, title);
    }

    public void inputContentDescription(String desc) {
        log.info("Input Content Description (CKEditor)");
        // CKEditor biasanya butuh click dulu
        click(inputContentDesc);
        type(inputContentDesc, desc);
    }

    /* =========================
       CONTENT - UPLOAD / CHOOSE MEDIA
       ========================= */
    public void clickUploadMedia() {
        log.info("Click Upload Media button");
        click(uploadMediaButton);
    }

    public void chooseFirstMediaFromLibrary() {
        log.info("Choose first media from library ");
        click(selectMediaFile); // note: class ini berpotensi tidak unik
        click(chooseMediaButton);
        // IMPORTANT: tunggu modal bener-bener hilang (minimal jeda)
        waitMillis(500);
    }

    public void chooseFirstArticleFromLibrary() {
        log.info("Choose first article from library");
        click(selectArticleFile); // note: class ini berpotensi tidak unik
        click(chooseMediaButton);
    }

    /* =========================
       CONTENT - DURATION INPUT
       ========================= */
    public void inputEstimatedVideoDuration(String minutes) {
        log.info("Input Estimated Video Duration: {}", minutes);

        waitForVisibility(estimatedVideoDurationInput);
        waitForClickable(estimatedVideoDurationInput);

        click(estimatedVideoDurationInput);      // penting
        clearField(estimatedVideoDurationInput); // penting
        type(estimatedVideoDurationInput, minutes);
    }

    public void inputReadDuration(String minutes) {
        log.info("Input Read Duration: {}", minutes);

        waitForVisibility(readDurationInput);
        waitForClickable(readDurationInput);

        click(readDurationInput);      // penting untuk Chakra UI
        clearField(readDurationInput); // optional tapi aman
        type(readDurationInput, minutes);
    }

    public void inputTestDuration(String minutes) {
        log.info("Input Test Duration: {}", minutes);
        type(testDurationInput, minutes);
    }

    public void toggleShuffleQuestion(boolean shouldEnable) {
        log.info("Set Shuffle Question: {}", shouldEnable);
        boolean isChecked = shuffleQuestionCheckbox.findElement(
                org.openqa.selenium.By.xpath(".//input[@type='checkbox']")
        ).isSelected();

        if (shouldEnable != isChecked) {
            click(shuffleQuestionCheckbox);
        }
    }

    /* =========================
       CONTENT - SUBMIT
       ========================= */

    public void clickChooseMediaButton() {
        log.info("Click Choose Media");
        click(chooseMediaButton);
    }
//
//    public void clickSubmitAddContent() {
//        log.info("Click Submit Add Content");
//        click(submitContentButton);
//    }

    public void clickSubmitAddContent() {
        log.info("Click Submit Add Content");
        clickStable(btnSubmitAddContentBy);
    }

    /* =========================
       HIGH-LEVEL HELPERS
       ========================= */

    // Video content (title, desc, choose media, duration)
    public void createVideoContent(String title, String desc, String estMinutes) {
        log.info("Create Video content");
        clickAddContent();
        verifyAddContentFormVisible();

        selectContentTypeVideo();
        inputContentTitle(title);
        inputContentDescription(desc);

        clickUploadMedia();
        chooseFirstMediaFromLibrary();

        // tunggu UI stabil setelah modal close
        waitMillis(1000);
        waitForVisibility(readDurationInput);
        waitForClickable(readDurationInput);

        inputEstimatedVideoDuration(estMinutes);
        clickSubmitAddContent();
    }

    // Article content (title, desc, choose article, read duration)
    public void createArticleContent(String title, String desc, String readMinutes) {
        log.info("Create Article content");
        clickAddContent();
        verifyAddContentFormVisible();
        selectContentTypeArticle();
        inputContentTitle(title);
        inputContentDescription(desc);
        clickUploadMedia();
        chooseFirstArticleFromLibrary();

        inputReadDuration(readMinutes);
        clickSubmitAddContent();
    }

    // Test content (title, desc, test duration, shuffle)
    public void createTestContent(String title, String desc, String testMinutes, boolean shuffle) {
        log.info("Create Test content");
        clickAddContent();
        verifyAddContentFormVisible();

        selectContentTypeTest();
        inputContentTitle(title);
        inputContentDescription(desc);

        inputTestDuration(testMinutes);
        toggleShuffleQuestion(shuffle);

        clickSubmitAddContent();
    }

    public void clearChapterName() {
        log.info("Clear Chapter Name");
        clearField(inputChapterName);
    }

    public void clearChapterDesc() {
        log.info("Clear Chapter Description");
        clearField(inputChapterDesc);
    }

    public void updateChapterName(String name) {
        log.info("Update Chapter Name: {}", name);
        type(inputChapterName, name);
    }

    public void updateChapterDesc(String desc) {
        log.info("Update Chapter Description");
        type(inputChapterDesc, desc);
    }



}
