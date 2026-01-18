package dibimbing.tests.training;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class AssignTests extends BaseTest {
    @Test(
            description = "ASN-ASSIGN-001 - Assign employee with valid date range - Success assign employee",
            groups = {"regression","training","positive"})
    public void ASN_ASSIGN_001_assignEmployeeSuccessfully() {}

    @Test(
            description = "ASN-ASSIGN-002 - Assign employee when training has no content - Validation error displayed",
            groups = {"regression","training","negative"})
    public void ASN_ASSIGN_002_assignEmployeeWithoutContentRejected() {}

    @Test(
            description = "ASN-ASSIGN-003 - Assign employee with invalid deadline date - Validation error displayed",
            groups = {"regression","training","negative"})
    public void ASN_ASSIGN_003_assignEmployeeInvalidDeadlineRejected() {}

    @Test(
            description = "ASN-EDITDL-001 - Update assignment deadline with valid date - Success update assignment",
            groups = {"regression","training","positive"})
    public void ASN_EDITDL_001_updateAssignmentDeadlineSuccessfully() {}

    @Test(
            description = "ASN-EDITDL-002 - Update assignment deadline with invalid date - Validation error displayed",
            groups = {"regression","training","negative"})
    public void ASN_EDITDL_002_updateAssignmentDeadlineInvalidRejected() {}

    @Test(
            description = "ASN-DEL-001 - Delete assignment with confirmation - Success delete assignment",
            groups = {"regression","training","positive"})
    public void ASN_DEL_001_deleteAssignmentSuccessfully() {}

}
