package vn.oes.tests.CourseManagementTest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.CourseManagement.AddLessonPage;
import vn.oes.pages.CourseManagement.CourseListPage;
import vn.oes.pages.CourseManagement.MaterialPage;

public class MaterialTest extends BaseTest{
    SidebarMenu sidebarMenu;
    CourseListPage courseListPage;
    MaterialPage materialPage;

    @BeforeMethod
    public void setupAddLesson() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        materialPage = new MaterialPage(driver);
        courseListPage = new CourseListPage(driver);
        sidebarMenu.gotoCourseList();
    }

    @Test
    public void testAddMaterial() {
        courseListPage.clickEditCourse("Khóa học Automation Test v1.0");
        courseListPage.selectTab("Tài liệu đính kèm");
        materialPage.addMaterial("Tài liệu đính kèm", "Feautures.docx");
    }

    @Test
    public void verifyModalTitle() {
        courseListPage.clickEditCourse("Khóa học Automation Test v1.0");
        courseListPage.selectTab("Tài liệu đính kèm");
//        materialPage.getModalTitle();
        Assert.assertEquals(materialPage.getModalTitle(), "Thêm bài tập mới");
    }

    @Test
    public void testEdit(){
        courseListPage.clickEditCourse("Khóa học Automation Test v1.0");
        courseListPage.selectTab("Tài liệu đính kèm");
        materialPage.editMaterial("Tài liệu đính kèm", "Tài liệu đính kèm mới", "Feautures.docx");
    }

    @Test
    public void testDelete(){
        courseListPage.clickEditCourse("Khóa học Automation Test v1.0");
        courseListPage.selectTab("Tài liệu đính kèm");
        materialPage.deleteMaterial("Tài liệu đính kèm mới");
    }
}
