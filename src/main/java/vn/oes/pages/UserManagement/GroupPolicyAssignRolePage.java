package vn.oes.pages.UserManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;
import vn.oes.utils.XPathUtils;

import java.util.List;

public class GroupPolicyAssignRolePage extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);
    public GroupPolicyAssignRolePage(WebDriver driver) {
        super(driver);
    }
    By branchSelectAll = By.xpath("//div[@class='add-visitor']//button[@id='selectBranchButton']");


    public GroupPolicyAssignRolePage handleSelectBranchByName(String branchName) {
        By expandBtn = By.xpath("//tr[.//span[normalize-space()=" + XPathUtils.toXPathLiteral(branchName) + "]]//a[contains(@class, 'branch_toggle') and contains(@class, 'collapsed')]");
        By checkmark = By.xpath("//tr[.//span[normalize-space()='" + branchName + "']]//span[contains(@class, 'checkmark')]");


        List<WebElement> expandButtons = driver.findElements(expandBtn);
        for(WebElement btn : expandButtons){
            if (btn.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                waitUtils.waitForSeconds(1);
            }
        }

        WebElement el = waitUtils.waitForVisible(checkmark);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        return this;
    }

    public GroupPolicyAssignRolePage handleSeleactCategoryByName(String categoryName){
        By expandBtn = By.xpath("//tr[.//a[normalize-space()=" + XPathUtils.toXPathLiteral(categoryName) + "]]//a[contains(@href, 'collapseCategory1')]");
        By checkmark = By.xpath("//tr[.//a[normalize-space()='" + categoryName + "']]//span[contains(@class, 'checkmark mr-2')]");

        List<WebElement> expandButtons = driver.findElements(expandBtn);
        for (WebElement btn : expandButtons) {
            if (btn.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                waitUtils.waitForSeconds(1);
            }
        }
        WebElement el = waitUtils.waitForVisible(checkmark);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);

        return this;
    }

    public GroupPolicyAssignRolePage handleSelectRoleByName(String roleName){
        By expandBtn = By.xpath("//div[@class='single_permission']");
        return this;
    }

}
