package vn.oes.pages.Modal.StudentGroupModal;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EditStudentModal extends BasePage {

    WaitUtils waitUtils = new WaitUtils(driver);

    public EditStudentModal(WebDriver driver) {
        super(driver);
    }
    By editNameStudent = By.xpath("//input[@id='editName']");
    By editCodeStudent = By.xpath("//input[@id='editCode']");
    By updateButton = By.xpath("//div[@id='editBranch']//button[@id='save_button_parent']");

    public void editStudentGroup(String studentGroupName, String studentGroupCode) {
        sendKeys(editNameStudent, studentGroupName);
        sendKeys(editCodeStudent, studentGroupCode);
        waitUtils.waitForClickable(updateButton).click();
        waitUtils.waitForSeconds(1);
    }

}
