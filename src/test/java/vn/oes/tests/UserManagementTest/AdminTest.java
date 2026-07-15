package vn.oes.tests.UserManagementTest;

import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.Modal.UsersModal.AddAdminModal;
import vn.oes.pages.UserManagement.AdminPage;
import vn.oes.utils.WaitUtils;

@Slf4j
public class AdminTest extends BaseTest {
    WaitUtils waitUtils = new WaitUtils(driver);
    SidebarMenu sidebarMenu;
    AdminPage adminPage;
    AddAdminModal addAdminModal;

    @Test
    public void gotoAdmin() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        sidebarMenu.gotoAdmin();
    }

    @Test
    public void addAdmin() throws InterruptedException {
        gotoAdmin();
        adminPage = new AdminPage(driver);
        addAdminModal = new AddAdminModal(driver);

        adminPage.clickAddAdminButton();
        waitUtils.waitForSeconds(2);
        addAdminModal.addNameAdmin("Admin Test auto");
        addAdminModal.addEmailAdmin("adminautomation@oes.vn");
        addAdminModal.addPasswordAdmin("12345678");
        addAdminModal.addConfirmPasswordAdmin("12345678");
        addAdminModal.clickSubmitButton();
        waitUtils.waitForSeconds(2);
    }

    @Test
    public void displayAdmin() throws InterruptedException {
        gotoAdmin();
        adminPage = new AdminPage(driver);
        adminPage.clickDisplayedResults("50");
        waitUtils.waitForSeconds(2);
    }

    @Test
    public void enableActiveStatus() throws InterruptedException {
        gotoAdmin();
        adminPage = new AdminPage(driver);
        adminPage.clickStatusActiveByEmail("adminautomation@oes.vn");
        waitUtils.waitForSeconds(2);
    }


//    @Test
//    public void testDownloadCSV() throws IOException, InterruptedException {
//        String downloadPath = System.getProperty("java.io.tmpdir") + "downloads";
//        Files.createDirectories(Paths.get(downloadPath));
//
//        WebDriver drivers = DriverFactory.initDownloadDriver(downloadPath);
//
//        drivers.get(ConfigReader.get("baseUrl") + "/login");
//        LoginPage loginPage = new LoginPage(drivers);
//        loginPage.login(
//                ConfigReader.get("username"),
//                ConfigReader.get("password")
//        );
//        sidebarMenu = new SidebarMenu(drivers);
//        sidebarMenu.gotoAdmin();
//
//        adminPage = new AdminPage(drivers);
//        adminPage.clickExportCSVButton();
//        // Chờ file xuất hiện
//        File downloadedFile = waitForFileDownloaded(downloadPath, ".csv", 10);
//        Assert.assertTrue(downloadedFile.exists());
//        Assert.assertTrue(downloadedFile.getName().endsWith(".csv"));
//    }

}
