package dibimbing.tests.training;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.training.ContentChapterPage;
import dibimbing.pages.training.DetailTrainingPage;
import dibimbing.pages.training.TrainingListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChapterTests extends BaseTest {
    @Test(
            description = "CHP-ADD-001 - Create chapter with valid data - Success create chapter",
            groups = {"regression","training","positive"})
    public void CHP_ADD_001_createChapterSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Seed: create training dulu =====
        String runId = String.valueOf(System.nanoTime());
        String trainingName = "TRN_CHAPTER_SEED_" + runId;
        String trainingDesc = "Training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training

//        trainingListPage.openTrainingDetailByName(trainingName);

        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // UI refresh trick: open & close Add Training modal
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();


        detailTrainingPage.verifyDetailTrainingPageLoaded();


        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Act: create chapter =====
        String chapterName = "CHAPTER_AUTO_" + runId;
        String chapterDesc = "Chapter desc automation " + runId;

        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();
        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        // ===== Assert =====
        toast.verifySuccessCreateChapterVisible();
        Assert.assertEquals(
                toast.getSuccessCreateChapterText().trim(),
                "Success create chapter",
                "Toast success create chapter tidak sesuai"
        );
    }

    @Test(
            description = "CHP-ADD-002 - Create chapter with empty name - Validation error displayed",
            groups = {"regression","training","negative"})
    public void CHP_ADD_002_createChapterNameEmptyRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu(); // sesuaikan kalau method kamu namanya beda
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Seed: create training valid dulu =====
        String runId = String.valueOf(System.nanoTime());
        String trainingName = "CHP2_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible(); // toast success create program/training

        // ===== Open detail training

//        trainingListPage.openTrainingDetailByName(trainingName);

        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // Trick supaya tombol detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();


        detailTrainingPage.verifyDetailTrainingPageLoaded();


        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Open Add Chapter modal =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible(); // jika ada

        // ===== Act: kosongkan chapter name, isi desc, submit =====
        contentChapterPage.inputChapterDesc("Chapter desc " + runId);
        contentChapterPage.clickConfirmAddChapter();

        // ===== Assert: muncul validasi required pada name =====
        Assert.assertTrue(
                contentChapterPage.isChapterNameRequiredVisible(),
                "Validasi Required untuk Chapter Name tidak muncul saat name dikosongkan"
        );

        // ===== Assert tambahan: memastikan tidak ada toast sukses =====
//        Assert.assertFalse(
//                toast.isSuccessCreateChapterToastVisible(),
//                "Chapter tetap berhasil dibuat (toast success create chapter muncul) padahal name kosong"
//        );

    }

    @Test(
            description = "CHP-EDIT-001 - Update chapter with valid data - Success update chapter",
            groups = {"regression","training","positive"})
    public void CHP_EDIT_001_updateChapterSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Seed: create training valid dulu =====
        String runId = String.valueOf(System.nanoTime());
        String trainingName = "CHP_EDIT_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (pola stabil) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // Trick supaya tombol detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Go to Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid dulu =====
        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();
        contentChapterPage.inputChapterName(chapterName);
        contentChapterPage.inputChapterDesc(chapterDesc);
        contentChapterPage.clickConfirmAddChapter();

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul saat seed chapter"
        );

        // ===== Act: edit chapter (update valid) =====
        // Catatan: locator kamu pakai update-chapter-button-2, artinya target chapter index tertentu.
        // Minimal: langsung klik edit yang sudah kamu set (btnEditChapter)
        contentChapterPage.clickEditChapter();

        String updatedName = "CHP_UPDATED_" + runId;
        String updatedDesc = "Chapter desc updated " + runId;

        contentChapterPage.clearChapterName();
        contentChapterPage.updateChapterName(updatedName);

        contentChapterPage.clearChapterDesc();
        contentChapterPage.updateChapterDesc(updatedDesc);

        contentChapterPage.clickSaveEditChapter();

        // ===== Assert: toast success update chapter =====
        Assert.assertTrue(
                toast.isSuccessUpdateChapterToastVisible(),
                "Toast success update chapter tidak muncul"
        );
    }

    @Test(
            description = "CHP-EDIT-002 - Update chapter with empty name - Validation error displayed",
            groups = {"regression","training","negative"})
    public void CHP_EDIT_002_updateChapterNameEmptyRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Seed: create training valid =====
        String runId = String.valueOf(System.nanoTime());
        String trainingName = "CHP_EDIT2_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (pola stabil) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Go to Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid dulu (biar bisa diedit) =====
        String chapterName = "CHP2_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();
        contentChapterPage.inputChapterName(chapterName);
        contentChapterPage.inputChapterDesc(chapterDesc);
        contentChapterPage.clickConfirmAddChapter();

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul saat seed chapter"
        );

        // ===== Act: edit chapter -> kosongkan name =====
        contentChapterPage.clickEditChapter();

        contentChapterPage.clearChapterName();
        contentChapterPage.clickSaveEditChapter();

        // ===== Assert: required validation name muncul =====
        Assert.assertTrue(
                contentChapterPage.isChapterNameRequiredVisible(),
                "Validasi Required untuk Chapter Name tidak muncul saat name dikosongkan"
        );


    }

}
