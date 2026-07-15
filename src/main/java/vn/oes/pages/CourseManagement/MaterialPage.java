package vn.oes.pages.CourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.HandleFileUploadUtils;
import vn.oes.utils.WaitUtils;

public class MaterialPage extends BasePage {
    WaitUtils waitUtils;

    public MaterialPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    By addMaterial = By.cssSelector("a[data-target='#addFile']");
    private final By visibleModalTitle = By.xpath("//div[contains(@class, 'modal') and (contains(@class, 'show') or contains(@style, 'display: block'))]//*[contains(@class, 'modal-title')]");
    private final String ACTIVE_MODAL_XPATH = "//div[contains(@class, 'modal') and (contains(@class, 'show') or contains(@style, 'display: block'))]";
    private final By inputFileName = By.xpath(ACTIVE_MODAL_XPATH + "//input[@name='fileName']");
    private final By btnSubmitDocument = By.xpath(ACTIVE_MODAL_XPATH + "//button[@type='submit' and contains(normalize-space(), 'Thêm')]");
    private final By btnUpdateDocument = By.xpath(ACTIVE_MODAL_XPATH + "//button[@type='submit' and contains(normalize-space(), 'Cập nhật')]");
    private final By importFileInput = By.xpath(ACTIVE_MODAL_XPATH + "//div[contains(@class, 'filepond--drop-label')]");

    public String getModalTitle() {
        click(addMaterial);
        return waitUtils.waitForVisible(visibleModalTitle).getText();
    }


    public void addMaterial(String fileName, String resourceFileName) {
        String filePath = HandleFileUploadUtils.getTestResourcePath(resourceFileName);
        click(addMaterial);
        waitUtils.waitForVisible(inputFileName).sendKeys(fileName);
        click(importFileInput);
        HandleFileUploadUtils.uploadWithRobot(filePath);
        waitForFilePondUploadComplete();
        waitUtils.waitForClickable(btnSubmitDocument).click();
    }

    public void selectDocumentAction(String documentName, String action){
        By dropdownBtnLocator = By.xpath("//tbody//tr[td[normalize-space()='" + documentName + "']]//button[contains(@class, 'dropdown-toggle')]");
        WebElement dropdownBtn = waitUtils.waitForClickable(dropdownBtnLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dropdownBtn);
        dropdownBtn.click();

        By actionOptionLocator = By.xpath("//tbody//tr[td[normalize-space()='" + documentName + "']]//div[contains(@class, 'dropdown-menu')]//a[contains(normalize-space(), '" + action + "')]");
        WebElement editOption = waitUtils.waitForClickable(actionOptionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editOption);
    }

    public void editMaterial(String documentName, String newFileName, String newResourceFileName) {
        selectDocumentAction(documentName, "Chỉnh sửa");
        String filePath = HandleFileUploadUtils.getTestResourcePath(newResourceFileName);
        waitUtils.waitForVisible(inputFileName).clear();
        waitUtils.waitForVisible(inputFileName).sendKeys(newFileName);
        click(importFileInput);
        HandleFileUploadUtils.uploadWithRobot(filePath);
        waitForFilePondUploadComplete();
        waitUtils.waitForClickable(btnUpdateDocument).click();
    }

    public void deleteMaterial(String documentName) {
        selectDocumentAction(documentName, "Xóa");
        By confirmDelete = By.cssSelector("div[id='deleteQuestionGroupModal12'] button[type='submit']");
        waitUtils.waitForVisible(confirmDelete).click();
    }
}
