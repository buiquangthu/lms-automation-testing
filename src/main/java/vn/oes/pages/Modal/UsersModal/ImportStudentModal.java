package vn.oes.pages.Modal.UsersModal;

import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.HandleFileUploadUtils;
import vn.oes.utils.WaitUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImportStudentModal extends BasePage {

    WaitUtils waitUtils = new WaitUtils(driver);



    public ImportStudentModal(WebDriver driver) {
        super(driver);
    }

    By branchListDownload = By.xpath("//a[normalize-space()='Branch List Download']");
    By positionListDownload = By.xpath("//a[normalize-space()='Position List Download']");
    By SampleFileDownload = By.xpath("//a[normalize-space()='Sample Download']");
    By BrowseFile = By.xpath("//label[@for='document_file_1_import_']");
    By importButton = By.xpath("//div[@id='import_student']//button[@id='save_button_parent']");

    public void clickBranchListDownload() {
        click(branchListDownload);
    }
    public void clickPositionListDownload() {
        click(positionListDownload);
    }
    public void clickSampleFileDownload() {
        click(SampleFileDownload);
    }

    public void uploadFile(String resourceFileName) {
        String filePath = HandleFileUploadUtils.getTestResourcePath(resourceFileName);
        click(BrowseFile);
        waitUtils.waitForSeconds(1);

        HandleFileUploadUtils.uploadWithRobot(filePath);
        waitUtils.waitForClickable(importButton).click();
    }

}
