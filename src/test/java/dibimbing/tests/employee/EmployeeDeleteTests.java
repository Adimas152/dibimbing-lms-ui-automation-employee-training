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

public class EmployeeDeleteTests extends BaseTest {
    @Test(
            description = "EMP-DEL-001 - Delete employee with confirmation - Success delete employee",
            groups = {"regression","employee","positive"})
    public void EMP_DEL_001_deleteEmployeeSuccessfully() {
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

        // ===== Seed create employee =====
        String runId = String.valueOf(System.nanoTime());
        String uniqueName = "DEL_SEED_" + runId;

        String employeeId = "EMP-DEL-" + runId;
        String email = "seed.del+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();

        addEmployeeModal.fillAllFields(
                uniqueName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Delete No. 1",
                "1234567890123456", "123456789012345"
        );

        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // ===== Open detail by name (case-sensitive) =====
        employeeListPage.openEmployeeDetailByName(uniqueName);

        // ===== Delete flow =====
        detailEmployeePage.verifyDetailEmployeePageLoaded();
        detailEmployeePage.clickDeleteEmployee();
        detailEmployeePage.verifyDeleteModalVisible();
        detailEmployeePage.confirmDeleteEmployee();

        // ===== Assert toast delete =====
        toast.verifySuccessDeleteEmployeeVisible();
        Assert.assertEquals(
                toast.getSuccessDeleteEmployeeText(),
                "Succes delete employee",
                "Failed to delete employee"
        );
    }
}
