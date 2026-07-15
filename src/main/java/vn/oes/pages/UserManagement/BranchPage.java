package vn.oes.pages.UserManagement;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.pages.Modal.BranchModal.DeleteBranchModal;
import vn.oes.pages.Modal.BranchModal.ImportBranchModal;
import vn.oes.pages.Modal.BranchModal.UpdateBranchModal;
import vn.oes.utils.WaitUtils;

import java.util.List;

@Slf4j
public class BranchPage extends BasePage {
    WaitUtils waitUtils;

    public BranchPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }


    By pageTitle = By.cssSelector("div[class='container-fluid'] div h1");
    By searchInput = By.id("searchBranch");
    By addBranchButton = By.id("add_branch_btn");
    By importBranchButton = By.id("import_org_chart_btn");
    By btnExportDepartment = By.cssSelector(".d-flex.align-content-lg-end");


    public String getPageTitle() {
        return waitUtils.waitForVisible(pageTitle).getText();
    }

    public void inputSearch(String searchText) {
        sendKeys(searchInput, searchText);
    }

    public boolean isBranchExist(String branchName) {
        By branchCell = By.xpath("//span[normalize-space()='" + branchName + "']");
        // Kiểm tra nhanh không dùng wait dài
        List<?> elements = driver.findElements(branchCell);
        if (!elements.isEmpty()) {
            return driver.findElement(branchCell).isDisplayed();
        }
        return false;
    }


    public void clickAddBranchButton() {
        click(addBranchButton);
        waitUtils.waitForSeconds(1);
    }

    public void clickImportBranchButton() {
        click(importBranchButton);
        waitUtils.waitForSeconds(1);
    }

    public void clickDownloadListBranchButton() {
        click(btnExportDepartment);
        waitUtils.waitForSeconds(1);
    }


    /**
     * Mở dropdown thao tác cho một nhóm.
     * @param groupName Tên nhóm cha (nếu có subGroupName) hoặc tên nhóm cần thao tác
     * @param subGroupName (Optional) Tên nhóm con cần thao tác. Truyền null/"" nếu thao tác ở nhóm cha
     */
    public void handleSelectOptionByGroupName(String groupName, String subGroupName) {
        String targetGroupName = groupName;

        if (subGroupName != null && !subGroupName.isEmpty()) {
            By collapseParentButton = By.xpath("//tr[.//span[normalize-space()='" + groupName + "']]//a[@role='button'][contains(@class, 'collapsed')]");
            // Mở thư mục cha nếu đang bị đóng
            if (!driver.findElements(collapseParentButton).isEmpty() && driver.findElement(collapseParentButton).isDisplayed()) {
                click(collapseParentButton);
                waitUtils.waitForSeconds(1);
            }
            targetGroupName = subGroupName;
        }

        By selectButton = By.xpath("//tr[.//span[normalize-space()='" + targetGroupName + "']]//button[contains(@class, 'dropdown-toggle')]");
        waitUtils.waitForClickable(selectButton).click();
    }


    /**
     * Chỉnh sửa thông tin phòng ban.
     * @param parentGroupName Thu hẹp tìm kiếm ở thư mục cha. Truyền tên thư mục muốn sửa nếu là thư mục gốc.
     * @param subGroupName Tên thư mục con muốn sửa. Truyền null/"" nếu muốn sửa thư mục gốc (parentGroupName).
     */
    public void editBranchByName(String parentGroupName, String subGroupName, String newGroupName, String newCodeBranch) {
        handleSelectOptionByGroupName(parentGroupName, subGroupName);
        By selectEdit = By.cssSelector("div[class='dropdown-menu dropdown-menu-right show'] a[class='dropdown-item editBranch']");
        waitUtils.waitForClickable(selectEdit).click();

        UpdateBranchModal updateBranchModal = new UpdateBranchModal(driver);
        updateBranchModal.editGroupName(newGroupName);
        updateBranchModal.editCodeBranch(newCodeBranch);
        
        By updateButton = By.cssSelector("div[role='dialog'] div div div form div div button[type='submit']");
        waitUtils.waitForClickable(updateButton).click();
        waitUtils.waitForSeconds(2);
    }


    private void openDeleteModal(String groupName) {
        handleSelectOptionByGroupName(groupName, null);
        By selectDelete = By.cssSelector("div[class='dropdown-menu dropdown-menu-right show'] a[class='dropdown-item ']");
        waitUtils.waitForClickable(selectDelete).click();
    }

    public void deleteBranchByName(String groupName) {
        openDeleteModal(groupName);
        new DeleteBranchModal(driver).clickDeleteButton();
    }

    public void cancelDeleteBranchByName(String groupName) {
        openDeleteModal(groupName);
        new DeleteBranchModal(driver).clickCancelButton();
    }


    public void importBranch(String resourceFileName) {
        clickImportBranchButton();
        new ImportBranchModal(driver).handleImportBranch(resourceFileName);
        waitUtils.waitForSeconds(2);
    }
}
