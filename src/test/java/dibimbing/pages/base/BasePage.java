package dibimbing.pages.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /* ================= WAIT ================= */

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /* ================= ACTION ================= */

    protected void click(WebElement element) {
        waitForClickable(element).click();
    }

    // Overloaded click method for already located WebElement
    protected void clickBtn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }


    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clearAndType(WebElement element, String text) {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
    }

    protected void clearField(WebElement element) {
        waitForVisibility(element);
        element.click();

        // Mac pakai COMMAND, Windows/Linux pakai CONTROL
        String os = System.getProperty("os.name").toLowerCase();
        Keys cmdCtrl = os.contains("mac") ? Keys.COMMAND : Keys.CONTROL;

        element.sendKeys(cmdCtrl, "a");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public void waitMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread sleep interrupted", e);
        }
    }



    public WebElement waitForClickable(By locator) {
        return wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebDriverWait waitSeconds(long seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /** Tunggu sampai element benar-benar bisa diklik/diinput (handle overlay/animasi ringan). */
    protected void waitUntilInteractable(WebElement el, long seconds) {
        waitSeconds(seconds).until(driver -> {
            try {
                if (!el.isDisplayed() || !el.isEnabled()) return false;
                // cek apakah element ketutup overlay (klik di center point)
                Rectangle r = el.getRect();
                int cx = r.getX() + (r.getWidth() / 2);
                int cy = r.getY() + (r.getHeight() / 2);
                WebElement top = ((JavascriptExecutor) driver).executeScript(
                        "return document.elementFromPoint(arguments[0], arguments[1]);", cx, cy
                ) instanceof WebElement w ? w : null;

                return top != null && (top.equals(el) || el.equals(top.findElement(By.xpath("ancestor-or-self::*"))));
            } catch (StaleElementReferenceException e) {
                return false;
            } catch (Exception e) {
                return false;
            }
        });
    }

    /** Type dengan memastikan interactable */
    protected void safeType(WebElement el, String text) {
        waitUntilInteractable(el, 15);
        el.click();
        el.clear();
        el.sendKeys(text);
    }



}
