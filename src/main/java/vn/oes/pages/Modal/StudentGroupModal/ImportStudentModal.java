package vn.oes.pages.Modal.StudentGroupModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.HandleFileUploadUtils;
import vn.oes.utils.WaitUtils;

public class ImportStudentModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);
    public ImportStudentModal(WebDriver driver) {
        super(driver);
    }

    By title = By.xpath("//h4[normalize-space()='Import Position']");
    By importFileInput = By.cssSelector("label[for='document_file_1_import_']");
    By importFileButton = By.xpath("//div[@id='import_position']//button[@id='save_button_parent']");
    By downloadSampleFileButton = By.cssSelector("a[class='primary-btn small fix-gr-bg']");


    public void handleImportFileInput(String resourceFileName){
        String filePath = HandleFileUploadUtils.getTestResourcePath(resourceFileName);
        waitUtils.waitForClickable(importFileInput).click();
        waitUtils.waitForSeconds(1);
        HandleFileUploadUtils.uploadWithRobot(filePath);
        waitUtils.waitForClickable(importFileButton).click();
        waitUtils.waitForSeconds(1);
    }
}
