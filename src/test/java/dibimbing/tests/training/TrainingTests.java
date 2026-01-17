package dibimbing.tests.training;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.training.DetailTrainingPage;
import dibimbing.pages.training.TrainingListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TrainingTests extends BaseTest {
    @Test(
            description = "TRN-ADD-001 - Create training with valid data - Success create training",
            groups = {"regression","training","positive"})
    public void TRN_ADD_001_createTrainingSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training page =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu(); // pastikan method ini ada di DashboardPage
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Open Add Training modal =====
        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();

        // ===== Arrange (data unik) =====
        String runId = String.valueOf(System.nanoTime());
        String trainingName = "TRN_AUTO_" + runId;
        String trainingDesc = "Training automation desc " + runId;

        // ===== Act: submit create training =====
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);

        // ===== Assert: toast sukses =====
        Assert.assertTrue(
                toast.isSuccessCreateProgramToastVisible(),
                "Toast success create program tidak muncul"
        );

        Assert.assertEquals(
                toast.getSuccessCreateProgramText().trim(),
                "Success create program",
                "Toast text create training/program tidak sesuai"
        );
    }

    @Test(
            description = "TRN-ADD-002 - Create training with empty name - Validation error displayed",
            groups = {"regression","training","negative"})
    public void TRN_ADD_002_createTrainingNameEmptyRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training page =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu(); // pastikan method ini ada
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Open Add Training modal =====
        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();

        // ===== Arrange (Name kosong, Desc valid) =====
        String runId = String.valueOf(System.nanoTime());
        String name = ""; // EMPTY NAME
        String desc = "Training desc valid " + runId;

        // ===== Act =====
        trainingListPage.inputTrainingName(name);
        trainingListPage.inputTrainingDesc(desc);
        trainingListPage.clickConfirmAddTraining();

        // ===== Assert: required message tampil =====
        trainingListPage.verifyTrainingNameRequiredVisible();
    }

    @Test(
            description = "TRN-EDIT-001 - Update training with valid data - Success update training",
            groups = {"regression","training","positive"})
    public void TRN_EDIT_001_updateTrainingSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        // ===== Seed: create training valid dulu =====
        String runId = String.valueOf(System.nanoTime());
        String trainingName = "TRN_EDIT_SEED_" + runId;
        String trainingDesc = "Seed training desc " + runId;

        trainingListPage.clickAddTraining();
        trainingListPage.verifyAddTrainingModalVisible();
        trainingListPage.fillTrainingForm(trainingName, trainingDesc);

        // assert create sukses (toast create program)
        toast.verifySuccessCreateProgramVisible();

        // ===== Open detail training by name =====
        // trainingListPage.openTrainingDetailByName(trainingName);
        trainingListPage.clickDetailTrainingByName(trainingName);

        detailTrainingPage.verifyDetailTrainingPageLoaded();

        // ===== Act: update training =====
        detailTrainingPage.clickEditTraining();
        detailTrainingPage.verifyEditTrainingFormVisible();

        String updatedName = trainingName + "_UPD";
        String updatedDesc = "Updated training desc " + runId;

        detailTrainingPage.clearTrainingName();
        detailTrainingPage.updateTrainingName(updatedName);

        detailTrainingPage.clearTrainingDesc();
        detailTrainingPage.updateTrainingDesc(updatedDesc);

        detailTrainingPage.clickSaveEditTraining();

        // ===== Assert: toast update program =====
        Assert.assertTrue(
                toast.isSuccessUpdateProgramToastVisible(),
                "Toast success update program tidak muncul"
        );

        Assert.assertEquals(
                toast.getSuccessUpdateProgramText().trim(),
                "Success update program",
                "Toast text update training/program tidak sesuai"
        );
    }

}
