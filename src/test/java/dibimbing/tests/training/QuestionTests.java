package dibimbing.tests.training;

import dibimbing.tests.base.BaseTest;
import org.testng.annotations.Test;

public class QuestionTests extends BaseTest {
    @Test(description = "QST-CRT-001 - Create single selection question with correct answer - Success save question", groups = {"training","positive"})
    public void QST_CRT_001_createQuestionSingleSelectionSuccessfully() {}

    @Test(description = "QST-CRT-002 - Create multiple selection question with multiple correct answers - Success save question", groups = {"training","positive"})
    public void QST_CRT_002_createQuestionMultipleSelectionSuccessfully() {}

}
