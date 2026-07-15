package vn.oes.pages.CourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class CourseLevelPage extends BasePage {
    WaitUtils waitUtils;

    public CourseLevelPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    By title = By.xpath("//h1[contains(text(),'Cấp độ khoá học')]");
    private final By tabEnglish = By.xpath("//a[@href='#elementen']");
    private final By tabVietnamese = By.xpath("//a[@href='#elementvi']");

    private final By inputTitleEn = By.name("title[en]");
    private final By inputTitleVi = By.name("title[vi]");

    private final By btnSave = By.id("save_button_parent");

    private final By inputSearch = By.cssSelector("#lms_table_filter input");
    private final By btnConfirmDelete = By.id("delete_link");

    public String getTitle() {
        return waitUtils.waitForVisible(title).getText();
    }

    public void switchToVietnameseTab() {
        waitUtils.waitForClickable(tabVietnamese).click();
    }

    public void switchToEnglishTab() {
        waitUtils.waitForClickable(tabEnglish).click();
    }

    public void handleAddCourseLevel(String titleEn, String titleVi) {
        if (titleVi != null && !titleVi.isEmpty()) {
            switchToVietnameseTab();
            sendKeys(inputTitleVi, titleVi);
        }
        if (titleEn != null && !titleEn.isEmpty()) {
            switchToEnglishTab();
            sendKeys(inputTitleEn, titleEn);
        }
        waitUtils.waitForClickable(btnSave).click();
    }


    // Handle search
    public void seachCourseLevel(String keyword) {
        if (keyword != null) {
            sendKeys(inputSearch, keyword);
            waitUtils.waitForSeconds(1);
        }
    }

    public boolean isCourseLevelExist(String courseLevelTitle) {
        By levelRow = By.xpath("//table[@id='lms_table']//tr[td[normalize-space(.)='"
                + courseLevelTitle + "']]");
        if (!driver.findElements(levelRow).isEmpty()) {
            return driver.findElement(levelRow).isDisplayed();
        }
        return false;
    }
    public void openActionMenu(String levelTitle) {
        By btnAction = By.xpath("//table[@id='lms_table']//tr[td[normalize-space(.)='"
                + levelTitle + "']]//button[contains(text(), 'Lựa chọn')]");

        WebElement actionBtnElement = driver.findElement(btnAction);
        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", actionBtnElement);
        waitUtils.waitForSeconds(1);

        waitUtils.waitForClickable(btnAction).click();
    }

    public void clickEditCourseLevel(String levelTitle) {
        openActionMenu(levelTitle);
        By btnEdit = By.xpath("//table[@id='lms_table']//tr[td[normalize-space(.)='" + levelTitle + "']]//a[contains(text(), 'Chỉnh sửa')]");
        waitUtils.waitForClickable(btnEdit).click();
    }

    public void clickDeleteLevel(String levelTitle) {
        openActionMenu(levelTitle);
        By btnDelete = By.xpath("//table[@id='lms_table']//tr[td[normalize-space(.)='" + levelTitle + "']]//a[contains(text(), 'Xóa')]");
        waitUtils.waitForClickable(btnDelete).click();

        waitUtils.waitForClickable(btnConfirmDelete).click();
        waitUtils.waitForSeconds(1);
    }


}
