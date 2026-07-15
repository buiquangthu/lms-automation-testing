package vn.oes.pages.Modal.BranchModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.HandleFileUploadUtils;
import vn.oes.utils.WaitUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class ImportBranchModal extends BasePage {
    WaitUtils waitUtils;
    public ImportBranchModal(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }
    By titleImportBranch = By.cssSelector("div[id='import_org_chart'] h4[class='modal-title']");
    By importFileInput = By.cssSelector("label[for='document_file_1_import_']");
    By importButton = By.xpath("//div[@id='import_org_chart']//button[@id='save_button_parent']");

    public String getTitleImportBranch() {
        return waitUtils.waitForVisible(titleImportBranch).getText();
    }

    public void handleImportBranch(String resourceFileName) {
        String filePath = HandleFileUploadUtils.getTestResourcePath(resourceFileName);
        click(importFileInput);
        waitUtils.waitForSeconds(1);
        HandleFileUploadUtils.uploadWithRobot(filePath);
        waitUtils.waitForClickable(importButton).click();
    }

}
