package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(id = "update-chapter-button-1")
    private WebElement btnEditChapter;

    @FindBy(id = "update-chapter-submit-button-1")
    private WebElement btnSaveEditChapter;

    @FindBy(xpath = "(//button[normalize-space()='Add Content'])[1]")
    private WebElement addContentButton;

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

    /* =========================
       CHAPTER - ADD
       ========================= */
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
    public void clickAddContent() {
        log.info("Click Add Content");
        click(addContentButton);
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
        log.info("Choose first media from library (css-14t4u77)");
        click(selectMediaFile); // note: class ini berpotensi tidak unik
        click(chooseMediaButton);
    }

    public void chooseFirstArticleFromLibrary() {
        log.info("Choose first article from library (css-14t4u77)");
        click(selectArticleFile); // note: class ini berpotensi tidak unik
        click(chooseMediaButton);
    }

    /* =========================
       CONTENT - DURATION INPUT
       ========================= */
    public void inputEstimatedVideoDuration(String minutes) {
        log.info("Input Estimated Video Duration: {}", minutes);
        type(estimatedVideoDurationInput, minutes);
    }

    public void inputReadDuration(String minutes) {
        log.info("Input Read Duration: {}", minutes);
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
    public void clickSubmitAddContent() {
        log.info("Click Submit Add Content");
        click(submitContentButton);
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
