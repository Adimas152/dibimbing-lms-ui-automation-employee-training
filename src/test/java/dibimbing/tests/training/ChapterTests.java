package dibimbing.tests.training;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class ChapterTests extends BaseTest {
    @Test(description = "CHP-ADD-001 - Create chapter with valid data - Success create chapter", groups = {"training","positive"})
    public void CHP_ADD_001_createChapterSuccessfully() {}

    @Test(description = "CHP-ADD-002 - Create chapter with empty name - Validation error displayed", groups = {"training","negative"})
    public void CHP_ADD_002_createChapterNameEmptyRejected() {}

    @Test(description = "CHP-EDIT-001 - Update chapter with valid data - Success update chapter", groups = {"training","positive"})
    public void CHP_EDIT_001_updateChapterSuccessfully() {}

    @Test(description = "CHP-EDIT-002 - Update chapter with empty name - Validation error displayed", groups = {"training","negative"})
    public void CHP_EDIT_002_updateChapterNameEmptyRejected() {}

}
