package dibimbing.tests.division;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.division.DetailDivisionPage;
import dibimbing.pages.division.DivisionPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DivisionDeleteTests extends BaseTest {
    @Test(
            description = "DIV-DEL-001 - Delete division without employee - Success delete division",
            groups = {"regression","division","positive"})
    public void DIV_DEL_001_deleteDivisionSuccessfully() {
        DashboardPage dashboardPage = new DashboardPage(DriverManager.getDriver());
        DivisionPage divisionPage = new DivisionPage(DriverManager.getDriver());
        DetailDivisionPage detailDivisionPage = new DetailDivisionPage(DriverManager.getDriver());
        ToastComponent toast = new ToastComponent(DriverManager.getDriver());

        login();

        // ===== Navigate to Division tab =====
        dashboardPage.verifyEmployeeTrainingMenuVisible();
        dashboardPage.clickEmployeeSidebareMenu();
        divisionPage.clickDivisionTab();
        divisionPage.verifyDivisionTabVisible();

        // ===== Seed: create division baru (no employee) =====
        String runId = String.valueOf(System.nanoTime());
        String divisionName = "DIV_DEL_SEED_" + runId;
        String divisionDesc = "Division delete seed " + runId;

        divisionPage.clickAddDivision();
        divisionPage.verifyAddDivisionModalVisible();
        divisionPage.fillDivisionForm(divisionName, divisionDesc);

        // pastikan seed berhasil
        toast.verifySuccessCreateDivisionVisible();

        // ===== Open detail division by name =====
        divisionPage.openDivisionDetailByName(divisionName);
        detailDivisionPage.verifyDetailDivisionPageLoaded();

        detailDivisionPage.clickEditDivision();

        // ===== Act: delete with confirmation =====
        detailDivisionPage.clickDeleteDivision();
        detailDivisionPage.confirmDeleteDivision();

        // ===== Assert: toast delete division =====
        Assert.assertTrue(
                toast.isSuccessDeleteDivisionToastVisible(),
                "Toast success delete division tidak muncul"
        );
        Assert.assertEquals(
                toast.getSuccessDeleteDivisionText().trim(),
                "Success delete division",
                "Toast text delete division tidak sesuai"
        );
    }
//
//    @Test(
//            description = "DIV-DEL-002 - Delete division that has employees - Validation error displayed",
//            groups = {"regression","division","negative"})
//    public void DIV_DEL_002_deleteDivisionHasEmployeeRejected() {}

}
