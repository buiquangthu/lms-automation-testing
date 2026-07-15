package vn.oes.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import vn.oes.utils.WaitUtils;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitUtils = new WaitUtils(driver);
    }

    // click
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // nhap text vao input
    protected void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
    // lay gia tri cua input
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // kiem tra co hien thi hay khong
    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }
    // doi den khi hien thi
    protected void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // doi den khi co the click
    protected void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Cuộn đến element
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void refreshPage() {
        driver.navigate().refresh();
    }

    protected String getAttribute(By locator, String attributeName) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attributeName);
    }

    // Kiểm tra field có bắt buộc (required) không
    protected boolean isFieldRequired(By locator) {
        return Boolean.TRUE.equals(
                ((JavascriptExecutor) driver).executeScript("return arguments[0].required;",
                        driver.findElement(locator)));
    }

    // Lấy thông báo lỗi validation từ trình duyệt
    protected String getValidationMessage(By locator) {
        return (String) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].validationMessage;",
                driver.findElement(locator));
    }

    // Kiểm tra field có hợp lệ (valid) không
    protected boolean isFieldValid(By locator) {
        return Boolean.TRUE.equals(
                ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
                        driver.findElement(locator)));
    }

    /**
     * Chờ tiến trình tải file của thư viện FilePond hoàn tất.
     * Sử dụng ACTIVE_FORM_XPATH để đảm bảo chỉ check trạng thái file của form ĐANG MỞ.
     */
    public void waitForFilePondUploadComplete() {
        System.out.println("Đang chờ FilePond xử lý tải lên...");


        By uploadCompleteState = By.xpath("//li[@data-filepond-item-state='processing-complete']");
        By uploadCompleteText = By.xpath("//span[normalize-space()='Tải lên hoàn tất']");


        waitUtils.waitForVisible(uploadCompleteState);
        waitUtils.waitForVisible(uploadCompleteText);
//
//        System.out.println("Tải file hoàn tất, sẵn sàng cho thao tác tiếp theo!");
    }

}
