package vn.oes.tests.CourseManagementTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.CourseManagement.CourseLevelPage;

public class CourseLevelTest extends BaseTest {

    SidebarMenu sidebarMenu;
    CourseLevelPage courseLevelPage;

    @BeforeMethod
    public void navigateToCourseLevelPage() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        courseLevelPage = new CourseLevelPage(driver);
        sidebarMenu.gotoCourseLevel();
    }

    @Test(priority = 1)
    public void verifyTitleOfCourseLevelPage() {
        String expectedTitle = "Cấp độ khoá học";
        Assert.assertEquals(courseLevelPage.getTitle(), expectedTitle, "Lỗi: Tiêu đề trang không đúng!");
    }

    @Test(priority = 2)
    public void addCourseLevelSuccessfully() {
        courseLevelPage.handleAddCourseLevel("Auto", "Tự động");
        String actualToastMessage = getToastMessage();
        Assert.assertEquals(actualToastMessage, "Thao tác thành công", "Lỗi: Thông báo sau khi thêm cấp độ khóa học không đúng!");
    }

     @Test(priority = 3)
    public void searchCourseLevelSuccessfully() {

        String keyword = "Tự động";
        courseLevelPage.seachCourseLevel(keyword);
        boolean isFound = courseLevelPage.isCourseLevelExist(keyword);
        Assert.assertTrue(isFound, "Lỗi: Không tìm thấy cấp độ khóa học " + keyword + " trên bảng!");
    }

    @Test(priority = 4)
    public void deleteCourseLevelSuccessfully() {
        courseLevelPage.clickDeleteLevel("Tự động");
        String actualToastMessage = getToastMessage();
        Assert.assertEquals(actualToastMessage, "Thao tác thành công", "Lỗi: Không xóa cấp độ câu hỏi!");
    }

}
