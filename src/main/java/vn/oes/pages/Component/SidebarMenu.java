package vn.oes.pages.Component;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vn.oes.config.ConfigReader;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SidebarMenu extends BasePage{

    WaitUtils waitUtils;

    public SidebarMenu(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }
    By ticket = By.cssSelector("a[href=' " + ConfigReader.get("baseUrl") +"/support/ticket ']");

    public void selectMenu(String parentName, String childName) {
        By parentLocator = By.xpath("//a[contains(@class, 'has-arrow') and .//span[normalize-space()='" + parentName + "']]");
        WebElement parentElement = waitUtils.waitForVisible(parentLocator);


        String isExpanded = parentElement.getAttribute("aria-expanded");


        if ("false".equals(isExpanded)) {
            parentElement.click();
                waitUtils.waitForSeconds(1);
        }


        if (childName != null && !childName.isEmpty()) {
            By childLocator = By.xpath("//li[a[.//span[normalize-space()='" + parentName + "']]]//ul//a[normalize-space()='" + childName + "']");


            scrollToElement(childLocator);

            waitUtils.waitForClickable(childLocator).click();
            waitUtils.waitForSeconds(1);
        }
    }

    public void gotoDashboard() {
        // Dashboard không có menu con, chỉ có menu cha
        By dashboardLocator = By.xpath("//a[.//span[normalize-space()='Bảng điều khiển']]");
        waitUtils.waitForClickable(dashboardLocator).click();
    }

    public void gotoUser() {
        selectMenu("Quản trị người dùng", "Học viên");
    }

    public void gotoAdmin() {
        selectMenu("Quản trị người dùng", "Giảng viên");
    }

    public void gotoBranch() {
        selectMenu("Quản trị người dùng", "Sơ đồ tổ chức");
    }

    public void gotoPosition() {
        selectMenu("Quản trị người dùng", "Vị trí chức danh");
    }

    public void gotoStudent() {
        selectMenu("Quản trị người dùng", "Học viên");
    }

    public void gotoRoleAssign() {
        selectMenu("Quản trị người dùng", "Gán quyền");
    }

    public void gotoGroupPolicy() {
        selectMenu("Quản trị người dùng", "Phân quyền theo nhóm");
    }

    public void gotoCourseCategories() {
        selectMenu("Quản trị khóa học", "Phân nhóm khóa học");
    }

    public void gotoCourseLevel() {
        selectMenu("Quản trị khóa học", "Cấp độ khoá học");
    }

    public void gotoCourseList() {
        selectMenu("Quản trị khóa học", "Danh sách khóa học");
    }

    public void gotoTicket(){
        waitUtils.waitForVisible(ticket).click();
    }
}

