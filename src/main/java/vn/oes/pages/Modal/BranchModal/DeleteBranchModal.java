package vn.oes.pages.Modal.BranchModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class DeleteBranchModal extends BasePage {
    public DeleteBranchModal(WebDriver driver) {
        super(driver);
    }
    WaitUtils waitUtils = new WaitUtils(driver);

    By titleDeleteBranch = By.xpath("//*[@id=\"confirm-delete\"]/div/div/div[1]/h4");
    By cancelButton = By.xpath("//*[@id=\"confirm-delete\"]/div/div/div[2]/div/div/button");
    By deleteBUtton = By.xpath("//a[@id='delete_link']");

    public String getTitleDeleteBranch() {
        return waitUtils.waitForVisible(titleDeleteBranch).getText();
    }
    public void clickCancelButton() {
        waitUtils.waitForClickable(cancelButton).click();
        waitUtils.waitForSeconds(1);
    }

    public void clickDeleteButton() {
        waitUtils.waitForClickable(deleteBUtton).click();
        waitUtils.waitForSeconds(1);
    }
}
