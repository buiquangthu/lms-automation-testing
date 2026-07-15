package vn.oes.pages.Modal.BranchModal;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class UpdateBranchModal extends BasePage {
    public UpdateBranchModal(WebDriver driver) {
        super(driver);
    }
    WaitUtils waitUtils = new WaitUtils(driver);
    By titleUpdateBranch = By.xpath("//*[@id=\"editBranch\"]/div/div/div[1]/h4");
    By editGroupName = By.xpath("//input[@id='editGroup']");
    By editCodeBranch = By.xpath("//input[@id='editCode']");
    By selectParentBranch = By.xpath("//body/div/div/section/div/div/div[@role='dialog']/div/div/div/form/div/div/div/div[1]");

    public String getTitleUpdateBranch() {
        return waitUtils.waitForVisible(titleUpdateBranch).getText();
    }

    public void editGroupName(String groupName) {
        sendKeys(editGroupName, groupName);
    }
    public void editCodeBranch(String codeBranch) {
        sendKeys(editCodeBranch, codeBranch);
    }

    public void selectParentBranch(String parentBranch) {
        By parentBranchOption = By.xpath("//div[@class='nice-select primary_select open']//ul[@class='list']//li[normalize-space()='" + parentBranch + "']");
        click(selectParentBranch);
        waitUtils.waitForVisible(parentBranchOption).click();
        waitUtils.waitForSeconds(1);
    }
}
