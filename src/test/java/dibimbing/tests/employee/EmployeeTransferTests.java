package dibimbing.tests.employee;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class EmployeeTransferTests extends BaseTest {
    @Test(description = "EMP-TRF-001 - Transfer employees to target division - Success transfer employee", groups = {"employee","positive"})
    public void EMP_TRF_001_transferEmployeeSuccessfully() {}

    @Test(description = "EMP-TRF-002 - Transfer employee without target division - Validation error displayed", groups = {"employee","negative"})
    public void EMP_TRF_002_transferEmployeeWithoutDivisionRejected() {}

}
