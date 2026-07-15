package vn.oes.pages.Modal.UsersModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class AddAdminModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);

    public AddAdminModal(WebDriver driver) {
        super(driver);
    }

    By titleAddAdmin = By.xpath("//h4[normalize-space()='Thêm Admin']");
    By addNameAdmin = By.xpath("//input[@id='addName']");
    By addAboutAdmin = By.xpath("//div[@id='add_instructor']//div[@role='textbox']");
    By addYobAdmin = By.xpath("//input[@id='startDate']");
    By addphoneNumberAdmin = By.xpath("//input[@id='addPhone']");
    By addEmailAdmin = By.xpath("//input[@id='addEmail']");
    By buttonAddImage = By.xpath("//label[@for='document_file']");
    By addPasswordAdmin = By.xpath("//input[@id='addPassword']");
    By addConfirmPasswordAdmin = By.xpath("//input[@id='addCpassword']");
    By submitButton = By.xpath("//div[@id='add_instructor']//button[@id='save_button_parent']");

    public String getTitleAdmin(){
        return waitUtils.waitForVisible(titleAddAdmin).getText();
    }

    public void addNameAdmin(String name) {
        sendKeys(addNameAdmin, name);
        waitUtils.waitForSeconds(1);
    }
    public void addAboutAdmin(String about) {
        sendKeys(addAboutAdmin, about);
        waitUtils.waitForSeconds(1);
    }
    public void addYobAdmin(String yob) {
        sendKeys(addYobAdmin, yob);
        waitUtils.waitForSeconds(1);
    }
    public void addPhoneNumberAdmin(String phoneNumber) {
        sendKeys(addphoneNumberAdmin, phoneNumber);
        waitUtils.waitForSeconds(1);
    }
    public void addEmailAdmin(String email) {
        sendKeys(addEmailAdmin, email);
        waitUtils.waitForSeconds(1);
    }

    public void uploadImage(String filePath) {
        click(buttonAddImage);
        waitUtils.waitForSeconds(1);

        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        try {
            Robot robot = new Robot();
            robot.delay(1000); // Wait a bit to ensure the input is focused


            robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
            robot.keyPress(java.awt.event.KeyEvent.VK_V);

            robot.keyRelease(java.awt.event.KeyEvent.VK_V);
            robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void addPasswordAdmin(String password) {
        sendKeys(addPasswordAdmin, password);
        waitUtils.waitForSeconds(1);
    }
    public void addConfirmPasswordAdmin(String confirmPassword) {
        sendKeys(addConfirmPasswordAdmin, confirmPassword);
        waitUtils.waitForSeconds(1);
    }

    public void clickSubmitButton() {
        click(submitButton);
        waitUtils.waitForSeconds(2);
    }
}
