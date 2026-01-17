package dibimbing.tests.employee;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.employee.AddEmployeeModal;
import dibimbing.pages.employee.DetailEmployeePage;
import dibimbing.pages.employee.EmployeeListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeStatusTests extends BaseTest {
    @Test(
            description = "EMP-STS-001 - Inactivate employee account with confirmation - Success inactivate employee",
            groups = {"regression","employee","positive"})
    public void EMP_STS_001_inactivateEmployeeSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Seed: create ACTIVE employee =====
        String runId = String.valueOf(System.nanoTime());
        String fullName = "STS_SEED_" + runId;
        String employeeId = "EMP-STS-" + runId;
        String email = "seed.sts+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                fullName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Status No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // ===== Open detail by name =====
        employeeListPage.openEmployeeDetailByName(fullName);
        detailEmployeePage.verifyDetailEmployeePageLoaded();

        // ===== Act: inactivate with confirmation =====
        detailEmployeePage.clickStatusEmployeeButton();
        detailEmployeePage.verifyActivationModalVisible();
        detailEmployeePage.confirmChangeStatusEmployee();

        // ===== Assert: toast success inactivate + badge jadi Inactive =====
        Assert.assertTrue(
                toast.isSuccessInactivateEmployeeToastVisible(),
                "Toast success inactivate employee account tidak muncul"
        );

        detailEmployeePage.verifyEmployeeStatusInactive();
    }

    @Test(
            description = "EMP-STS-002 - Activate employee account with confirmation - Success activate employee",
            groups = {"regression","employee","positive"})
    public void EMP_STS_002_activateEmployeeSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Seed: create employee (default biasanya Active) =====
        String runId = String.valueOf(System.nanoTime());
        String fullName = "STS2_SEED_" + runId;
        String employeeId = "EMP-STS2-" + runId;
        String email = "seed.sts2+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                fullName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Status No. 2",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // ===== Open detail by name =====
        employeeListPage.openEmployeeDetailByName(fullName);
        detailEmployeePage.verifyDetailEmployeePageLoaded();

        // ===== Precondition: pastikan employee jadi Inactive dulu =====
        // Kalau sudah inactive, skip step ini.
        if (detailEmployeePage.isEmployeeStatusActive()) {
            detailEmployeePage.clickStatusEmployeeButton();
            detailEmployeePage.verifyActivationModalVisible();
            detailEmployeePage.confirmChangeStatusEmployee();

            Assert.assertTrue(
                    toast.isSuccessInactivateEmployeeToastVisible(),
                    "Toast success inactivate employee account tidak muncul (precondition)"
            );

            detailEmployeePage.verifyEmployeeStatusInactive();
        }

        // ===== Act: Activate with confirmation =====
        detailEmployeePage.clickStatusEmployeeButton();
        detailEmployeePage.verifyActivationModalVisible();
        detailEmployeePage.confirmChangeStatusEmployee();

        // ===== Assert: toast success activate + badge jadi Active =====
        Assert.assertTrue(
                toast.isSuccessActivateEmployeeToastVisible(),
                "Toast success activate employee account tidak muncul"
        );

        detailEmployeePage.verifyEmployeeStatusActive();

    }

}
