package vn.oes.pages.Modal.AdminModal;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UpdateAdminModal extends BasePage {

    WaitUtils waitUtils = new WaitUtils(driver);

    public UpdateAdminModal(WebDriver driver) {
        super(driver);
    }
    By nameUpdate = By.xpath("//input[@id='instructorName']");
    By aboutUpdate = By.xpath("//div[@role='dialog']//div//div//div//form//div//div//div//div//div//div[@role='textbox']");
    By dobUpdate = By.xpath("//input[@id='instructorDob']']");
    By phoneUpdate = By.xpath("//input[@id='instructorPhone']");
    By emailUpdate = By.xpath("//input[@id='instructorEmail']");
    By uploadImage = By.xpath("//label[@for='document_file_edit']");
    By positionUpdate = By.xpath("//span[normalize-space()='Select Position']");
    By orgChartUpdate = By.xpath("//span[normalize-space()='Select Org Chart']");
    By updatePassword = By.xpath("//body[1]/div[3]/div[1]/section[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[6]/div[1]/div[1]/div[1]/input[1]");
    By confirmPasswordUpdate = By.xpath("//body[1]/div[3]/div[1]/section[2]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[6]/div[2]/div[1]/div[1]/input[1]");
    By updateButton = By.xpath("//button[contains(text(),'p nh')]");

    public void addName(String name) {
        sendKeys(nameUpdate, name);
    }
    public void addAbout(String about) {
        sendKeys(aboutUpdate, about);
    }
    public void addDob(String dob) {
        sendKeys(dobUpdate, dob);
    }
    public void addPhone(String phone) {
        sendKeys(phoneUpdate, phone);
    }
    public void addEmail(String email) {
        sendKeys(emailUpdate, email);
    }
    public void uploadImage(String imagePath){
        click(uploadImage);
        waitUtils.waitForSeconds(2);
        StringSelection stringSelection = new StringSelection(imagePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        try {
            Robot robot = new Robot();
            robot.delay(1000); // Chờ một chút để chắc chắn rằng input file đã được focus

            // CTRL + V de paste duong dan file tu clipboard vao input file
            robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyPress(java.awt.event.KeyEvent.VK_V);

            robot.keyRelease(java.awt.event.KeyEvent.VK_V);
            robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);

            robot.delay(1000);
            // Nhan Enter
            robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
            robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        waitUtils.waitForSeconds(2);
    }

    public void handlePosition(String position) {
        waitUtils.waitForVisible(positionUpdate).click();
        By positionOption = By.xpath("//ul[@class='list']//li[normalize-space(text())='" + position + "']");
        waitUtils.waitForVisible(positionOption).click();
    }

    public void handleOrgChart(String orgChart){
        waitUtils.waitForVisible(orgChartUpdate).click();
        By orgChartOption = By.xpath("//li[normalize-space()='" + orgChart + "']");
        waitUtils.waitForClickable(orgChartOption).click();
    }

    public void addUpdatePassword(String password) {
        sendKeys(updatePassword, password);
    }
    public void addConfirmPassword(String confirmPassword) {
        sendKeys(confirmPasswordUpdate, confirmPassword);
    }

    public void clickUpdateButton() {
        waitUtils.waitForClickable(updateButton).click();
    }
}

