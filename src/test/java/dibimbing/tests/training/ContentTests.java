package dibimbing.tests.training;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.training.ContentChapterPage;
import dibimbing.pages.training.DetailTrainingPage;
import dibimbing.pages.training.EditContentPage;
import dibimbing.pages.training.TrainingListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContentTests extends BaseTest {
    @Test(
            description ="CNT-ADD-001 - Create article content with valid upload - Success create content",
            groups = {"regression","training","positive"})
    public void CNT_ADD_001_createContentArticleSuccessfully() {
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

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training valid =====
        String trainingName = "CNT_ART_SEED_CONTENT" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // Workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid dulu =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_WITH_CONTENT" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        // toast sukses create chapter
        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open chapter paling atas (yang barusan dibuat biasanya jadi top) =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Act: create Article content =====
        String contentTitle = "Article Content " + runId;
        String contentDesc = "Article desc automation " + runId;
        String readDuration = "5"; // menit

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeArticle();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);
        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("login");
        contentChapterPage.clickChooseMediaButton();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.inputReadDuration(readDuration);

        contentChapterPage.clickSubmitAddContent();

        // ===== Assert: toast success create content =====
        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Article Content"
        );

    }

    @Test(
            description = "CNT-ADD-002 - Create video content with valid configuration - Success create content",
            groups = {"regression","training","positive"})
    public void CNT_ADD_002_createContentVideoSuccessfully() {
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

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training valid =====
        String trainingName = "CNT_VID_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // Workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid dulu =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        // Assert toast sukses create chapter
        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open chapter paling atas =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Act: create Video content =====
        String contentTitle = "Video Content " + runId;
        String contentDesc = "Video desc automation " + runId;
        String estVideoDuration = "5"; // menit (Estimated Video Duration)

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.selectContentTypeVideo();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        // upload media
        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("Intro QA");      // samain seperti article test kamu
        contentChapterPage.clickChooseMediaButton();

        // pastikan form sudah balik normal sebelum isi duration
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.inputEstimatedVideoDuration(estVideoDuration);

        contentChapterPage.clickSubmitAddContent();

        // ===== Assert: toast success create content =====
        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Video Content"
        );

    }

    @Test(
            description = "CNT-ADD-003 - Create test content with valid configuration - Success create content",
            groups = {"regression","training","positive"})
    public void CNT_ADD_003_createContentTestSuccessfully() {
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

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training valid =====
        String trainingName = "CNT_TST_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // Workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid dulu =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open chapter paling atas =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Act: create Test content =====
        String contentTitle = "Test Content " + runId;
        String contentDesc = "Test desc automation " + runId;
        String testDuration = "10"; // menit

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.selectContentTypeTest();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        // set duration & shuffle
        contentChapterPage.inputTestDuration(testDuration);
        contentChapterPage.toggleShuffleQuestion(true);

        contentChapterPage.clickSubmitAddContent();

        // ===== Assert: toast success create content =====
        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Test Content"
        );
    }

    @Test(
            description = "CNT-ADD-004 - Create article content with invalid read duration - Validation error displayed",
            groups = {"regression","training","negative"})
    public void CNT_ADD_004_createContentArticleInvalidReadDurationRejected() {
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

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training valid =====
        String trainingName = "CNT_RDINV_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // Workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open first chapter =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Act: create Article content with INVALID Read Duration =====
        String contentTitle = "Article Invalid Duration " + runId;
        String contentDesc = "Article desc automation " + runId;

        // invalid duration (contoh): huruf / simbol / negatif. Pilih salah satu yg beneran dianggap invalid oleh sistemmu.
        String invalidReadDuration = "0";

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.selectContentTypeArticle();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("login");
        contentChapterPage.clickChooseMediaButton();

        // input invalid duration
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.inputReadDuration(invalidReadDuration);

        contentChapterPage.clickSubmitAddContent();

        // ===== Assert: tidak boleh sukses create content =====
        Assert.assertFalse(
                toast.isSuccessCreateContentToastVisible(),
                "Content tetap berhasil dibuat (toast success create content muncul) padahal Read Duration invalid"
        );
    }

    @Test(
            description = "CNT-EDIT-001 - Update article content with valid data - Success update content",
            groups = {"regression","training","positive"})
    public void CNT_EDIT_001_updateContentArticleSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        EditContentPage editContentPage = new EditContentPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training =====
        String trainingName = "CNT_EDIT_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;
        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open first chapter =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Seed: create article content =====
        String contentTitle = "Article Content " + runId;
        String contentDesc = "Article desc automation " + runId;
        String readDuration = "5";

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeArticle();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);
        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("login");
        contentChapterPage.clickChooseMediaButton();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.inputReadDuration(readDuration);
        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Article Content"
        );

        // ===== Act: edit article content (paling atas / content pertama) =====
        // NOTE: kamu perlu method ini di ContentChapterPage:
        // - clickEditTopContent() / clickEditContentByIndex(0)
        contentChapterPage.clickBtnDetailContent();
        editContentPage.clickEditContent();
        editContentPage.verifyEditContentModalVisible();

        String updatedTitle = "Article UPDATED " + runId;
        String updatedDesc  = "Updated desc " + runId;
        String updatedReadDuration = "7";

        editContentPage.clearTitle();
        editContentPage.updateTitle(updatedTitle);

        editContentPage.clearReadDuration();
        editContentPage.updateReadDuration(updatedReadDuration);

        editContentPage.clickSaveUpdate();

        // ===== Assert: toast success update content =====
        Assert.assertTrue(
                toast.isSuccessUpdateContentToastVisible(),
                "Toast success update content tidak muncul setelah update"
        );

        // OPTIONAL: kalau ada cara verifikasi judul content berubah di list:
        // Assert.assertTrue(contentChapterPage.isContentTitleVisible(updatedTitle), "Title content tidak ter-update");
    }

    @Test(
            description = "CNT-EDIT-002 - Update video content with valid data - Success update content",
            groups = {"regression","training","positive"})
    public void CNT_EDIT_002_updateContentVideoSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        EditContentPage editContentPage = new EditContentPage(DriverManager.getDriver()); // gunakan modal edit kamu
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training =====
        String trainingName = "CNT_VID_EDIT_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;
        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open first chapter =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Seed: create VIDEO content =====
        String contentTitle = "Video Content " + runId;
        String contentDesc = "Video desc automation " + runId;
        String estDuration = "3"; // menit

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeVideo();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("Intro QA"); // sesuaikan nama media yang ada di library kamu
        contentChapterPage.clickChooseMediaButton();

        // pastikan field video duration sudah muncul/aktif sebelum type
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.inputEstimatedVideoDuration(estDuration);

        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Video Content"
        );

        // ===== Act: Edit VIDEO content (top content) =====
        contentChapterPage.clickBtnDetailContent();
        editContentPage.clickEditContent();
        editContentPage.verifyEditContentModalVisible();

        String updatedTitle = "Video UPDATED " + runId;

        // Update fields (pastikan modal edit kamu punya clear & update untuk duration)
        editContentPage.clearTitle();
        editContentPage.updateTitle(updatedTitle);

        editContentPage.clickSaveUpdate();

        // ===== Assert =====
        Assert.assertTrue(
                toast.isSuccessUpdateContentToastVisible(),
                "Toast success update content tidak muncul setelah update video content"
        );
    }

    @Test(
            description = "CNT-EDIT-003 - Update test content with valid data - Success update content",
            groups = {"regression","training","positive"})
    public void CNT_EDIT_003_updateContentTestSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        EditContentPage editContentPage = new EditContentPage(DriverManager.getDriver()); // modal edit content kamu
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed: create training valid =====
        String trainingName = "CNT_TST_EDIT_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training (top search result) =====
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        // workaround biar tombol Detail bisa diklik
        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Open Content Chapter tab =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed: create chapter valid =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_SEED_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);

        Assert.assertTrue(
                toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul setelah create chapter"
        );

        // ===== Open chapter paling atas =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Seed: create TEST content =====
        String contentTitle = "Test Content " + runId;
        String contentDesc = "Test content desc " + runId;
        String testDuration = "10"; // menit

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeTest();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        contentChapterPage.inputTestDuration(testDuration);
        contentChapterPage.toggleShuffleQuestion(true); // optional, boleh true/false

        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Test Content"
        );

        // ===== Act: Edit TEST content (top content) =====
        contentChapterPage.clickBtnDetailContent();
        editContentPage.clickEditContent();
        editContentPage.verifyEditContentModalVisible();

        String updatedTitle = "Test UPDATED " + runId;
        String updatedDesc  = "Updated test desc " + runId;
        String updatedDuration = "15";

        editContentPage.clearTitle();
        editContentPage.updateTitle(updatedTitle);

        editContentPage.clickSaveUpdate();

        // ===== Assert: toast success update content =====
        Assert.assertTrue(
                toast.isSuccessUpdateContentToastVisible(),
                "Toast success update content tidak muncul setelah update Test Content"
        );
    }

    @Test(
            description = "CNT-DEL-001 - Delete article content with confirmation - Success delete content",
            groups = {"regression","training","positive"})
    public void CNT_DEL_001_deleteContentArticleSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        EditContentPage editContentPage = new EditContentPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed training =====
        String trainingName = "DEL_ART_SEED_" + runId;
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

        String chapterName = "CHP_DEL_ART_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);
        Assert.assertTrue(toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul");

        contentChapterPage.clickFirstChapterItem();

        // ===== Seed content: ARTICLE =====
        // ===== Act: create Article content =====
        String contentTitle = "Article Content " + runId;
        String contentDesc = "Article desc automation " + runId;
        String readDuration = "5"; // menit

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.selectContentTypeArticle();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);
        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("login");
        contentChapterPage.clickChooseMediaButton();
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.inputReadDuration(readDuration);

        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul (article)");

        contentChapterPage.clickBtnDetailContent();
        editContentPage.clickEditContent();
        editContentPage.verifyEditContentModalVisible();


        // ===== Act: Delete content + confirm =====
        editContentPage.clickDeleteContent();           // method: click(btnDeleteContent)
        editContentPage.confirmDeleteContent();         // method: click(btnConfirmDeleteContent)

        // ===== Assert: toast delete =====
        Assert.assertTrue(
                toast.isSuccessDeleteContentToastVisible(),
                "Toast success delete content tidak muncul (article)"
        );

    }

    @Test(
            description = "CNT-DEL-002 - Delete video content with confirmation - Success delete content",
            groups = {"regression","training","positive"})
    public void CNT_DEL_002_deleteContentVideoSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        EditContentPage editContentPage = new EditContentPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed training =====
        String trainingName = "DEL_VID_SEED_" + runId;
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

        // ===== Content Chapter =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed chapter =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_DEL_VID_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);
        Assert.assertTrue(toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul");

        contentChapterPage.clickFirstChapterItem();

        // ===== Act: create Video content =====
        String contentTitle = "Video Content " + runId;
        String contentDesc = "Video desc automation " + runId;
        String estVideoDuration = "5"; // menit (Estimated Video Duration)

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.selectContentTypeVideo();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        // upload media
        contentChapterPage.clickUploadMedia();
        contentChapterPage.selectMediaByName("Intro QA");      // samain seperti article test kamu
        contentChapterPage.clickChooseMediaButton();

        // pastikan form sudah balik normal sebelum isi duration
        contentChapterPage.verifyAddContentFormVisible();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.inputEstimatedVideoDuration(estVideoDuration);

        contentChapterPage.clickSubmitAddContent();

        Assert.assertTrue(toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul (video)");

        contentChapterPage.clickBtnDetailContent();
        editContentPage.clickEditContent();
        editContentPage.verifyEditContentModalVisible();

        // ===== Act: Delete content + confirm =====
        editContentPage.clickDeleteContent();           // method: click(btnDeleteContent)
        editContentPage.confirmDeleteContent();         // method: click(btnConfirmDeleteContent)


        // ===== Assert =====
        Assert.assertTrue(
                toast.isSuccessDeleteContentToastVisible(),
                "Toast success delete content tidak muncul (video)"
        );

    }

    @Test(
            description = "CNT-DEL-003 - Delete test content with confirmation - Success delete content",
            groups = {"regression","training","positive"})
    public void CNT_DEL_003_deleteContentTestSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ContentChapterPage contentChapterPage = new ContentChapterPage(DriverManager.getDriver());
        EditContentPage editContentPage = new EditContentPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // ===== Seed training =====
        String trainingName = "DEL_TST_SEED_" + runId;
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

        // ===== Content Chapter =====
        detailTrainingPage.openTabContentChapter();
        contentChapterPage.verifyContentChapterSectionLoaded();

        // ===== Seed chapter =====
        contentChapterPage.clickAddChapter();
        contentChapterPage.verifyAddChapterModalVisible();

        String chapterName = "CHP_DEL_TST_" + runId;
        String chapterDesc = "Chapter desc " + runId;

        contentChapterPage.submitCreateChapter(chapterName, chapterDesc);
        Assert.assertTrue(toast.isSuccessCreateChapterToastVisible(),
                "Toast success create chapter tidak muncul");

        // ===== Open chapter paling atas =====
        contentChapterPage.clickFirstChapterItem();

        // ===== Act: create Test content =====
        String contentTitle = "Test Content " + runId;
        String contentDesc = "Test desc automation " + runId;
        String testDuration = "10"; // menit

        contentChapterPage.clickAddContent();
        contentChapterPage.verifyAddContentFormVisible();

        contentChapterPage.selectContentTypeTest();
        contentChapterPage.inputContentTitle(contentTitle);
        contentChapterPage.inputContentDescription(contentDesc);

        // set duration & shuffle
        contentChapterPage.inputTestDuration(testDuration);
        contentChapterPage.toggleShuffleQuestion(true);

        contentChapterPage.clickSubmitAddContent();

        // ===== Assert: toast success create content =====
        Assert.assertTrue(
                toast.isSuccessCreateContentToastVisible(),
                "Toast success create content tidak muncul setelah submit Test Content"
        );

        contentChapterPage.clickBtnDetailContent();
        editContentPage.clickEditContent();
        editContentPage.verifyEditContentModalVisible();

        // ===== Act: Delete content + confirm =====
        editContentPage.clickDeleteContent();           // method: click(btnDeleteContent)
        editContentPage.confirmDeleteContent();         // method: click(btnConfirmDeleteContent)


        // ===== Assert =====
        Assert.assertTrue(
                toast.isSuccessDeleteContentToastVisible(),
                "Toast success delete content tidak muncul (test)"
        );

    }

}
