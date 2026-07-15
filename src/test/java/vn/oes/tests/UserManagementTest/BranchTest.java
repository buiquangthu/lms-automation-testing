package vn.oes.tests.UserManagementTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import vn.oes.models.BranchTestData;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.Modal.BranchModal.AddBranchModal;
import vn.oes.pages.Modal.BranchModal.ImportBranchModal;
import vn.oes.pages.UserManagement.BranchPage;
import vn.oes.utils.WaitUtils;

@Slf4j
public class BranchTest extends BaseTest {
    SidebarMenu sidebarMenu;
    BranchPage branchPage;
    ImportBranchModal importBranchModal;
    AddBranchModal addBranchModal;

    @BeforeMethod
    public void navigateToBranchPage() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        sidebarMenu.gotoBranch();
        branchPage = new BranchPage(driver);
        importBranchModal = new ImportBranchModal(driver);
        addBranchModal = new AddBranchModal(driver);
    }

    @Test(groups = "Display", description = "TC_SDTC_1")
    public void verifyPageAccessFromSidebar() {
        String expectedTitle = "Sơ đồ tổ chức";
        String actualTitle = branchPage.getPageTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "Lỗi: Tiêu đề trang không đúng!");
    }

    @Test(groups = "Search", dataProvider = "getDepartmentSearchData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifySearchFunctionality(BranchTestData data) {
        branchPage.inputSearch(data.getDepartmentName());
        if (data.isSuccessExpected()){
            boolean isFound = branchPage.isBranchExist(data.getDepartmentName());
            Assert.assertTrue(isFound, "Lỗi: Đã search từ khóa hợp lệ nhưng không tìm thấy phòng ban '");
        }else {
            boolean isFound = branchPage.isBranchExist(data.getDepartmentName());
            Assert.assertFalse(isFound, "Lỗi: Đã search từ khóa không hợp lệ nhưng vẫn tìm thấy phòng ban '");
        }
    }

    @Test(groups = "add", dataProvider = "getAddDepartmentData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyAddBranchModalTitle(BranchTestData data) {
        branchPage.clickAddBranchButton();
        addBranchModal.handleAddBranch(data.getDepartmentName(), data.getDepartmentCode(), data.getParentDepartment());

        if(data.isSuccessExpected()){
            Assert.assertEquals(getToastMessage(), data.getExpectedMessage(), "Lỗi: Thông báo sau khi thêm phòng ban không đúng!");
        }else {
            if(!addBranchModal.isNameFieldValid()){
                String nameValidation = addBranchModal.getNameValidationMessage();
                Assert.assertEquals(nameValidation, data.getExpectedMessage(), "Lỗi: Thông báo validation cho trường tên phòng ban không đúng!");
            }else if (!addBranchModal.isCodeFieldValid()){
                String codeValidation = addBranchModal.getCodeValidationMessage();
                Assert.assertEquals(codeValidation, data.getExpectedMessage(), "Lỗi: Thông báo validation cho trường mã phòng ban không đúng!");

            }else{
                String actualToastMessage = getToastMessage();
                Assert.assertEquals(actualToastMessage, data.getExpectedMessage(), "Lỗi: Thông báo sau khi thêm phòng ban không đúng!");
            }
        }
    }

    @Test(groups = "import", dataProvider = "getImportDepartmentData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyImportBranchModalTitle(BranchTestData data) {
        branchPage.importBranch(data.getFileName());

        String actualToastMessage = getToastMessage();
        Assert.assertEquals(actualToastMessage, data.getExpectedMessage(), "Lỗi: Thông báo sau khi import phòng ban không đúng!");
    }

    @Test(groups = "edit", dataProvider = "getEditDepartmentData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyEditBranch(BranchTestData data) {
        branchPage.editBranchByName(data.getTargetDepartmentName(), data.getTargetSubDepartmentName(),data.getNewDepartmentName(), data.getNewDepartmentCode());

        String actualToastMessage = getToastMessage();
        Assert.assertEquals(actualToastMessage, data.getExpectedMessage(), "Lỗi: Thông báo sau khi chỉnh sửa phòng ban không đúng!");
    }

    @Test(groups = "delete", dataProvider = "getDeleteDepartmentData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyDeleteBranch(BranchTestData data) {
        if(data.isSuccessExpected()){
            branchPage.deleteBranchByName(data.getTargetDepartmentName());
            String actualToastMessage = getToastMessage();
            Assert.assertEquals(actualToastMessage, data.getExpectedMessage(), "Lỗi: Thông báo sau khi xóa phòng ban không đúng!");
        }else {
            branchPage.cancelDeleteBranchByName(data.getTargetDepartmentName());
            boolean isStillExist = branchPage.isBranchExist(data.getTargetDepartmentName());
            Assert.assertTrue(isStillExist, "Lỗi: Đã hủy xóa nhưng phòng ban '" + data.getTargetDepartmentName() + "' không còn trên bảng!");
        }
    }




}
