package dibimbing.tests.division;

import dibimbing.core.DriverManager;
import dibimbing.pages.DashboardPage;
import dibimbing.pages.components.ToastComponent;
import dibimbing.pages.division.DetailDivisionPage;
import dibimbing.pages.division.DivisionPage;
import dibimbing.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DivisionEditTests extends BaseTest {
    @Test(
            description = "DIV-EDIT-001 - Update division with valid data - Success update division",
            groups = {"regression","division","positive"})
    public void DIV_EDIT_001_updateDivisionSuccessfully() {
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
        String divisionKeyword = "DIV_AUTO_";

        // cari division hasil dari DIV_ADD_001
        divisionPage.searchDivisionByName(divisionKeyword);
        divisionPage.waitDivisionSearchResultLoaded();

        // buka detail division paling atas (terbaru)
        divisionPage.clickDetailTopSearchResult();


        // ===== Act: edit division =====
        detailDivisionPage.clickEditDivision();
        detailDivisionPage.verifyEditDivisionModalVisible();

        String updatedName = divisionKeyword + "_UPD";
        String updatedDesc = "Updated division desc " + "_UPD";

        detailDivisionPage.clearDivisionName();
        detailDivisionPage.updateDivisionName(updatedName);

        detailDivisionPage.clearDivisionDesc();
        detailDivisionPage.updateDivisionDesc(updatedDesc);

        detailDivisionPage.clickSaveEditDivision();

        // ===== Assert: toast success update division =====
        Assert.assertTrue(
                toast.isSuccessUpdateDivisionToastVisible(),
                "Toast success update division tidak muncul"
        );

        Assert.assertEquals(
                toast.getSuccessUpdateDivisionText().trim(),
                "Success update division",
                "Toast text update division tidak sesuai"
        );

    }

}
