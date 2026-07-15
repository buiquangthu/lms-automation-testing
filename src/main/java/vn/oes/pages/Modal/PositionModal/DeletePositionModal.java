package vn.oes.pages.Modal.PositionModal;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class DeletePositionModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);
    public DeletePositionModal(WebDriver driver) {
        super(driver);
    }
    By selectDelete = By.cssSelector("div[class='dropdown-menu dropdown-menu-right show'] a[class='dropdown-item ']");
    By deleteButton = By.xpath("//a[@id='delete_link']");
    By cancelButton = By.cssSelector("div[id='confirm-delete'] div[class='modal-body'] button[type='button']");

    public void clickDeleteButton() {
        waitUtils.waitForClickable(deleteButton).click();
    }

    public void clickCancelButton() {
        waitUtils.waitForClickable(cancelButton).click();
    }

    public void handleDeletePosition() {
        waitUtils.waitForClickable(selectDelete).click();
        clickDeleteButton();
        waitUtils.waitForSeconds(1);
    }
}
