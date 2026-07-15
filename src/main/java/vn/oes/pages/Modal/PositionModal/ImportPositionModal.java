package vn.oes.pages.Modal.PositionModal;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.HandleFileUploadUtils;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ImportPositionModal extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);
    public ImportPositionModal(WebDriver driver) {
        super(driver);
    }

    By downloadSampleFileButton = By.cssSelector("a[class='primary-btn small fix-gr-bg']");
    By importFileInput = By.xpath("//div[@class='primary_file_uploader']//button[@type='button']");
    By importFileButton = By.xpath("//div[@id='import_position']//button[@id='save_button_parent']");

    public void clickDownloadSampleFileButton() {
        waitUtils.waitForClickable(downloadSampleFileButton).click();
        waitUtils.waitForSeconds(1);
    }

    public void handleImportFileInput(String resourceFileName){
        String filePath = HandleFileUploadUtils.getTestResourcePath(resourceFileName);
        click(importFileInput);
        waitUtils.waitForSeconds(1);
        HandleFileUploadUtils.uploadWithRobot(filePath);
        waitUtils.waitForClickable(importFileButton).click();
    }
}
