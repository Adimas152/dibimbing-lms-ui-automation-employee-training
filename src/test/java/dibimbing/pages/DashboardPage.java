package dibimbing.pages;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardPage extends BasePage {
    private static final Logger log = LogManager.getLogger(DashboardPage.class);

    @FindBy(id = "layout-desktop-menu-item-box-employee")
    private WebElement employeeSidebarMenu;

    @FindBy(id = "layout-desktop-menu-item-box-training")
    private WebElement trainingSidebarMenu;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void verifyEmployeeTrainingMenuVisible() {
        log.info("Verify Employee & Training menu visible");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(employeeSidebarMenu));
        wait.until(ExpectedConditions.visibilityOf(trainingSidebarMenu));

        Assert.assertTrue(employeeSidebarMenu.isDisplayed(), "Employee menu tidak tampil");
        Assert.assertTrue(trainingSidebarMenu.isDisplayed(), "Training menu tidak tampil");
    }
    public void clickEmployeeSidebareMenu(){
        log.info("Clik employee menu");
        employeeSidebarMenu.click();
    }

    public void clickTrainingSidebarMenu(){
        log.info("Clik training menu");
        trainingSidebarMenu.click();
    }

}
