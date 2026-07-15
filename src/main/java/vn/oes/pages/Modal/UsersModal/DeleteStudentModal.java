package vn.oes.pages.Modal.UsersModal;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteStudentModal extends BasePage {
    public DeleteStudentModal(WebDriver driver) {
        super(driver);
    }

    By closeButton = By.xpath("//div[@id='deleteStudentModal']//div[@class='modal-header']//button[@type='button']");
    By titleModal = By.xpath("//h4[normalize-space()='Delete Student']");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By deleteButton = By.xpath("//button[normalize-space()='Delete']");

    public void clickCloseButton() {
        click(closeButton);
    }
    public String getTitleModal() {
        return getText(titleModal);
    }
    public void clickCancelButton() {
        click(cancelButton);
    }
    public void clickDeleteButton() {
        click(deleteButton);
    }
}
