package vn.oes.pages.Modal.GroupPolicyModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class AddGroupPolicyModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);

    public AddGroupPolicyModal(WebDriver driver) {
        super(driver);
    }

    By title = By.xpath("//h4[normalize-space()='Add New Group Policy']");
    By nameInput = By.xpath("//input[@class='primary_input_field addName']");
    By addGroupPolicyButton = By.xpath("//div[@id='add_policy']//button[@id='save_button_parent']");

    public AddGroupPolicyModal handleAddGroupPolicy(String groupPolicyName) {
        waitUtils.waitForVisible(title).getText();
        waitUtils.waitForVisible(nameInput).sendKeys(groupPolicyName);
        waitUtils.waitForClickable(addGroupPolicyButton).click();
        return this;
    }
}
