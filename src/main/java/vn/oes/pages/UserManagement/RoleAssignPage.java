package vn.oes.pages.UserManagement;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;
import vn.oes.utils.XPathUtils;

@Slf4j
public class RoleAssignPage extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);

    public RoleAssignPage(WebDriver driver) {
        super(driver);
    }
    By selectRoleDropdown = By.xpath("//div[@id='ms-list-1']//button[@type='button']");
    By selectRoleOptionInstructor = By.cssSelector("label[for='ms-opt-1']");
    By selectRoleOptionStudent = By.cssSelector("label[for='ms-opt-2']");
    By buttonFilter = By.xpath("//button[normalize-space()='Filter']");
    By dropdownVisibility = By.xpath("//div[@class='nice-select dataTable_select']");
    By optionShowAll = By.xpath("//li[normalize-space()='All']");


    public RoleAssignPage handleFilterByRole(String roleName) {
        waitUtils.waitForClickable(selectRoleDropdown).click();
        By roleOption;
        switch (roleName.toLowerCase()) {
            case "student":
                roleOption = selectRoleOptionStudent;
                break;
            case "instructor":
                roleOption = selectRoleOptionInstructor;
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + roleName);
        }
        waitUtils.waitForClickable(roleOption).click();
        waitUtils.waitForVisible(buttonFilter).click();
        return this;
    }
    public RoleAssignPage showAllTableData(){
        waitUtils.waitForClickable(dropdownVisibility).click();
        waitUtils.waitForClickable(optionShowAll).click();
        waitUtils.waitForSeconds(1);
        return this;
    }

    public RoleAssignPage selectInstructorByName(String instructorName){
        showAllTableData();
        By instructorSpan = By.xpath("//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(instructorName) + "]]//input[@type='checkbox' and @data-role='2']/following-sibling::span[contains(@class, 'checkmark')]");
        By instructorCheckbox = By.xpath("//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(instructorName) + "]]//input[@type='checkbox' and @data-role='2']");

        waitUtils.waitForClickable(instructorSpan);
        if(!driver.findElement(instructorCheckbox).isSelected()){
//            System.out.println("Gia tri"+ driver.findElement(instructorCheckbox).isSelected());
            click(instructorSpan);
            log.info("Selected Instructor checkbox for user: {}", instructorName);
        }else {
            System.out.println("Đã được chọn trước đó");
        }
        return this;
    }

    public RoleAssignPage unSelectInstructorByName(String instructorName){
        showAllTableData();
        By instructorSpan = By.xpath("//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(instructorName) + "]]//input[@type='checkbox' and @data-role='2']/following-sibling::span[contains(@class, 'checkmark')]");
        By instructorCheckbox = By.xpath("//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(instructorName) + "]]//input[@type='checkbox' and @data-role='2']");

        waitUtils.waitForClickable(instructorSpan);
        if(driver.findElement(instructorCheckbox).isSelected()){
            click(instructorSpan);
            log.info("Unselected Instructor checkbox for user: {}", instructorName);
        }
        return this;
    }

    public RoleAssignPage selectStudentByName(String studentName){
        showAllTableData();
        By studentSpan = By.xpath("//tr[td[normalize-space(.)=" + studentName + "]]//input[@type='checkbox' and @data-role='3']/following-sibling::span[contains(@class, 'checkmark')]");
        By studentCheckbox = By.xpath("//tr[td[normalize-space(.)=" + studentName + "]]//input[@type='checkbox' and @data-role='3']");
        waitUtils.waitForClickable(studentSpan);
        if(!driver.findElement(studentCheckbox).isSelected()){
            click(studentSpan);
            log.info("Selected Student checkbox for user: {}", studentName);
        }
        return this;
    }

    public RoleAssignPage unSelectStudentByName(String studentName){
        showAllTableData();
        By studentSpan = By.xpath("//tr[td[normalize-space(.)=" + studentName + "]]//input[@type='checkbox' and @data-role='3']/following-sibling::span[contains(@class, 'checkmark')]");
        By studentCheckbox = By.xpath("//tr[td[normalize-space(.)=" + studentName + "]]//input[@type='checkbox' and @data-role='3']");
        waitUtils.waitForClickable(studentSpan);
        if(driver.findElement(studentCheckbox).isSelected()){
            click(studentSpan);
            log.info("Unselected Student checkbox for user: {}", studentName);
        }
        return this;
    }

}
