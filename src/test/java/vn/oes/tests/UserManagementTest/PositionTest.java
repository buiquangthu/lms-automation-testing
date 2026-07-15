package vn.oes.tests.UserManagementTest;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import vn.oes.models.PositionTestData;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.Modal.PositionModal.AddPositionModal;
import vn.oes.pages.Modal.PositionModal.DeletePositionModal;
import vn.oes.pages.UserManagement.PositionPage;

@Slf4j
public class PositionTest extends BaseTest {
    SidebarMenu sidebarMenu;
    PositionPage positionPage;
    AddPositionModal addPositionModal;

    @BeforeMethod
    public void navigateToPositionPage() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);

        sidebarMenu.gotoPosition();

        positionPage = new PositionPage(driver);
        addPositionModal = new AddPositionModal(driver);
    }

    @Test(groups = "Display", description = "TC_VTCD_1")
    public void verifyPageAccessFromSidebar(){
        Assert.assertEquals(positionPage.getPageTitle(), "Vị trí chức danh");
    }

    @Test(groups = "Display", description = "TC_VTCD_2")
    public void verifyListDisplay(){
        Assert.assertEquals(positionPage.getName(), "TÊN");
        Assert.assertEquals(positionPage.getCode(), "MÃ");
        Assert.assertEquals(positionPage.getAction(), "HÀNH ĐỘNG");
    }

    @Test(groups = "Display", description = "TC_VTCD_3", dataProvider = "getPositionSearchDataFromJson", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyQuickSearchWithResult(PositionTestData data){
        positionPage.searchPosition(data.getPositionName());

        if (data.isSuccessExpected()){
            boolean isFound = positionPage.isPositionExist(data.getPositionName());
            Assert.assertTrue(isFound, "Lỗi: Đã search từ khóa hợp lệ nhưng không tìm thấy chức danh '" + data.getPositionName() + "' trên bảng!");
        }else {
            boolean isFound = positionPage.isPositionExist(data.getPositionName());
            Assert.assertFalse(isFound, "Lỗi: Đã search từ khóa không hợp lệ nhưng vẫn tìm thấy chức danh '" + data.getPositionName() + "' trên bảng!");
        }
    }

    @Test(groups = "add", description = "TC_VTCD_5")
    public void verifyAddPositionModalTitle() {
        positionPage.addPosition();
        String actualTitle = addPositionModal.getTitlePosition();
        Assert.assertEquals(actualTitle, "Thêm Vị trí", "Lỗi: Tiêu đề modal không đúng!");
    }

    @Test(groups = "add", dataProvider = "getAddPositionData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyAddPosition(PositionTestData data) throws InterruptedException {
        positionPage.addPosition();
        addPositionModal.handleAddPosition(data.getPositionName(), data.getPositionCode());

        if (data.isSuccessExpected()) {
            String actualToastMessage = getToastMessage();
            Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                    "Lỗi: Thêm chức danh '" + data.getPositionName() + "' - thông báo không đúng!");
        } else {
            if (!addPositionModal.isNameFieldValid()) {
                String nameValidation = addPositionModal.getNameValidationMessage();
                Assert.assertEquals(nameValidation, data.getExpectedMessage(),
                        "Lỗi: Thông báo validation trường Tên không đúng!");
            } else if (!addPositionModal.isCodeFieldValid()) {
                String codeValidation = addPositionModal.getCodeValidationMessage();
                Assert.assertEquals(codeValidation, data.getExpectedMessage(),
                        "Lỗi: Thông báo validation trường Mã không đúng!");
            } else {
                String actualToastMessage = getToastMessage();
                Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                        "Lỗi: Dữ liệu không hợp lệ nhưng thông báo lỗi không đúng!");
            }

            Assert.assertTrue(addPositionModal.isModalDisplayed(),
                    "Lỗi: Modal đã đóng dù nhập dữ liệu không hợp lệ!");
        }
    }

    @Test(groups = "import", dataProvider = "getImportPositionData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyImportPosition(PositionTestData data) throws InterruptedException {
        positionPage.importPosition(data.getFileName());

        String actualToastMessage = getToastMessage();
        Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                "Lỗi: Import file '" + data.getFileName() + "' - thông báo không đúng!");
    }

    @Test(groups = "edit", dataProvider = "getEditPositionData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyEditPosition(PositionTestData data) throws InterruptedException {
        positionPage.editPostionByName(data.getTargetPositionName(), data.getNewPositionName(), data.getNewPositionCode());

        String actualToastMessage = getToastMessage();
        Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                "Lỗi: Sửa chức danh '" + data.getTargetPositionName() + "' - thông báo không đúng!");
    }

    @Test(groups = "delete", dataProvider = "getDeletePositionData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyDeletePosition(PositionTestData data) throws InterruptedException {
        if ("confirm".equals(data.getAction())) {
            positionPage.deletePositionByName(data.getTargetPositionName());
            String actualToastMessage = getToastMessage();
            Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                    "Lỗi: Xóa chức danh '" + data.getTargetPositionName() + "' - thông báo không đúng!");
        } else {
            positionPage.cancelDeletePositionByName(data.getTargetPositionName());
            boolean isStillExist = positionPage.isPositionExist(data.getTargetPositionName());
            Assert.assertTrue(isStillExist,
                    "Lỗi: Đã hủy xóa nhưng chức danh '" + data.getTargetPositionName() + "' không còn trên bảng!");
        }
    }

}
