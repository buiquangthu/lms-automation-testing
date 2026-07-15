package vn.oes.pages.Modal.GroupPolicyModal;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;
import vn.oes.utils.XPathUtils;

public class AddAdminModal extends BasePage {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WaitUtils waitUtils = new WaitUtils(driver);

    public AddAdminModal(WebDriver driver) {
        super(driver);
    }
    By title = By.xpath("//h4[normalize-space()='Add Instructor']");
    By selectInstructorDropdown = By.xpath("//span[@role='combobox']");
    By addInstructorButton = By.xpath("//div[@id='addInstructor']//button[@id='save_button_parent']");

    public AddAdminModal handleAddAdmin(String instructorName) {
        waitUtils.waitForVisible(title).getText();
        waitUtils.waitForClickable(selectInstructorDropdown).click();
//        By instructorOption = By.xpath("//li[normalize-space()=" + XPathUtils.toXPathLiteral(instructorName) + "]");
        By instructorOption = By.xpath(XPathUtils.xpath("li", instructorName).caseInsensitive().build());
        waitUtils.waitForVisible(instructorOption).click();
        js.executeScript("arguments[0].click();", driver.findElement(addInstructorButton));
        waitUtils.waitForSeconds(3);
        return this;
    }

}
