package dibimbing.tests.training;

import dibimbing.core.DateUtils;
import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.training.AssignEmployeePage;
import dibimbing.pages.training.DetailTrainingPage;
import dibimbing.pages.training.TrainingListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssignTests extends BaseTest {
    @Test(
            description = "ASN-ASSIGN-001 - Assign employee with valid date range - Success assign employee",
            groups = {"regression","training","positive"})
    public void ASN_ASSIGN_001_assignEmployeeSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        AssignEmployeePage assignEmployeePage = new AssignEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training page =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu(); // pastikan method ini ada di DashboardPage
        trainingListPage.verifyTrainingListPageLoaded();

        // Training yang tidak memiliki content (CNT_ART_SEED_)
        String trainingName = "CNT_ART_SEED_CONTENT";



        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();

        detailTrainingPage.verifyDetailTrainingPageLoaded();

        detailTrainingPage.openTabAssignedEmployee();
        assignEmployeePage.clickAssignEmployeeButton();

        String employeeKeyword = "QA Auto";
        String deadlineDate = DateUtils.plusDaysFromTodayUi(20);
        assignEmployeePage.searchEmployeeToAssign(employeeKeyword);
        assignEmployeePage.clickActionAssignEmployeeTopResult();

        assignEmployeePage.inputDeadlineDate(deadlineDate);
        assignEmployeePage.clickSaveAssignEmployee();


        // ===== Assert toast success assign employee =====
        toast.verifySuccessAssignEmployeeToastVisible();
        Assert.assertTrue(
                toast.isSuccessAssignEmployeeToastVisible(),
                "Toast 'Success assign employee' tidak muncul setelah assign employee"
        );

    }

    @Test(
            description = "ASN-ASSIGN-002 - Assign employee when training has no content - Validation error displayed",
            groups = {"regression","training","negative"})
    public void ASN_ASSIGN_002_assignEmployeeWithoutContentRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        AssignEmployeePage assignEmployeePage = new AssignEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Training page =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu(); // pastikan method ini ada di DashboardPage
        trainingListPage.verifyTrainingListPageLoaded();

        // TRN_CHAPTER_SEED_ Training yang isi nya hanya ada chapter yang tidak ada content di dalamnya
        String trainingName = "TRN_CHAPTER_SEED_";
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();


        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();
        detailTrainingPage.openTabAssignedEmployee();

        assignEmployeePage.clickAssignEmployeeButton();

        String employeeKeyword = "QA Auto";
        String deadlineDate = DateUtils.plusDaysFromTodayUi(20);

        assignEmployeePage.searchEmployeeToAssign(employeeKeyword);
        assignEmployeePage.clickActionAssignEmployeeTopResult();

        assignEmployeePage.inputDeadlineDate(deadlineDate);
        assignEmployeePage.clickSaveAssignEmployee();

        // ===== ASSERT =====
        Assert.assertTrue(
                toast.isErrorCompleteContentFirstToastVisible(),
                "Toast 'Lengkapi konten terlebih dahulu' tidak muncul saat training belum memiliki content"
        );

    }

    @Test(
            description = "ASN-ASSIGN-003 - Assign employee with invalid deadline date - Validation error displayed",
            groups = {"regression","training","negative"})
    public void ASN_ASSIGN_003_assignEmployeeInvalidDeadlineRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        AssignEmployeePage assignEmployeePage = new AssignEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String trainingName = "CNT_ART_SEED_CONTENT"; // training yang sudah ada content

        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();
        detailTrainingPage.verifyDetailTrainingPageLoaded();

        detailTrainingPage.openTabAssignedEmployee();
        assignEmployeePage.clickAssignEmployeeButton();

        String employeeKeyword = "QA Auto";
        assignEmployeePage.searchEmployeeToAssign(employeeKeyword);
        assignEmployeePage.clickActionAssignEmployeeTopResult();

        // ===== Invalid deadline (BEFORE start date) =====
        String invalidDeadlineDate =
                DateUtils.plusDaysFromTodayUi(-20); // ✅ BENAR


        assignEmployeePage.inputDeadlineDate(invalidDeadlineDate);

        assignEmployeePage.clickSaveAssignEmployee();

        Assert.assertTrue(
                toast.isErrorEndDateMustBeLaterToastVisible(),
                "Error toast 'End date must be later than the start date' tidak tampil"
        );
    }

    @Test(
            description = "ASN-EDITDL-001 - Update assignment deadline with valid date - Success update assignment",
            groups = {"regression","training","positive"})
    public void ASN_EDITDL_001_updateAssignmentDeadlineSuccessfully() {

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        AssignEmployeePage assignEmployeePage = new AssignEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String trainingName = "CNT_ART_SEED_CONTENT";
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();

        detailTrainingPage.verifyDetailTrainingPageLoaded();
        detailTrainingPage.openTabAssignedEmployee();

        // ===== Seed: Assign employee (VALID) =====
        assignEmployeePage.clickAssignEmployeeButton();

        String employeeKeyword = "QA Auto";

        String initialDeadlineDate =
                DateUtils.plusDaysFromTodayUi(20);

        assignEmployeePage.searchEmployeeToAssign(employeeKeyword);
        assignEmployeePage.clickActionAssignEmployeeTopResult();

        // ❗ Start Date BIARKAN default dari UI
        assignEmployeePage.inputDeadlineDate(initialDeadlineDate);
        assignEmployeePage.clickSaveAssignEmployee();

        toast.verifySuccessAssignEmployeeToastVisible();
        Assert.assertTrue(
                toast.isSuccessAssignEmployeeToastVisible(),
                "Toast 'Success assign employee' tidak muncul"
        );

        // ===== Act: Update deadline =====
        detailTrainingPage.openTabAssignedEmployee();
        assignEmployeePage.clickDetailAssignedEmployee();
        assignEmployeePage.clickUpdateAssignedEmployee();

        String updatedDeadlineDate =
                DateUtils.plusDaysFromTodayUi(30);

        assignEmployeePage.updateDeadlineDate(updatedDeadlineDate);
        assignEmployeePage.clickSaveUpdateDeadline();

        // ===== Assert =====
        toast.verifySuccessUpdateAssignEmployeeToastVisible();
        Assert.assertTrue(
                toast.isSuccessUpdateAssignEmployeeToastVisible(),
                "Toast 'Success update assign employee' tidak muncul setelah update deadline"
        );
    }

    @Test(
            description = "ASN-EDITDL-002 - Update assignment deadline with invalid date - Validation error displayed",
            groups = {"regression","training","negative"})
    public void ASN_EDITDL_002_updateAssignmentDeadlineInvalidRejected() {

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        AssignEmployeePage assignEmployeePage = new AssignEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String trainingName = "CNT_ART_SEED_CONTENT";
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();

        detailTrainingPage.verifyDetailTrainingPageLoaded();
        detailTrainingPage.openTabAssignedEmployee();

        // ===== Seed: Assign employee VALID =====
        assignEmployeePage.clickAssignEmployeeButton();

        String employeeKeyword = "QA AUTO";
        String initialDeadlineDate =
                DateUtils.plusDaysFromTodayUi(20);

        assignEmployeePage.searchEmployeeToAssign(employeeKeyword);
        assignEmployeePage.clickActionAssignEmployeeTopResult();

        // ❗ Start Date BIARKAN default dari UI
        assignEmployeePage.inputDeadlineDate(initialDeadlineDate);
        assignEmployeePage.clickSaveAssignEmployee();

        toast.verifySuccessAssignEmployeeToastVisible();
        Assert.assertTrue(
                toast.isSuccessAssignEmployeeToastVisible(),
                "Toast 'Success assign employee' tidak muncul"
        );

        // ===== Act: Update deadline INVALID (before start date) =====
        detailTrainingPage.openTabAssignedEmployee();
        assignEmployeePage.clickDetailAssignedEmployee();
        assignEmployeePage.clickUpdateAssignedEmployee();

        String invalidUpdatedDeadlineDate =
                DateUtils.plusDaysFromTodayUi(-30);

        assignEmployeePage.updateDeadlineDate(invalidUpdatedDeadlineDate);
        assignEmployeePage.clickSaveUpdateDeadline();

        // ===== Assert ERROR =====
        Assert.assertTrue(
                toast.isErrorEndDateMustBeLaterToastVisible(),
                "Error toast 'End date must be later than the start date' tidak muncul saat update deadline invalid"
        );
    }

    @Test(
            description = "ASN-DEL-001 - Delete assignment with confirmation - Success delete assignment",
            groups = {"regression","training","positive"})
    public void ASN_DEL_001_deleteAssignmentSuccessfully() {

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        TrainingListPage trainingListPage = new TrainingListPage(DriverManager.getDriver());
        DetailTrainingPage detailTrainingPage = new DetailTrainingPage(DriverManager.getDriver());
        AssignEmployeePage assignEmployeePage = new AssignEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickTrainingSidebarMenu();
        trainingListPage.verifyTrainingListPageLoaded();

        String trainingName = "CNT_ART_SEED_CONTENT";
        trainingListPage.searchTrainingByName(trainingName);
        trainingListPage.waitTrainingSearchResultLoaded();

        trainingListPage.clickAddTraining();
        trainingListPage.clickCloseAddTraining();

        trainingListPage.clickDetailTopSearchResult();

        detailTrainingPage.verifyDetailTrainingPageLoaded();
        detailTrainingPage.openTabAssignedEmployee();

        // ===== Seed: Assign employee (VALID) =====
        assignEmployeePage.clickAssignEmployeeButton();

        String employeeKeyword = "QA AUTO";
        String deadlineDate =
                DateUtils.plusDaysFromTodayUi(20);

        assignEmployeePage.searchEmployeeToAssign(employeeKeyword);
        assignEmployeePage.clickActionAssignEmployeeTopResult();

        // ❗ Start Date BIARKAN default dari UI
        assignEmployeePage.inputDeadlineDate(deadlineDate);
        assignEmployeePage.clickSaveAssignEmployee();

        toast.verifySuccessAssignEmployeeToastVisible();
        Assert.assertTrue(
                toast.isSuccessAssignEmployeeToastVisible(),
                "Toast 'Success assign employee' tidak muncul"
        );

        // ===== Act: Delete assignment =====
        detailTrainingPage.openTabAssignedEmployee();
        assignEmployeePage.clickDetailAssignedEmployee();
        assignEmployeePage.clickDeleteAssignedEmployee();
        assignEmployeePage.confirmDeleteAssignedEmployee();

        // ===== Assert =====
        toast.verifySuccessDeleteAssignEmployeeToastVisible();
        Assert.assertTrue(
                toast.isSuccessDeleteAssignEmployeeToastVisible(),
                "Toast 'Success delete Assigned Employee' tidak muncul setelah delete assignment"
        );
    }
}
