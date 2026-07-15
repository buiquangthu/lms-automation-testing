package vn.oes.pages.CourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class CourseCategoriesPage extends BasePage {

    WaitUtils waitUtils;

    public CourseCategoriesPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    By title = By.xpath("//h1[contains(text(),'Phân nhóm khóa học')]");

    By tabEnglish = By.xpath("//a[@href='#elementen']");
    By tabVietnamese = By.xpath("//a[@href='#elementvi']");

    By inputNameEn = By.name("name[en]");
    By inputDescriptionEn = By.name("description[en]");

    By inputNameVi = By.name("name[vi]");
    By inputDescriptionVi = By.name("description[vi]");

    By uploadIcon = By.id("document_file_1");
    By uploadThumbnail = By.id("document_file_2");

    By btnSave = By.id("save_button_parent");

    By btnConfirmDelete = By.id("delete_link");

    public String getTitle() {
        return waitUtils.waitForVisible(title).getText();
    }

    public void switchToVietnameseTab() {
        waitUtils.waitForClickable(tabVietnamese).click();
    }

    public void switchToEnglishTab() {
        waitUtils.waitForClickable(tabEnglish).click();
    }

    public void enterVietnameseCategoryData(String name, String description) {
        switchToVietnameseTab();
        sendKeys(inputNameVi, name);
        sendKeys(inputDescriptionVi, description);
    }

    public void enterEnglishCategoryData(String name, String description) {
        switchToEnglishTab();
        sendKeys(inputNameEn, name);
        sendKeys(inputDescriptionEn, description);
    }

    public void selectParentCategory(String parentName) {
        if (parentName != null && !parentName.isEmpty()) {

            By parentDropdown = By.xpath("//select[@id='parent']/following-sibling::div[contains(@class, 'nice-select')]");
            waitUtils.waitForClickable(parentDropdown).click();

            By parentOption = By.xpath("//select[@id='parent']/following-sibling::div[contains(@class, 'nice-select')]//ul//li[normalize-space(.)='" + parentName + "']");
            waitUtils.waitForClickable(parentOption).click();
        }
    }

    public void selectPositionOrder(String positionNumber) {
        if (positionNumber != null && !positionNumber.isEmpty()) {
            By positionDropdown = By.xpath("//select[@id='position_order']/following-sibling::div[contains(@class, 'nice-select')]");
            waitUtils.waitForClickable(positionDropdown).click();

            By positionOption = By.xpath("//select[@id='position_order']/following-sibling::div[contains(@class, 'nice-select')]//ul//li[normalize-space(.)='" + positionNumber + "']");
            waitUtils.waitForClickable(positionOption).click();
        }
    }

    public void selectStatus(String statusText) {
        if (statusText != null && !statusText.isEmpty()) {
            By statusDropdown = By.xpath("//select[@id='status']/following-sibling::div[contains(@class, 'nice-select')]");
            waitUtils.waitForClickable(statusDropdown).click();

            By statusOption = By.xpath("//select[@id='status']/following-sibling::div[contains(@class, 'nice-select')]//ul//li[normalize-space(.)='" + statusText + "']");
            waitUtils.waitForClickable(statusOption).click();
        }
    }

    public void uploadIconImage(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            // Selenium gửi đường dẫn tuyệt đối thẳng vào thẻ input file
            driver.findElement(uploadIcon).sendKeys(filePath);
        }
    }

    public void uploadThumbnailImage(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            driver.findElement(uploadThumbnail).sendKeys(filePath);
        }
    }

    public void clickSaveCategory() {
        waitUtils.waitForClickable(btnSave).click();
    }

    public void handleAddCourseCategory(String nameVi, String descVi,
                                        String nameEn, String descEn,
                                        String parentName, String positionOrder, String status,
                                        String iconPath, String thumbnailPath) {

        if (nameVi != null && !nameVi.isEmpty()) {
            enterVietnameseCategoryData(nameVi, descVi);
        }
        if (nameEn != null && !nameEn.isEmpty()) {
            enterEnglishCategoryData(nameEn, descEn);
        }

        selectParentCategory(parentName);
        selectPositionOrder(positionOrder);
        selectStatus(status);
        uploadIconImage(iconPath);
        uploadThumbnailImage(thumbnailPath);
        clickSaveCategory();
    }

    public boolean isCourseCategoryExist(String categoryName) {
        By categoryRow = By.xpath("//tr[contains(@class, 'category-row') and td[normalize-space(.)='" + categoryName + "']]");

        // Dùng findElements để không bị văng lỗi NoSuchElementException nếu không tìm thấy
        if (!driver.findElements(categoryRow).isEmpty()) {
            return driver.findElement(categoryRow).isDisplayed();
        }
        return false;
    }

    public void openActionMenu(String categoryName) {
        By btnAction = By.xpath("//tr[contains(@class, 'category-row') and td[normalize-space(.)='" + categoryName + "']]//button[contains(text(), 'Lựa chọn')]");


        // 1. Tìm element trên DOM
        WebElement actionBtnElement = driver.findElement(btnAction);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", actionBtnElement);


        waitUtils.waitForSeconds(1);

        waitUtils.waitForClickable(btnAction).click();
    }


    public void clickEditCategory(String categoryName) {
        openActionMenu(categoryName);


        By btnEdit = By.xpath("//tr[contains(@class, 'category-row') and td[normalize-space(.)='" + categoryName + "']]//a[contains(text(), 'Chỉnh sửa')]");
        waitUtils.waitForClickable(btnEdit).click();
    }

    /**
     * Bấm nút "Xóa" theo Tên danh mục
     */
    public void clickDeleteCategory(String categoryName) {
        openActionMenu(categoryName);

        By btnDelete = By.xpath("//tr[contains(@class, 'category-row') and td[normalize-space(.)='" + categoryName + "']]//a[contains(text(), 'Xóa')]");
        waitUtils.waitForClickable(btnDelete).click();


        waitUtils.waitForClickable(btnConfirmDelete).click();
        waitUtils.waitForSeconds(2);
    }
}
