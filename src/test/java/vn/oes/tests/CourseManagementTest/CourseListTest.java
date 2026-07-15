package vn.oes.tests.CourseManagementTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.CourseManagement.CourseListPage;

public class CourseListTest extends BaseTest {

    SidebarMenu sidebarMenu;
    CourseListPage courseListPage;

    @BeforeMethod
    public void navigateToCourseList() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        sidebarMenu.gotoCourseList();
        courseListPage = new CourseListPage(driver);
    }


    @Test
    public void testAddLesson(){
        courseListPage.clickAddLesson("Khóa học Automation Test v1.0");
    }

    @Test
    public void testDeleteCourse(){
        courseListPage.clickDeleteCourse("Khoá học Tester 12 (new)");
    }
}
