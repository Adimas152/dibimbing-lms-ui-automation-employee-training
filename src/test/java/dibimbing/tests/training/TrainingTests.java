package dibimbing.tests.training;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class TrainingTests extends BaseTest {
    @Test(description = "TRN-ADD-001 - Create training with valid data - Success create training", groups = {"training","positive"})
    public void TRN_ADD_001_createTrainingSuccessfully() {}

    @Test(description = "TRN-ADD-002 - Create training with empty name - Validation error displayed", groups = {"training","negative"})
    public void TRN_ADD_002_createTrainingNameEmptyRejected() {}

    @Test(description = "TRN-EDIT-001 - Update training with valid data - Success update training", groups = {"training","positive"})
    public void TRN_EDIT_001_updateTrainingSuccessfully() {}

}
