package dibimbing.tests.training;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.training.ContentChapterPage;
import dibimbing.pages.training.DetailTrainingPage;
import dibimbing.pages.training.QuestionPage;
import dibimbing.pages.training.TrainingListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QuestionTests extends BaseTest {
    @Test(
            description = "QST-CRT-001 - Create single selection question with correct answer - Success save question",
            groups = {"regression","training","positive"})
    public void QST_CRT_001_createQuestionSingleSelectionSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        QuestionPage questionPage = new QuestionPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed training =====
        String trainingName = "QST1_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed chapter =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_QST1_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);
        Assert.assertTrue(toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul");

        contentChapterPage.clickFirstChapterItem();

        // ===== Seed content: TEST =====
        String contentTitle = "Test Content " + runId;
        String contentDesc = "Test content desc " + runId;

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeTest();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);
        contentChapterPage.inputTestDuration("10");
        contentChapterPage.toggleShuffleQuestion(true);
        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul (test content)");

        // ===== OPEN QUESTION PAGE =====
        // contoh: contentChapterPage.openTestContentFromTopResult();
        // atau: contentChapterPage.clickTestContentByTitle(contentTitle);
        // Untuk sekarang aku anggap kamu sudah punya cara masuk ke question page.

        contentChapterPage.clickBtnDetailContent();

        questionPage.verifyQuestionSectionLoaded();

        // ===== Act: create single selection question =====

        questionPage.clickAddQuestion();
        questionPage.selectSingleSelection();

        String question = "Single Question " + runId + " ?";
        questionPage.inputQuestionText(question);

        questionPage.createSingleSelectionQuestion(
                "Answer A",
                "Answer B",
                0 // correct = Answer A
        );

        // ===== Assert =====
        Assert.assertTrue(
                toast.isSuccessSaveQuestionToastVisible(),
                "Toast success save question tidak muncul (single selection)"
        );
    }

    @Test(
            description = "QST-CRT-002 - Create multiple selection question with multiple correct answers - Success save question",
            groups = {"regression","training","positive"})
    public void QST_CRT_002_createQuestionMultipleSelectionSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        QuestionPage questionPage = new QuestionPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed training =====
        String trainingName = "QST2_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed chapter =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_QST2_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);
        Assert.assertTrue(toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul");

        contentChapterPage.clickFirstChapterItem();

        // ===== Seed content: TEST =====
        String contentTitle = "Test Content " + runId;
        String contentDesc = "Test content desc " + runId;

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeTest();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);
        contentChapterPage.inputTestDuration("10");
        contentChapterPage.toggleShuffleQuestion(true);
        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul (test content)");

        // ===== OPEN QUESTION PAGE =====


        contentChapterPage.clickBtnDetailContent();

        questionPage.verifyQuestionSectionLoaded();

        // ===== Act: create multiple selection question =====
//        String question = "Multiple Question " + runId + " ?";
//        String[] answers = new String[] {"Option A", "Option B", "Option C"};
//        int[] correctIdx = new int[] {0, 2}; // benar: A dan C

//        questionPage.clickAddQuestion();
//
//        questionPage.selectMultipleSelection();
//
//        questionPage.inputQuestionText(question);

// ===== Act: create single selection question =====
        String question = "Pilih warna bendera Indonesia?";
        String[] answers = {"Merah", "Putih", "Hijau"};

        questionPage.clickAddQuestion();
        questionPage.selectMultipleSelection();
        questionPage.inputQuestionText(question);

        questionPage.createListOfAnswerMultipleSelection(answers[0], answers[1], answers[2]);

        // step sesuai flow kamu:
        // 1) dropdown 0 pilih Merah (index option biasanya 1)
        questionPage.selectCorrectAnswerOptionByIndex(0, 1);

        // 2) klik Add Correct Answer -> muncul dropdown 1
        questionPage.clickAddCorrectAnswer();

        // 3) dropdown 1 pilih Putih (option index biasanya 2)
        questionPage.selectCorrectAnswerOptionByIndex(1, 2);

        questionPage.clickSaveQuestion();


        // ===== Assert =====
        Assert.assertTrue(
                toast.isSuccessSaveQuestionToastVisible(),
                "Toast success save question tidak muncul (multiple selection)"
        );
    }

}
