package dibimbing.tests.training;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class ContentTests extends BaseTest {
    @Test(
            description ="CNT-ADD-001 - Create article content with valid upload - Success create content",
            groups = {"regression","training","positive"})
    public void CNT_ADD_001_createContentArticleSuccessfully() {}

    @Test(
            description = "CNT-ADD-002 - Create video content with valid configuration - Success create content",
            groups = {"regression","training","positive"})
    public void CNT_ADD_002_createContentVideoSuccessfully() {}

    @Test(
            description = "CNT-ADD-003 - Create test content with valid configuration - Success create content",
            groups = {"regression","training","positive"})
    public void CNT_ADD_003_createContentTestSuccessfully() {}

    @Test(
            description = "CNT-ADD-004 - Create article content with invalid read duration - Validation error displayed",
            groups = {"regression","training","negative"})
    public void CNT_ADD_004_createContentArticleInvalidReadDurationRejected() {}

    @Test(
            description = "CNT-ADD-005 - Create video content with invalid upload or duration - Validation error displayed",
            groups = {"regression","training","negative"})
    public void CNT_ADD_005_createContentVideoInvalidUploadOrDurationRejected() {}

    @Test(
            description = "CNT-EDIT-001 - Update article content with valid data - Success update content",
            groups = {"regression","training","positive"})
    public void CNT_EDIT_001_updateContentArticleSuccessfully() {}

    @Test(
            description = "CNT-EDIT-002 - Update video content with valid data - Success update content",
            groups = {"regression","training","positive"})
    public void CNT_EDIT_002_updateContentVideoSuccessfully() {}

    @Test(
            description = "CNT-DEL-001 - Delete article content with confirmation - Success delete content",
            groups = {"regression","training","positive"})
    public void CNT_DEL_001_deleteContentArticleSuccessfully() {}

    @Test(
            description = "CNT-DEL-002 - Delete video content with confirmation - Success delete content",
            groups = {"regression","training","positive"})
    public void CNT_DEL_002_deleteContentVideoSuccessfully() {}

    @Test(
            description = "CNT-DEL-003 - Delete test content with confirmation - Success delete content",
            groups = {"regression","training","positive"})
    public void CNT_DEL_003_deleteContentTestSuccessfully() {}

}
