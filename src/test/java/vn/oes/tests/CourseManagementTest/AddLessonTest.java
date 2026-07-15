package vn.oes.tests.CourseManagementTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.CourseManagement.AddLessonPage;
import vn.oes.pages.CourseManagement.CourseListPage;

public class AddLessonTest extends BaseTest {

    SidebarMenu sidebarMenu;
    AddLessonPage addLessonPage;
    CourseListPage courseListPage;

    @BeforeMethod
    public void setupAddLesson() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        addLessonPage = new AddLessonPage(driver);
        courseListPage = new CourseListPage(driver);
        sidebarMenu.gotoCourseList();
    }

    @Test
    public void testAddChapterAndLesson() {
        courseListPage.clickAddLesson("Khóa học Automation Test v1.0");
        addLessonPage.openCategorySelectionForChapter("Chương 1","content");

        addLessonPage.selectLessonType("word");

        addLessonPage.selectActionCard("nội dung bài học");

        addLessonPage.fillBasicLessonInfo("Bài học word", "30", "", "Feautures.docx");

        addLessonPage.clickSaveLesson();

    }

    

}
