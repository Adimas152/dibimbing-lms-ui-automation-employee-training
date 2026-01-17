package dibimbing.tests.employee;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class EmployeeStatusTests extends BaseTest {
    @Test(description = "EMP-STS-001 - Inactivate employee account with confirmation - Success inactivate employee", groups = {"employee","positive"})
    public void EMP_STS_001_inactivateEmployeeSuccessfully() {}

    @Test(description = "EMP-STS-002 - Activate employee account with confirmation - Success activate employee", groups = {"employee","positive"})
    public void EMP_STS_002_activateEmployeeSuccessfully() {}

}
