package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EditContentPage extends BasePage {
    private static final Logger log = LogManager.getLogger(EditContentPage.class);

    @FindBy(id = "button-update-content")
    private WebElement btnEditContent;

    @FindBy(id = "input-title-content")
    private WebElement inputContentTitle;

    @FindBy(xpath = "div[role='textbox'][contenteditable='true']")
    private WebElement inputContentDesc;

    @FindBy(id = "input-read-duration-content")
    private WebElement readDurationInput;

    @FindBy(id = " input-estimated-video-duration-content")
    private WebElement videoDurationInput;

    @FindBy(id = "modal-update-content-save-button")
    private WebElement btnSaveUpdateContent;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElement btnDeleteContent;

    @FindBy(xpath = "(//button[normalize-space()='Delete'])[2]")
    private WebElement btnConfirmDeleteContent;

    public EditContentPage(WebDriver driver) {
        super(driver);
    }

    public void clickDeleteContent() {
        log.info("Click Delete content");
        waitForClickable(btnDeleteContent);
        click(btnDeleteContent);
    }

    public void confirmDeleteContent() {
        log.info("Confirm Delete content");
        waitForClickable(btnConfirmDeleteContent);
        click(btnConfirmDeleteContent);
    }

    public void clickEditContent() {
        log.info("Click Edit Content");
        waitForClickable(btnEditContent);
        click(btnEditContent);
    }

    public void verifyEditContentModalVisible() {
        log.info("Verify Edit Content modal visible");
        Assert.assertTrue(isDisplayed(btnSaveUpdateContent), "Edit Content modal tidak tampil");
    }

    public void clearTitle() { clearField(inputContentTitle); }
    public void updateTitle(String title) { type(inputContentTitle, title); }

    public void clearDesc() {// kalau clearField kamu tidak support CKEditor, pakai ctrl/cmd+a delete:
        clearField(inputContentDesc);
    }

    public void updateDesc(String desc) {
        click(inputContentDesc);
        type(inputContentDesc, desc);
    }

    public void clearReadDuration() { clearField(readDurationInput); }
    public void updateReadDuration(String minutes) { type(readDurationInput, minutes); }

    public void clearVideoDuration() { clearField(videoDurationInput); }
    public void updateVideoDuration(String minutes) { type(videoDurationInput, minutes); }

    public void clickSaveUpdate() { click(btnSaveUpdateContent); }

    // helper untuk CKEditor contenteditable
    private void clearContentEditable(WebElement el) {
        el.click();
        el.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.COMMAND, "a"));
        el.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
    }



}
