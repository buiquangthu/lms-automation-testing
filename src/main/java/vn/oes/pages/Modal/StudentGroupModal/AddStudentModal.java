package vn.oes.pages.Modal.StudentGroupModal;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.models.StudentTestData;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AddStudentModal extends BasePage {
    WaitUtils waitUtils;

    public AddStudentModal(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    By studentName = By.id("addName");
    By username = By.id("addUsername");
    By email = By.id("addEmail");
    By studentId = By.id("addEmployeeId");
    By password = By.id("addPassword");
    By confirmPassword = By.id("addCpassword");
    By phoneNumber = By.id("addPhone");
    By saveButton = By.xpath("//div[@id='add_student']//button[@id='save_button_parent']");


    public void handleSelectPosition(String position) {
        if (position != null && !position.isEmpty()) {
            By positionDropdown = By.xpath("//div[contains(@class,'nice-select') and .//li[@data-display='Lựa chọn Chức danh']]");
            waitUtils.waitForClickable(positionDropdown).click();

            By positionOption = By.xpath("//div[contains(@class,'nice-select') and .//li[@data-display='Lựa chọn Chức danh']]//ul//li[normalize-space(.)='" + position + "']");
            waitUtils.waitForClickable(positionOption).click();
        }
    }

    public void handleSelectBranch(String branchName) {
        if (branchName != null && !branchName.isEmpty()) {
            By branchDropdown = By.xpath("//div[contains(@class,'nice-select') and .//li[@data-display='Lựa chọn Sơ đồ tổ chức']]");
            waitUtils.waitForClickable(branchDropdown).click();

            By branchOption = By.xpath("//div[contains(@class,'nice-select') and .//li[@data-display='Lựa chọn Sơ đồ tổ chức']]//ul//li[normalize-space(.)='" + branchName + "']");
            waitUtils.waitForClickable(branchOption).click();
        }
    }
//
//    public void handleAddStudent(String studentName, String username, String email, String studentId, String password, String confirmPassword, String position, String branchName) {
//        sendKeys(this.studentName, studentName);
//        sendKeys(this.username, username);
//        sendKeys(this.email, email);
//        sendKeys(this.studentId, studentId);
//        handleSelectPosition(position);
//        handleSelectBranch(branchName);
//        sendKeys(this.password, password);
//        sendKeys(this.confirmPassword, confirmPassword);
//        click(saveButton);
//        waitUtils.waitForSeconds(2);
//    }

    public void handleAddStudent(StudentTestData data){
        sendKeys(this.studentName, data.getStudentName());
        sendKeys(this.username, data.getUserName());
        sendKeys(this.email, data.getEmail());
        sendKeys(this.studentId, data.getStudentCode());
        handleSelectPosition(data.getPosition());
        handleSelectBranch(data.getDepartment());
        sendKeys(this.password, data.getPassword());
        sendKeys(this.confirmPassword, data.getPassword());
        click(saveButton);
        waitUtils.waitForSeconds(2);
    }
}
