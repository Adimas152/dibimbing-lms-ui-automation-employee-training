package dibimbing.tests.division;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.division.DivisionPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DivisionAddTests extends BaseTest {
    @Test(
            description = "DIV-ADD-001 - Create division with valid data - Success create division",
            groups = {"regression","division","positive"})
    public void DIV_ADD_001_createDivisionSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Division tab =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        divisionPage.clickDivisionTab();
        divisionPage.verifyDivisionTabVisible();

        // ===== Open Add Division modal =====
        divisionPage.clickAddDivision();
        divisionPage.verifyAddDivisionModalVisible();

        // ===== Arrange (data unik) =====
        String runId = String.valueOf(System.nanoTime());
        String divisionName = "DIV_AUTO_" + runId;
        String divisionDesc = "Division automation desc " + runId;

        // ===== Act: submit create division =====
        divisionPage.fillDivisionForm(divisionName, divisionDesc);

        // ===== Assert: toast sukses create division =====
        toast.verifySuccessCreateDivisionVisible();
        Assert.assertEquals(
                toast.getSuccessCreateDivisionText().trim(),
                "Success create division",
                "Create division gagal / toast tidak sesuai"
        );
    }

    @Test(
            description = "DIV-ADD-002 - Create division with empty required field - Validation error displayed",
            groups = {"regression","division","negative"})
    public void DIV_ADD_002_createDivisionRequiredFieldEmptyRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());

        login();

        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        divisionPage.clickDivisionTab();
        divisionPage.verifyDivisionTabVisible();

        String runId = String.valueOf(System.nanoTime());

        divisionPage.clickAddDivision();
        divisionPage.verifyAddDivisionModalVisible();

        divisionPage.inputDivisionName("");
        divisionPage.inputDivisionDesc("Division desc valid " + runId);
        divisionPage.clickSaveAddDivision();

        // Assert required name muncul
        divisionPage.verifyDivisionNameRequiredVisible();

    }

    @Test(
            description = "DIV-ADD-003 - Create division with duplicate name - Duplicate error displayed",
            groups = {"regression","division","negative"})
    public void DIV_ADD_003_createDivisionDuplicateNameRejected() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Division tab =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        divisionPage.clickDivisionTab();
        divisionPage.verifyDivisionTabVisible();

        String runId = String.valueOf(System.nanoTime());
        String divisionName = "DIV_DUP_" + runId;
        String divisionDesc = "Division duplicate test " + runId;

        // =================================================
        // SEED: Create division pertama (harus sukses)
        // =================================================
        divisionPage.clickAddDivision();
        divisionPage.verifyAddDivisionModalVisible();

        divisionPage.fillDivisionForm(divisionName, divisionDesc);

        toast.verifySuccessCreateDivisionVisible();
        Assert.assertEquals(
                toast.getSuccessCreateDivisionText().trim(),
                "Success create division"
        );

        // =================================================
        // ACT: Create division kedua dengan nama sama
        // =================================================
        divisionPage.clickAddDivision();
        divisionPage.verifyAddDivisionModalVisible();

        divisionPage.inputDivisionName(divisionName);   // DUPLICATE NAME
        divisionPage.inputDivisionDesc("Another desc " + runId);
        divisionPage.clickSaveAddDivision();

        // =================================================
        // ASSERT: tidak boleh sukses
        // =================================================
        Assert.assertFalse(
                toast.isSuccessCreateDivisionToastVisible(),
                "Division berhasil dibuat padahal nama division duplikat"
        );


    }

}
