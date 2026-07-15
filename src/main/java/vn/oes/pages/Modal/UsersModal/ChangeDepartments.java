package vn.oes.pages.Modal.UsersModal;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ChangeDepartments extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);

    public ChangeDepartments(WebDriver driver) {
        super(driver);
    }
    By titleModal = By.xpath("//h4[normalize-space()='Change departments']");
    By closeButton = By.xpath("//div[@id='moveStudentModal']//button[contains(@type,'button')]");
    By selectBranch = By.xpath("//span[@id='select2-org-container']");
    By updateButton = By.xpath("//div[@role='dialog']//div//div//div//form//div//div//button[@type='submit'][normalize-space()='Update Student']");

    public String getTitleModal() {
        return getText(titleModal);
    }
    public void clickCloseButton() {
        click(closeButton);
    }
    public void selectBreanch(String branchName) {
        click(selectBranch);
        By branchOption = By.xpath("//li[normalize-space()='" + branchName + "']");
        waitUtils.waitForVisible(branchOption).click();
        click(updateButton);
    }
}
