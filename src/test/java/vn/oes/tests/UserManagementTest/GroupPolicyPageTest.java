package vn.oes.tests.UserManagementTest;

import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.UserManagement.GroupPolicyAssignRolePage;
import vn.oes.pages.UserManagement.GroupPolicyPage;
import vn.oes.utils.WaitUtils;

@Slf4j
public class GroupPolicyPageTest extends BaseTest {
    WaitUtils waitUtils = new WaitUtils(driver);
    SidebarMenu sidebarMenu;
    GroupPolicyPage groupPolicyPage;
    GroupPolicyAssignRolePage groupPolicyAssignRolePage;


    @Test
    public void handleAddGroupPolicy() throws InterruptedException {
        sidebarMenu = new SidebarMenu(driver);
        groupPolicyPage = new GroupPolicyPage(driver);
        LoginAdmin();
        sidebarMenu.gotoGroupPolicy();
        groupPolicyPage.AddGroupPolicyButton("Test auto group policy")
                .slectOptionAddAdmin("Test auto group policy");
        waitUtils.waitForSeconds(2);
    }

    @Test
    public void checkAddAdminGroupPolicy() throws InterruptedException {
        sidebarMenu = new SidebarMenu(driver);
        groupPolicyPage = new GroupPolicyPage(driver);
        LoginAdmin();
        sidebarMenu.gotoGroupPolicy();
        groupPolicyPage.showFullDataTable()
                .slectOptionAddAdmin("Test auto group policy")
                .handleAddAdmin("admin test auto");

    }

    @Test
    public void checkAssignRoleGroupPolicy() throws InterruptedException {
        sidebarMenu = new SidebarMenu(driver);
        groupPolicyPage = new GroupPolicyPage(driver);
        LoginAdmin();
        sidebarMenu.gotoGroupPolicy();
        groupPolicyAssignRolePage = groupPolicyPage.showFullDataTable()
                .selectOptionAssignRole("Test auto group policy");
        waitUtils.waitForSeconds(2);
        groupPolicyAssignRolePage.handleSelectBranchByName("Phong ban 1")
                        .handleSeleactCategoryByName("Default");
        waitUtils.waitForSeconds(5);
        
    }

}
