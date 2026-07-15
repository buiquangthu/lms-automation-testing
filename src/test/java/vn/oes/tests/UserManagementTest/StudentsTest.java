package vn.oes.tests.UserManagementTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import vn.oes.models.StudentTestData;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.Modal.StudentGroupModal.AddStudentModal;
import vn.oes.pages.UserManagement.StudentsPage;
import vn.oes.utils.WaitUtils;

@Slf4j
public class StudentsTest extends BaseTest {
    WaitUtils waitUtils = new WaitUtils(driver);
    SidebarMenu sidebarMenu;
    StudentsPage studentsPage;
    AddStudentModal addStudentModal;

    @BeforeMethod
     public void navigateToStudentsPage() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        sidebarMenu.gotoStudent();
        studentsPage = new StudentsPage(driver);
        addStudentModal = new AddStudentModal(driver);
    }

    @Test(groups = "Display")
    public void verifyPageAccessFromSidebar() {
        Assert.assertEquals(studentsPage.getPageTitle(), "Học viên");
    }

    @Test(groups = "Search", dataProvider = "getStudentSearchData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyQuickSearchWithResult(StudentTestData data) {
        studentsPage.inputSearch(data.getSearchKeyword());
        if(data.isSuccessExpected()){
            boolean isFound = studentsPage.isStudentGroupExist(data.getSearchKeyword());
            Assert.assertTrue(isFound, "Lỗi: Đã search từ khóa hợp lệ nhưng không tìm thấy nhóm học viên '" + data.getSearchKeyword() + "' trên bảng!");
        }else {
            boolean isFound = studentsPage.isStudentGroupExist(data.getSearchKeyword());
            Assert.assertFalse(isFound, "Lỗi: Đã search từ khóa không hợp lệ nhưng vẫn tìm thấy nhóm học viên '" + data.getSearchKeyword() + "' trên bảng!");
        }
    }

    @Test(groups = "add", dataProvider = "getAddStudentDataFromJson", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyAddStudentModalTitle(StudentTestData data) {

    }

}
