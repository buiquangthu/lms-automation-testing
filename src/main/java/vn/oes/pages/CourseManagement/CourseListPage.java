package vn.oes.pages.CourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class CourseListPage extends BasePage {

    WaitUtils waitUtils;

    public CourseListPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    private final By title = By.xpath("//h1[contains(text(),'Danh sách khóa học')]");
    private final By inputSearchQuick = By.cssSelector("#lms_table_filter input");
    private final By btnFilter = By.xpath("//button[normalize-space()='Lọc']");
    private final By btnAddCourse = By.xpath("//a[contains(@href, '/admin/course/new/course')]");


    public void selectFilterOption(String selectName, String optionText) {
        if (optionText != null && !optionText.isEmpty()) {
            By dropdown = By.xpath("//select[@name='" + selectName + "']/following-sibling::div[contains(@class, 'nice-select')]");
            waitUtils.waitForClickable(dropdown).click();

            By option = By.xpath("//select[@name='" + selectName + "']/following-sibling::div[contains(@class, 'nice-select')]//ul//li[normalize-space(.)='" + optionText + "']");
            waitUtils.waitForClickable(option).click();
        }
    }

    public void filterCourses(String category, String type, String instructor, String status, String requiredType, String deliveryMode) {
        selectFilterOption("category", category);
        selectFilterOption("type", type);
        selectFilterOption("instructor", instructor);
        selectFilterOption("search_status", status);
        selectFilterOption("search_required_type", requiredType);
        selectFilterOption("search_delivery_mode", deliveryMode);

        waitUtils.waitForClickable(btnFilter).click();
        waitUtils.waitForSeconds(1);
    }

    public void searchQuickly(String keyword) {
        if (keyword != null) {
            sendKeys(inputSearchQuick, keyword);
            waitUtils.waitForSeconds(1); // Chờ bảng reload
        }
    }

    public boolean isCourseExist(String courseTitle) {
        By courseRow = By.xpath("//table[@id='lms_table']//tr[td[normalize-space(.)='" + courseTitle + "']]");
        if (!driver.findElements(courseRow).isEmpty()) {
            return driver.findElement(courseRow).isDisplayed();
        }
        return false;
    }

    public AddCoursePage clickAddCourseButton() {
        waitUtils.waitForClickable(btnAddCourse).click();

        return new AddCoursePage(driver);
    }

    public void openActionMenu(String courseTitle) {
        By courseRow = By.xpath("//table[@id='lms_table']//tr[not(contains(@class, 'child')) and td[normalize-space(.)='" + courseTitle + "']]");

        WebElement rowElement = waitUtils.waitForVisible(courseRow);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", rowElement);
        waitUtils.waitForSeconds(1);

        By btnAction = By.xpath("//table[@id='lms_table']//tr[not(contains(@class, 'child')) and td[normalize-space(.)='" + courseTitle + "']]//button[contains(normalize-space(), 'Hành động')]");


        if (driver.findElements(btnAction).isEmpty() || !driver.findElement(btnAction).isDisplayed()) {
            System.out.println("Màn hình nhỏ: Bắt đầu tự động Expand dòng...");

            By expandBtn = By.xpath("//table[@id='lms_table']//tr[not(contains(@class, 'child')) and td[normalize-space(.)='" + courseTitle + "']]/td[1]");
            waitUtils.waitForClickable(expandBtn).click();
            waitUtils.waitForSeconds(1);

            btnAction = By.xpath("//table[@id='lms_table']//tr[not(contains(@class, 'child')) and td[normalize-space(.)='" + courseTitle + "']]/following-sibling::tr[contains(@class, 'child')][1]//button[contains(normalize-space(), 'Hành động')]");
        }

        WebElement actionBtnElement = waitUtils.waitForVisible(btnAction);
        js.executeScript("arguments[0].click();", actionBtnElement);
    }

    public void selectActionItem(String courseTitle, String actionName) {

        openActionMenu(courseTitle);
        waitUtils.waitForSeconds(1);

        By actionItem = By.xpath("//div[contains(@class, 'dropdown-menu') and contains(@class, 'show')]//a[contains(normalize-space(), '" + actionName + "')]");
        WebElement itemElement = waitUtils.waitForClickable(actionItem);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", itemElement);
    }

    public void clickAddLesson(String courseTitle) {
        selectActionItem(courseTitle, "Thêm bài học");
    }

    public void clickEditCourse(String courseTitle) {
        selectActionItem(courseTitle, "Chỉnh sửa");
    }

    public void clickCourseStudents(String courseTitle) {
        selectActionItem(courseTitle, "Học viên");
    }

    public void clickCloneCourse(String courseTitle) {
        selectActionItem(courseTitle, "Nhân bản");
    }

    public void clickDeleteCourse(String courseTitle) {
        selectActionItem(courseTitle, "Xóa");

        By btnConfirmDelete = By.id("delete_link");
        waitUtils.waitForClickable(btnConfirmDelete).click();
        waitUtils.waitForSeconds(1);
    }

    public void selectTab(String tabName){
        By tabLocator = By.xpath("//ul[@role='tablist']//a[contains(normalize-space(), '" + tabName + "')]");
        waitUtils.waitForVisible(tabLocator).click();
    }


}
