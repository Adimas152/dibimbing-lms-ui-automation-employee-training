package dibimbing.tests.employee;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.employee.AddEmployeeModal;
import dibimbing.pages.employee.EmployeeListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeSearchTests  extends BaseTest {
    @Test(
            description = "EMP-SRC-001 - Search employee by name keyword - Employee data displayed",
            groups = {"regression","employee","positive"})
    public void EMP_SRC_001_searchEmployeeByNameSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Seed: create employee =====
        String runId = String.valueOf(System.nanoTime());
        String fullName = "SRC_SEED_" + runId;
        String employeeId = "EMP-SRC-" + runId;
        String email = "seed.src+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                fullName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Search No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();

        toast.verifySuccessCreateEmployeeVisible();

        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Act: search by keyword =====

        employeeListPage.verifyEmployeeListPageLoaded();

        employeeListPage.searchEmployeeByName(fullName);

        // ===== Assert: row yang dibuat muncul =====
        employeeListPage.verifyEmployeeRowByNameVisible(fullName);
    }


}
