package vn.oes.tests.UserManagementTest;

import vn.oes.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.UserManagement.RoleAssignPage;
import vn.oes.utils.WaitUtils;

public class RoleAssignTest extends BaseTest {
    WaitUtils waitUtils = new WaitUtils(driver);
    SidebarMenu sidebarMenu;
    RoleAssignPage roleAssignPage;

    @Test(priority = 0)
    public void handleSelectRoleInstructor() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        roleAssignPage = new RoleAssignPage(driver);
        sidebarMenu.gotoRoleAssign();
        roleAssignPage.selectInstructorByName("Bui Quang Thu");
        waitUtils.waitForSeconds(2);
        Assert.assertEquals(getToastMessage(), "Success", "Message is not correct");
    }

    @Test(priority = 1)
    public void handleSelectRoleStudent() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        roleAssignPage = new RoleAssignPage(driver);
        sidebarMenu.gotoRoleAssign();
        roleAssignPage.handleFilterByRole("student");
        roleAssignPage.selectInstructorByName("Bui Quang Thu");
        waitUtils.waitForSeconds(2);
        Assert.assertEquals(getToastMessage(), "Success", "Message is not correct");
    }


}
