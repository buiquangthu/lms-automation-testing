package vn.oes.tests.CourseManagementTest;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.models.CourseTestData;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.CourseManagement.AddCoursePage;
import vn.oes.pages.CourseManagement.CourseListPage;

@Slf4j
public class AddCourseTest extends BaseTest {

    SidebarMenu sidebarMenu;
    CourseListPage courseListPage;
    AddCoursePage addCoursePage;

    @BeforeMethod
    public void setupAddCourse() throws InterruptedException {
        LoginAdmin();
        sidebarMenu = new SidebarMenu(driver);
        sidebarMenu.gotoCourseList();
        courseListPage = new CourseListPage(driver);
        addCoursePage = courseListPage.clickAddCourseButton();
    }

    @Test(groups = "add", dataProvider = "getAddCourseData", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void verifyAddCourse(CourseTestData data) {
        addCoursePage.handleAddCourse(data);

        if (data.isSuccessExpected()) {
            String actualToastMessage = getToastMessage();
            Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                    String.format("Test [%s]: Thêm khóa học '%s' - Expected message '%s' nhưng actual là '%s'",
                            data.getTestId(), data.getTitleVi(), data.getExpectedMessage(), actualToastMessage));
        } else {
            String actualToastMessage = getToastMessage();
            Assert.assertEquals(actualToastMessage, data.getExpectedMessage(),
                    String.format("Test [%s]: Dữ liệu không hợp lệ nhưng thông báo lỗi không đúng! Expected '%s' nhưng actual là '%s'",
                            data.getTestId(), data.getExpectedMessage(), actualToastMessage));
        }
    }

}
