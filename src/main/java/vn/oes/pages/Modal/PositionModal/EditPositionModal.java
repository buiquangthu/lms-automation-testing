package vn.oes.pages.Modal.PositionModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class EditPositionModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);
    public EditPositionModal(WebDriver driver) {
        super(driver);
    }
    By selectEdit = By.cssSelector("div[class='dropdown-menu dropdown-menu-right show'] a[class='dropdown-item editBranch']");
    By editName = By.xpath("//input[@id='editName']");
    By editCode = By.xpath("//input[@id='editCode']");
    By updaeButton = By.cssSelector("div[role='dialog'] div div div form div div button[type='submit']");


    public void editPosition(String newPositionName, String newPositionCode) {
        waitUtils.waitForVisible(selectEdit).click();
        sendKeys(editName, newPositionName);
        sendKeys(editCode, newPositionCode);
        waitUtils.waitForClickable(updaeButton).click();
    }
}
