package dibimbing.tests.employee;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.employee.AddEmployeeModal;
import dibimbing.pages.employee.EmployeeListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EmployeeAddTests extends BaseTest {
    @Test(
            description = "EMP-ADD-001 - Create employee with valid data - Success create employee",
            groups = {"regression","employee","positive"}
    )
    public void EMP_ADD_001_createEmployeeSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();
        employeeListPage.clickAddEmployee();

        String runId = String.valueOf(System.currentTimeMillis());

        String name = "QA Auto " + runId;
        String employeeId = "EMP-" + runId;
        String email = "qa.auto+" + runId + "@example.com";
        String phone = "62" + runId.substring(Math.max(0, runId.length() - 9));
        String role = "Member";

        addEmployeeModal.fillAllFields(
                name, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. QA Automation No. " + runId.substring(runId.length() - 3),
                "1234567890123456", "123456789012345"
        );

        addEmployeeModal.submitCreateEmployee();

        toast.verifySuccessCreateEmployeeVisible();
        Assert.assertEquals(
                toast.getSuccessCreateEmployeeText(),
                "Success create employee",
                "Failed to create employee"
        );
    }

    @Test(
            description = "EMP-ADD-002 - Create employee with empty email - Validation error displayed",
            groups = {"regression","employee","negative"}
        )
    public void EMP_ADD_002_createEmployeeEmailEmptyRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();
        employeeListPage.clickAddEmployee();

        String runId = String.valueOf(System.currentTimeMillis());

        String name = "QA Auto " + runId;
        String employeeId = "EMP-" + runId;
        String email = ""; // EMPTY EMAIL
        String phone = "62" + runId.substring(Math.max(0, runId.length() - 9));
        String role = "Member";

        addEmployeeModal.fillAllFields(
                name, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Negative Test No. 1",
                "1234567890123456", "123456789012345"
        );

        addEmployeeModal.submitCreateEmployee();

        // Validasi error email muncul
        addEmployeeModal.verifyEmailRequiredMessageVisible();

        Assert.assertEquals(
                toast.getErrorCreateEmployeeRequiredFieldsText(),
                "Harap isi field yang wajib diisi."
        );
    }

    @Test(
            description = "EMP-ADD-003 - Create employee with invalid email format - Validation error displayed",
            groups = {"regression","employee","negative"})

    public void EMP_ADD_003_createEmployeeInvalidEmailRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();
        employeeListPage.clickAddEmployee();

        String runId = String.valueOf(System.currentTimeMillis());

        String name = "QA Auto " + runId;
        String employeeId = "EMP-" + runId;
        String email = "qa.auto" + runId + "example.com"; // INVALID (tanpa @)
        String phone = "62" + runId.substring(Math.max(0, runId.length() - 9));
        String role = "Member";

        addEmployeeModal.fillAllFields(
                name, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Negative Test No. 3",
                "1234567890123456", "123456789012345"
        );

        addEmployeeModal.submitCreateEmployee();

        Assert.assertFalse(
                toast.isSuccessCreateEmployeeToastVisible(),
                "Employee berhasil dibuat padahal format email invalid"
        );
    }

    @Test(description =
            "EMP-ADD-004 - Create employee with invalid phone number - Validation error displayed",
            groups = {"regression","employee","negative"})
    public void EMP_ADD_004_createEmployeeInvalidPhoneRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();
        employeeListPage.clickAddEmployee();

        String runId = String.valueOf(System.currentTimeMillis());

        String name = "QA Auto " + runId;
        String employeeId = "EMP-" + runId;
        String email = "qa.auto+" + runId + "@example.com";

        //  INVALID PHONE (terlalu pendek / tidak sesuai format)
        String phone = "34343";

        String role = "Member";

        addEmployeeModal.fillAllFields(
                name, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Negative Test No. 4",
                "1234567890123456", "123456789012345"
        );

        addEmployeeModal.submitCreateEmployee();

        Assert.assertFalse(
                toast.isSuccessCreateEmployeeToastVisible(),
                "Employee berhasil dibuat padahal phone number invalid"
        );

    }

    @Test(
            description = "EMP-ADD-005 - Create employee with duplicate employee ID - Duplicate error displayed",
            groups = {"regression","employee","negative"})
    public void EMP_ADD_005_createEmployeeDuplicateIdRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        String runId = String.valueOf(System.currentTimeMillis());

        // ====== SEED DATA (buat employee pertama) ======
        String duplicateEmployeeId = "EMP-DUP-" + runId;

        employeeListPage.clickAddEmployee();

        String seedName = "Seed QA " + runId;
        String seedEmail = "seed.qa+" + runId + "@example.com";
        String seedPhone = "62" + runId.substring(Math.max(0, runId.length() - 9));
        String role = "Member";

        addEmployeeModal.fillAllFields(
                seedName, duplicateEmployeeId, seedEmail, seedPhone,
                role, "Male",
                "01/01/2000", "Jl. Seed Data No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();

        // Assert seed berhasil dibuat
        toast.verifySuccessCreateEmployeeVisible();
        Assert.assertEquals(toast.getSuccessCreateEmployeeText(), "Success create employee");

        // ====== MAIN TEST (buat employee kedua dengan ID yang sama) ======
        employeeListPage.clickAddEmployee();

        String name2 = "QA Auto Dup " + runId;
        String email2 = "qa.dup+" + runId + "@example.com";
        String phone2 = "62" + System.currentTimeMillis(); // ini panjangnya pasti > 11
        // (kalau kamu butuh phone beda, bikin beda sedikit)

        addEmployeeModal.fillAllFields(
                name2, duplicateEmployeeId, email2, phone2,
                role, "Male",
                "01/01/2000", "Jl. Duplicate Test No. 5",
                "1234567890123456", "123456789012345"
        );

        addEmployeeModal.submitCreateEmployee();

        // pastikan tidak ada toast sukses
        Assert.assertFalse(
                toast.isSuccessCreateEmployeeToastVisible(),
                "Employee berhasil dibuat padahal Employee ID duplikat"
        );
    }


}





