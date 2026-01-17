package dibimbing.tests.division;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class DivisionAddTests extends BaseTest {
    @Test(description = "DIV-ADD-001 - Create division with valid data - Success create division", groups = {"division","positive"})
    public void DIV_ADD_001_createDivisionSuccessfully() {}

    @Test(description = "DIV-ADD-002 - Create division with empty required field - Validation error displayed", groups = {"division","negative"})
    public void DIV_ADD_002_createDivisionRequiredFieldEmptyRejected() {}

    @Test(description = "DIV-ADD-003 - Create division with duplicate name - Duplicate error displayed", groups = {"division","negative"})
    public void DIV_ADD_003_createDivisionDuplicateNameRejected() {}

}
