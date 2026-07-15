package vn.oes.pages.Modal.UsersModal;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChangeStatusModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);
    public ChangeStatusModal(WebDriver driver) {
        super(driver);
    }

    By closeButton = By.xpath("//div[@id='changeStudentModal']//div[contains(@class,'modal-header')]//button[contains(@type,'button')]");
    By titleModal = By.xpath("//h4[normalize-space()='Change Status']");
    By deactivateButton = By.xpath("//button[normalize-space()='Deactivate']");
    By activeButton = By.xpath("//button[normalize-space()='Active']");

    public void clickCloseButton() {
        click(closeButton);
    }

    public String getTitleModal() {
        return getText(titleModal);
    }

    public void clickDeactivateButton() {
        waitUtils.waitForVisible(deactivateButton).click();
        waitUtils.waitForSeconds(2);
    }

    public void clickActiveButton() {
        waitUtils.waitForVisible(activeButton).click();
        waitUtils.waitForSeconds(2);
    }

}
