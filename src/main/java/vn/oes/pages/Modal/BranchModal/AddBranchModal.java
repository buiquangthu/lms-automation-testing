package vn.oes.pages.Modal.BranchModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.pages.UserManagement.BranchPage;
import vn.oes.utils.WaitUtils;

public class AddBranchModal extends BasePage {

    private WaitUtils waitUtils;
    private BranchPage branchPage;

    public AddBranchModal(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
        this.branchPage = new BranchPage(driver);
    }

    By addGroupName = By.cssSelector("#addGroup");
    By addCodeBranch = By.cssSelector("#addCode");
    By selectParentBranch = By.cssSelector("div[id='add_branch'] span[class='current']");
    By saveBranchButton = By.xpath("//div[@id='add_branch']//button[@id='save_button_parent']");

    public void addGroupName(String groupName) {
        sendKeys(addGroupName, groupName);
    }

    public void addCodeBranch(String codeBranch) {
        sendKeys(addCodeBranch, codeBranch);
    }

    public void selectParentBranch(String parentBranch) {
        if (parentBranch != null && !parentBranch.isEmpty()) {
            By parentBranchOption = By.xpath("//div[@id='add_branch']//ul[@class='list']//li[normalize-space()='" + parentBranch + "']");
            click(selectParentBranch);
            waitUtils.waitForVisible(parentBranchOption).click();

        }
    }

    public void handleAddBranch(String groupName, String codeBranch, String parentBranch){
        addGroupName(groupName);
        addCodeBranch(codeBranch);
        selectParentBranch(parentBranch);
        click(saveBranchButton);
        waitUtils.waitForSeconds(2);
    }

    public String getNameValidationMessage() {
        return getValidationMessage(addGroupName);
    }

    public String getCodeValidationMessage() {
        return getValidationMessage(addCodeBranch);
    }

    public boolean isGroupNameFieldRequired() {
        return isFieldRequired(addGroupName);
    }

    public boolean isCodeBranchFieldRequired() {
        return isFieldRequired(addCodeBranch);
    }

    public boolean isNameFieldValid() {
        return isFieldValid(addGroupName);
    }

        public boolean isCodeFieldValid() {
            return isFieldValid(addCodeBranch);
        }


}
