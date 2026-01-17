package dibimbing.tests.employee;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.employee.AddEmployeeModal;
import dibimbing.pages.employee.DetailEmployeePage;
import dibimbing.pages.employee.EditEmployeeModal;
import dibimbing.pages.employee.EmployeeListPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeEditTests extends BaseTest {
    @Test(
            description = "EMP-EDIT-001 - Update employee with valid data - Success update employee",
            groups = {"regression","employee","positive"})
    public void EMP_EDIT_001_updateEmployeeSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        EditEmployeeModal editEmployeeModal = new EditEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Seed: create employee (biar data pasti ada untuk di-edit) =====
        String runId = String.valueOf(System.nanoTime());
        String fullName = "EDIT_SEED_" + runId;
        String employeeId = "EMP-EDIT-" + runId;
        String email = "seed.edit+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                fullName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Edit No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();

        // Assert seed sukses dibuat
        Assert.assertTrue(
                toast.isSuccessCreateEmployeeToastVisible(),
                "Toast success create employee tidak muncul (seed gagal dibuat)"
        );

        // ===== Open detail by fullname (case sensitive) =====
        employeeListPage.openEmployeeDetailByName(fullName);
        detailEmployeePage.verifyDetailEmployeePageLoaded();

        // ===== Act: click Edit -> update data -> Save =====
        detailEmployeePage.clickEditEmployee();
        editEmployeeModal.verifyEditEmployeeModalVisible();

        String updatedName = fullName + "_UPDATED";
        String updatedEmail = "updated.edit+" + runId + "@example.com";
        String updatedPhone = "62" + String.valueOf(System.currentTimeMillis()).substring(0, 10);
        String updatedRole = "qa";
        String updatedAddress = "Jl. Updated Edit No. " + runId.substring(0, 4);

        editEmployeeModal.updateEmployeeFields(
                updatedName,
                updatedEmail,
                updatedPhone,
                updatedRole,
                null,              // birthDate optional
                updatedAddress,
                null,              // nik optional
                null               // npwp optional
        );

        // kalau mau test dropdown / radio, uncomment:
        // editEmployeeModal.selectAngkatan2024Genap();
        // editEmployeeModal.selectGenderFemale();
        // editEmployeeModal.selectDivisionQualityAssuranceDataTestUpdate();

        editEmployeeModal.clickSaveChanges();

        // ===== Assert: toast success update employee muncul =====
        toast.verifySuccessUpdateEmployeeVisible();
        Assert.assertEquals(
                toast.getSuccessUpdateEmployeeText().trim(),
                "Success update employee",
                "Toast update employee tidak sesuai"
        );

        // ===== Optional Assert: pastikan field sudah ke-update (ambil prefill lagi) =====
        // (Jika setelah save modal masih terbuka, kamu bisa cek ini)
        // Assert.assertEquals(editEmployeeModal.getCurrentName(), updatedName);
        // Assert.assertEquals(editEmployeeModal.getCurrentEmail(), updatedEmail);
    }

    @Test(
            description = "EMP-EDIT-002 - Update employee with empty name - Validation error displayed",
            groups = {"regression","employee","negative"})
    public void EMP_EDIT_002_updateEmployeeNameEmptyRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        EditEmployeeModal editEmployeeModal = new EditEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Seed: create employee (valid) =====
        String runId = String.valueOf(System.nanoTime());
        String seedName = "EDIT2_SEED_" + runId;
        String employeeId = "EMP-EDIT2-" + runId;
        String email = "seed.edit2+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                seedName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Edit2 No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // ===== Open Detail -> Edit =====
        employeeListPage.openEmployeeDetailByName(seedName);
        detailEmployeePage.verifyDetailEmployeePageLoaded();

        detailEmployeePage.clickEditEmployee();
        editEmployeeModal.verifyEditEmployeeModalVisible();

        // ===== Act: kosongkan name lalu save =====
        // Penting: pakai clearAndType() (atau helper clear strong) biar benar-benar kosong
        editEmployeeModal.clearName(); // kosongkan name
        editEmployeeModal.clickSaveChanges();

        // ===== Assert tambahan: toast error required field (sesuaikan locator/text kalau beda) =====
        Assert.assertTrue(
                toast.isErrorUpdateEmployeeRequiredFieldsVisible(),
                "Toast error 'Please fill required field"
        );
    }

    @Test(
            description = "EMP-EDIT-003 - Update employee with invalid email - Validation error displayed",
            groups = {"regression","employee","negative"})
    public void EMP_EDIT_003_updateEmployeeInvalidEmailOrPhoneRejected() {

        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        EditEmployeeModal editEmployeeModal = new EditEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        // ===== Seed: create employee valid dulu =====
        String runId = String.valueOf(System.nanoTime());
        String fullName = "EDIT3_SEED_" + runId;
        String employeeId = "EMP-EDIT3-" + runId;
        String email = "seed.edit3+" + runId + "@example.com";
        String phone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                fullName, employeeId, email, phone,
                role, "Male",
                "01/01/2000", "Jl. Seed Edit3 No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // ===== Open detail employee =====
        employeeListPage.openEmployeeDetailByName(fullName);
        detailEmployeePage.verifyDetailEmployeePageLoaded();

        // ===== Open edit modal =====
        detailEmployeePage.clickEditEmployee();
        editEmployeeModal.verifyEditEmployeeModalVisible();

        // ===== Act: update dengan invalid email =====
        // simpan email valid sekarang (jaga-jaga)
        String currentEmail = editEmployeeModal.getCurrentEmail();

        // email invalid (tanpa '@')
        String invalidEmail = "invalid.email" + runId + "example.com";
        editEmployeeModal.clearEmail();
        editEmployeeModal.updateEmail(invalidEmail);
        editEmployeeModal.clickSaveChanges();

        // ===== Assert: harusnya tidak sukses update =====
        Assert.assertFalse(
                toast.isSuccessUpdateEmployeeToastVisible(),
                "Employee berhasil di-update padahal format email invalid"
        );

    }

    @Test(
            description = "EMP-EDIT-004 - Update employee with duplicate ID - Duplicate error displayed",
            groups = {"regression","employee","negative"})
    public void EMP_EDIT_004_updateEmployeeDuplicateRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModal addEmployeeModal = new AddEmployeeModal(DriverManager.getDriver());
        DetailEmployeePage detailEmployeePage = new DetailEmployeePage(DriverManager.getDriver());
        EditEmployeeModal editEmployeeModal = new EditEmployeeModal(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Employee List =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        employeeListPage.verifyEmployeeListPageLoaded();

        String runId = String.valueOf(System.nanoTime());

        // =========================================================
        // SEED 1: Create Employee A (ID yang akan diduplikasi)
        // =========================================================
        String empAName = "EDIT4_A_" + runId;
        String empAId = "EMP-EDIT4-A-" + runId;
        String empAEmail = "seed.edit4a+" + runId + "@example.com";
        String empAPhone = "62" + runId.substring(0, 10);
        String role = "Member";

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                empAName, empAId, empAEmail, empAPhone,
                role, "Male",
                "01/01/2000", "Jl. Seed Edit4 A No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // =========================================================
        // SEED 2: Create Employee B (yang akan di-edit)
        // =========================================================
        String empBName = "EDIT4_B_" + runId;
        String empBId = "EMP-EDIT4-B-" + runId;
        String empBEmail = "seed.edit4b+" + runId + "@example.com";
        String empBPhone = "62" + String.valueOf(System.nanoTime()).substring(0, 10);

        employeeListPage.clickAddEmployee();
        addEmployeeModal.fillAllFields(
                empBName, empBId, empBEmail, empBPhone,
                role, "Male",
                "01/01/2000", "Jl. Seed Edit4 B No. 1",
                "1234567890123456", "123456789012345"
        );
        addEmployeeModal.submitCreateEmployee();
        toast.verifySuccessCreateEmployeeVisible();

        // =========================================================
        // ACT: Open detail Employee B -> Edit -> set Employee ID = empAId (duplicate)
        // =========================================================
        employeeListPage.openEmployeeDetailByName(empBName);
        detailEmployeePage.verifyDetailEmployeePageLoaded();

        detailEmployeePage.clickEditEmployee();
        editEmployeeModal.verifyEditEmployeeModalVisible();

        // simpan current Employee ID utk assert tambahan (optional)
        String currentEmpId = editEmployeeModal.getCurrentEmployeeId();

        // Update Employee ID menjadi ID milik employee A (duplicate)
        // NOTE: pastikan kamu punya method clearEmployeeId() di EditEmployeeModal
        editEmployeeModal.clearEmployeeId();
        editEmployeeModal.updateEmployeeId(empAId);

        editEmployeeModal.clickSaveChanges();

        // =========================================================
        // ASSERT: tidak boleh sukses update
        // =========================================================
        Assert.assertFalse(
                toast.isSuccessUpdateEmployeeToastVisible(),
                "Employee berhasil di-update padahal Employee ID duplikat"
        );

        // (Optional) Assert tambahan: modal masih tampil & value tidak berubah
        editEmployeeModal.verifyEditEmployeeModalVisible();
        Assert.assertEquals(
                editEmployeeModal.getCurrentEmployeeId(),
                currentEmpId,
                "Employee ID berubah padahal seharusnya ditolak (duplikat)"
        );
    }

}
