package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.core.TestDataUtil;
import dibimbing.pages.Employee.AddEmployeeModalPage;
import dibimbing.pages.DasboardPage.DashboardPage;
import dibimbing.pages.Employee.EmployeeListPage;
import dibimbing.pages.auth.LoginPage;
import org.testng.annotations.Test;

public class AddEmployeeTest extends BaseTest {
    @Test(description = "Add Employee", groups = {"regression", "test-log"})
    public void EMP_ADD_001_addEmployee() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModalPage addEmployeeModalPage = new AddEmployeeModalPage(DriverManager.getDriver());

        loginPage.login(config.getProperty("test.email"), config.getProperty("test.password"));
        dashboardPage.verifyEmployeeMenuVisible();

        dashboardPage.clickEmployeeMenu();
        employeeListPage.verifyButtonAddEmployeeVisible();
        employeeListPage.clickButtonAddEmployee();

        addEmployeeModalPage.verifyFormAddEmployeeModalVisible();
        addEmployeeModalPage.addNewEmployee(
                TestDataUtil.randomName(),
                TestDataUtil.randomEmpId(),
                TestDataUtil.randomEmail(),
                TestDataUtil.randomPhoneId(),
                "Member"
        );

        employeeListPage.verifyToastAddEmployeeSuccessVisible();

    }
    @Test(description = "EMP-ADD-002 - Add employee ditolak jika Email kosong",
            groups = {"regression", "test-log"})
    public void EMP_ADD_002_addEmployee_EmailRequired() {

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        EmployeeListPage employeeListPage = new EmployeeListPage(DriverManager.getDriver());
        AddEmployeeModalPage addEmployeeModalPage = new AddEmployeeModalPage(DriverManager.getDriver());

        // login
        loginPage.login(config.getProperty("test.email"), config.getProperty("test.password"));
        dashboardPage.verifyEmployeeMenuVisible();

        // buka employee
        dashboardPage.clickEmployeeMenu();
        employeeListPage.verifyButtonAddEmployeeVisible();
        employeeListPage.clickButtonAddEmployee();

        // modal add employee
        addEmployeeModalPage.verifyFormAddEmployeeModalVisible();

        // isi data tapi EMAIL dikosongkan
        addEmployeeModalPage.addNewEmployee(
                TestDataUtil.randomName(),
                TestDataUtil.randomEmpId(),
                "", // email kosong (ini inti TC)
                TestDataUtil.randomPhoneId(),
                "Member"
        );

        // verifikasi expected result
        // pilih salah satu verifikasi berikut (sesuai yang kamu sudah buat):
        // 1) toast failed
        addEmployeeModalPage.verifyEmailRequiredErrorVisible(); // atau verifyToastMessageContains("Required")

        // 2) atau validasi required di field email
        // addEmployeeModalPage.verifyEmailRequiredMessageVisible();
    }

}
