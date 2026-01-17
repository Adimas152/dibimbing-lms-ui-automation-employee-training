package dibimbing.tests.employee;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class EmployeeEditTests extends BaseTest {
    @Test(description = "EMP-EDIT-001 - Update employee with valid data - Success update employee", groups = {"employee","positive"})
    public void EMP_EDIT_001_updateEmployeeSuccessfully() {}

    @Test(description = "EMP-EDIT-002 - Update employee with empty name - Validation error displayed", groups = {"employee","negative"})
    public void EMP_EDIT_002_updateEmployeeNameEmptyRejected() {}

    @Test(description = "EMP-EDIT-003 - Update employee with invalid email or phone - Validation error displayed", groups = {"employee","negative"})
    public void EMP_EDIT_003_updateEmployeeInvalidEmailOrPhoneRejected() {}

    @Test(description = "EMP-EDIT-004 - Update employee with duplicate ID or email - Duplicate error displayed", groups = {"employee","negative"})
    public void EMP_EDIT_004_updateEmployeeDuplicateRejected() {}


}
