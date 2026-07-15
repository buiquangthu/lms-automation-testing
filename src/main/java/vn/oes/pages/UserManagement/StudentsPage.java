package vn.oes.pages.UserManagement;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.pages.Modal.StudentGroupModal.AddStudentModal;
import vn.oes.pages.Modal.StudentGroupModal.ImportStudentModal;
import vn.oes.utils.WaitUtils;
import vn.oes.utils.XPathUtils;

import java.util.List;

@Slf4j
public class StudentsPage extends BasePage {

    private WaitUtils waitUtils;

    public StudentsPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }


    By pageTitle = By.cssSelector("div[class='container-fluid'] div h1");
    By addStudent = By.id("add_student_btn");
    By importStudent = By.cssSelector("#import_student");
    By studentGroupTable = By.cssSelector("div.nice-select.dataTable_select");
    By showAllOption = By.cssSelector("li[data-value='100000']");
    By searchInput = By.cssSelector("input[aria-controls='lms_table_org_new']");
    By confirmDeleteButton = By.id("delete_link");
    By cancelDeleteButton = By.cssSelector("button[data-dismiss='modal']");


    public String getPageTitle() {
        return waitUtils.waitForVisible(pageTitle).getText();
    }


    public StudentsPage showFullStudentGroupTable() {
        waitUtils.waitForClickable(studentGroupTable).click();
        waitUtils.waitForClickable(showAllOption).click();
        waitUtils.waitForSeconds(1);
        return this;
    }


    public void inputSearch(String searchText) {
        sendKeys(searchInput, searchText);
        waitUtils.waitForSeconds(2);
    }

    public boolean isStudentGroupExist(String groupName) {
        By groupCell = By.xpath("//td[normalize-space()=" + XPathUtils.toXPathLiteral(groupName) + "]");
        List<?> elements = driver.findElements(groupCell);
        if (!elements.isEmpty()) {
            return driver.findElement(groupCell).isDisplayed();
        }
        return false;
    }


    public void openGroupDropdown(String email) {
        By btnAction = By.xpath("//tr[td[normalize-space()='" + email + "']]//button[contains(normalize-space(), 'Hành động')]");
        scrollToElement(btnAction);
        waitUtils.waitForClickable(btnAction).click();
    }

    private By getMenuItemByEmail(String email, String cssClassOrText) {
        // Nếu là edit/delete thì dùng CSS Class, nếu là course/login thì dùng Text
        if (cssClassOrText.equals("editStudent") || cssClassOrText.equals("deleteStudent")) {
            return By.xpath("//tr[td[normalize-space()='" + email + "']]//button[contains(@class, '" + cssClassOrText + "')]");
        } else {
            return By.xpath("//tr[td[normalize-space()='" + email + "']]//a[normalize-space()='" + cssClassOrText + "']");
        }
    }


    public void clickAddStudentGroupButton() {
        waitUtils.waitForVisible(addStudent).click();
    }

//    public void handleAddStudentGroupButton(String studentGroupName, String studentGroupCode) {
//        clickAddStudentGroupButton();
//        new AddStudentModal(driver).handleAddStudent(studentGroupName, studentGroupCode);
//    }

    public void clickImportStudentGroupButton() {
        waitUtils.waitForVisible(importStudent).click();
        waitUtils.waitForSeconds(1);
    }

    public void handleImportStudentGroupButton(String filePath) {
        clickImportStudentGroupButton();
        new ImportStudentModal(driver).handleImportFileInput(filePath);
    }


    // HANH DONG
    public void clickEditStudentGroupButton(String email) {
        openGroupDropdown(email);
        By btnEdit = getMenuItemByEmail(email, "editStudent");
        waitUtils.waitForClickable(btnEdit).click();
    }

    public void deleteStudentGroupByName(String email) {
        openGroupDropdown(email);
        By btnDelete = getMenuItemByEmail(email, "deleteStudent");
        waitUtils.waitForClickable(btnDelete).click();

        // Popup xác nhận xóa
        waitUtils.waitForClickable(confirmDeleteButton).click();
        waitUtils.waitForSeconds(1);
    }

    public void cancelDeleteStudentGroupByName(String email) {
        openGroupDropdown(email);
        By btnDelete = getMenuItemByEmail(email, "deleteStudent");
        waitUtils.waitForClickable(btnDelete).click();

        // Popup xác nhận hủy
        waitUtils.waitForClickable(cancelDeleteButton).click();
        waitUtils.waitForSeconds(1);
    }

    public void clickCourseButtonByName(String email) {
        openGroupDropdown(email);
        By btnCourse = getMenuItemByEmail(email, "Khóa học");
        waitUtils.waitForClickable(btnCourse).click();
    }

    public void clickLoginActivityButtonByName(String email) {
        openGroupDropdown(email);
        By btnLoginAct = getMenuItemByEmail(email, "Hoạt động đăng nhập");
        waitUtils.waitForClickable(btnLoginAct).click();
    }


}
