package dibimbing.pages.division;

import dibimbing.pages.BasePage;
import dibimbing.pages.employee.EmployeeListPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DivisionListPage extends BasePage {
    private static final Logger log = LogManager.getLogger(DivisionListPage.class);

    @FindBy(id = "tabs-admin-employee--tab-1")
    private WebElement DivisionTab;

    @FindBy(xpath = "x")
    private WebElement x;

    public DivisionListPage(WebDriver driver) {
        super(driver);
    }


}
