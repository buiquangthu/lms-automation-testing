package vn.oes.tests;

import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import vn.oes.pages.Component.SidebarMenu;
import vn.oes.pages.Modal.UsersModal.*;
import vn.oes.pages.UserManagement.UsersPage;
import vn.oes.utils.WaitUtils;


@Slf4j
public class GotoTest extends BaseTest {
    WaitUtils waitUtils = new WaitUtils(driver);
//    SidebarMenu sidebarMenu = new SidebarMenu(driver);
//    UsersPage usersPage = new UsersPage(driver);

    @Test
    public void gotoUser() throws InterruptedException {
        SidebarMenu sidebarMenu = new SidebarMenu(driver);
        UsersPage usersPage = new UsersPage(driver);
        LoginAdmin();
        sidebarMenu.gotoUser();;
        usersPage.clickAddStudentButton();
        addStuden();
        Thread.sleep(5000);

    }
    @Test
    public void gotoUploadFile() throws InterruptedException {
        LoginAdmin();
        SidebarMenu sidebarMenu = new SidebarMenu(driver);
        UsersPage usersPage = new UsersPage(driver);
        ImportStudentModal importStudentModal = new ImportStudentModal(driver);

        sidebarMenu.gotoUser();
        usersPage.clickImportStudentButton();
        Thread.sleep(3000);
        importStudentModal.uploadFile("D:\\Software_Testing\\Selenium\\LMS_Autotesting\\src\\test\\resources\\sample_org_student.xlsx");
        Thread.sleep(5000);
    }
    @Test
    public void selectUserName() throws InterruptedException {
        SidebarMenu sidebarMenu = new SidebarMenu(driver);
        UsersPage usersPage = new UsersPage(driver);
        LoginAdmin();
        sidebarMenu.gotoUser();
        usersPage.selectUserRadioButtonByName("automation tesing");
        Thread.sleep(2000);
    }

    @Test
    public void selectAllUser() throws InterruptedException {
        SidebarMenu sidebarMenu = new SidebarMenu(driver);
        UsersPage usersPage = new UsersPage(driver);
        LoginAdmin();
        sidebarMenu.gotoUser();
        usersPage.slectAllUserRadioButton();
        Thread.sleep(2000);
    }

    @Test
    public void UpdateUser() throws InterruptedException {
        UsersPage usersPage = new UsersPage(driver);
        EditStudentModal editStudentModal = new EditStudentModal(driver);
        selectUserName();
        usersPage.clickEditStudentButton();
        editStudentModal.selectPosition("Tuyển dụng");
        waitUtils.waitForSeconds(5);
        editStudentModal.selectOrgChart("phòng ban");
        waitUtils.waitForSeconds(5);
        editStudentModal.clickUpdateButton();
    }

    @Test
    public void changeDepartment() throws InterruptedException {
        selectUserName();
        UsersPage usersPage = new UsersPage(driver);
        ChangeDepartments changeDepartments = new ChangeDepartments(driver);
        usersPage.clickChangeDepartmentButton();
        changeDepartments.selectBreanch("-pb_1");
    }

    @Test
    public void changeStatus() throws InterruptedException {
        selectUserName();
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickChangeStatusButton();
        ChangeStatusModal changeStatusModal = new ChangeStatusModal(driver);
        changeStatusModal.clickActiveButton();
    }

    @Test
    public void learningProcess() throws InterruptedException {
        selectUserName();
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickLearningProcessButton();
        assert driver.getCurrentUrl().contains("assign-courses") : "Failed to navigate to Learning Process page";
    }

    @Test
    public void quizResult() throws InterruptedException {
        selectUserName();
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickQuizResultButton();
        assert driver.getCurrentUrl().contains("quiz-results") : "Failed to navigate to Quiz Result page";
    }

    public void addStuden(){
        AddStudentModal addStudentModal = new AddStudentModal(driver);
        addStudentModal.addStudentRequirement("Bui Quang Thu", "automationtesting","bqtt@gmail.com", "12345678", "12345678", "chức danh test", "phòng ban test");
    }



}
