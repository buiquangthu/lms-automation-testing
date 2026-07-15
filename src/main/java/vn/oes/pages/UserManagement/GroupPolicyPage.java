package vn.oes.pages.UserManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.pages.Modal.GroupPolicyModal.AddAdminModal;
import vn.oes.pages.Modal.GroupPolicyModal.AddGroupPolicyModal;
import vn.oes.utils.WaitUtils;
import vn.oes.utils.XPathUtils;

public class GroupPolicyPage extends BasePage {

    WaitUtils waitUtils = new WaitUtils(driver);

    AddGroupPolicyModal addGroupPolicyModal;
    public GroupPolicyPage(WebDriver driver) {
        super(driver);
    }
    By addGroupPolicyButton = By.xpath("//ul[@class='d-flex']//li");
    By dropdownVisibility = By.xpath("//div[@class='nice-select dataTable_select']");
    By optionShowAll = By.xpath("//li[normalize-space()='All']");
    By searchInput = By.xpath("//div[@id='lms_table_filter']//label");
    By addAdminButton = By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//button[@type='button'][normalize-space()='Add Admin']");
    By assignRoleButton = By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//a[@class='dropdown-item'][normalize-space()='Assign Role']");
    By assignCoursesButton = By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//a[@class='dropdown-item'][normalize-space()='Assign Courses']");
    By editButton = By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//button[@type='button'][normalize-space()='Edit']");
    By deleteButton = By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//button[@type='button'][normalize-space()='Delete']");

    public GroupPolicyPage showFullDataTable() {
        waitUtils.waitForClickable(dropdownVisibility).click();
        waitUtils.waitForClickable(optionShowAll).click();
        return this;
    }

    public GroupPolicyPage AddGroupPolicyButton(String groupPolicyName) {

        addGroupPolicyModal = new AddGroupPolicyModal(driver);
        click(addGroupPolicyButton);
        addGroupPolicyModal.handleAddGroupPolicy(groupPolicyName);
        return this;
    }

    public GroupPolicyPage slectOptionAddAdmin(String groupPolicyName){
        By addAdminOptionByName = By.xpath("//tr[td[normalize-space(.)= " + XPathUtils.toXPathLiteral(groupPolicyName) + "]]//button[@class='btn btn-secondary dropdown-toggle']");
        waitUtils.waitForVisible(addAdminOptionByName).click();
        waitUtils.waitForClickable(addAdminButton).click();
        return this;
    }

    public GroupPolicyAssignRolePage selectOptionAssignRole(String groupPolicyName) {
        By assignRoleOptionByName = By.xpath("//tr[td[normalize-space(.)= " + XPathUtils.toXPathLiteral(groupPolicyName) + "]]//button[@class='btn btn-secondary dropdown-toggle']");
        waitUtils.waitForVisible(assignRoleOptionByName).click();
        waitUtils.waitForClickable(assignRoleButton).click();
        waitUtils.waitForUrlContains("org-instructor-policy/assign");
        return new GroupPolicyAssignRolePage(driver);
    }

    public GroupPolicyPage selectOptionAssignCourses(String groupPolicyName) {
        By assignCoursesOptionByName = By.xpath("//tr[td[normalize-space(.)= " + XPathUtils.toXPathLiteral(groupPolicyName) + "]]//button[@class='btn btn-secondary dropdown-toggle']");
        waitUtils.waitForVisible(assignCoursesOptionByName).click();
        waitUtils.waitForClickable(assignCoursesButton).click();
        return this;
    }

    public GroupPolicyPage selectOptionEdit(String groupPolicyName) {
        By editOptionByName = By.xpath("//tr[td[normalize-space(.)= " + XPathUtils.toXPathLiteral(groupPolicyName) + "]]//button[@class='btn btn-secondary dropdown-toggle']");
        waitUtils.waitForVisible(editOptionByName).click();
        waitUtils.waitForClickable(editButton).click();
        return this;
    }
    public GroupPolicyPage selectOptionDelete(String groupPolicyName) {
        By deleteOptionByName = By.xpath("//tr[td[normalize-space(.)= " + XPathUtils.toXPathLiteral(groupPolicyName) + "]]//button[@class='btn btn-secondary dropdown-toggle']");
        waitUtils.waitForVisible(deleteOptionByName).click();
        waitUtils.waitForClickable(deleteButton).click();
        return this;
    }

    public GroupPolicyPage handleAddAdmin(String adminName) {
        AddAdminModal addAdminModal = new AddAdminModal(driver);
        addAdminModal.handleAddAdmin(adminName);
        return this;
    }
}
