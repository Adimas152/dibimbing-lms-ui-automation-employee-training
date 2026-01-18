package dibimbing.pages.training;

import dibimbing.pages.base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AssignEmployeePage extends BasePage {
    private static final Logger log = LogManager.getLogger(AssignEmployeePage.class);

    @FindBy(id = "search-assigned-employee-input")
    private WebElement inputSearchListAssignedEmployee;

    @FindBy(id = "assign-employee-button")
    private WebElement btnAssignEmployee;

    @FindBy(xpath = "(//input[@placeholder='Search name, ID...'])[1]")
    private WebElement inputSearchAssignedEmployeeToAssign;

    @FindBy(xpath = "(//input[@type='date'])[1]")
    private WebElement inputStarDate;

    @FindBy(xpath = "a//input[@type='date'])[2]")
    private WebElement inputDeadlineDate;

    @FindBy(xpath = "//button[normalize-space()='Assign Employee']")
    private WebElement btnSaveAssignEmployee;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement btnCancelAssignEmployee;

    public AssignEmployeePage(WebDriver driver) {
        super(driver);
    }

}
