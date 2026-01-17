package dibimbing.tests.division;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class DivisionDeleteTests extends BaseTest {
    @Test(description = "DIV-DEL-001 - Delete division without employee - Success delete division", groups = {"division","positive"})
    public void DIV_DEL_001_deleteDivisionSuccessfully() {}

    @Test(description = "DIV-DEL-002 - Delete division that has employees - Validation error displayed", groups = {"division","negative"})
    public void DIV_DEL_002_deleteDivisionHasEmployeeRejected() {}

}
