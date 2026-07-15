package vn.oes.tests.CourseManagementTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.CourseManagement.CourseCategoriesPage;

public class CourseCategoriesTest extends BaseTest {

    SidebarMenu sidebarMenu;
    CourseCategoriesPage courseCategoriesPage;

    @BeforeMethod
    public void navigateToCourseCategoriesPage() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        sidebarMenu.gotoCourseCategories();
        courseCategoriesPage = new CourseCategoriesPage(driver);
    }

    @Test
    public void verifyTitleOfCourseCategoriesPage() {
        String expectedTitle = "Phân nhóm khóa học";
        String actualTitle = courseCategoriesPage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Lỗi: Tiêu đề trang không đúng!");
    }

    @Test
    public void addCourseCategorySuccessfully() {
        courseCategoriesPage.handleAddCourseCategory("Nhom tu dong", "Testdecription",
                "","",
                "","",
                "", "", "");
        String actualToastMessage = getToastMessage();

        Assert.assertEquals(actualToastMessage, "Thao tác thành công", "Lỗi: Thông báo sau khi thêm danh mục khóa học không đúng!");
    }

    @Test
    public void deleteCourseCategorySuccessfully() {
        courseCategoriesPage.clickDeleteCategory("Nhom tu dong");
        String actualToastMessage = getToastMessage();

        Assert.assertEquals(actualToastMessage, "Thao tác thành công", "Lỗi: Thông báo sau khi xóa danh mục khóa học không đúng!");
    }

}
