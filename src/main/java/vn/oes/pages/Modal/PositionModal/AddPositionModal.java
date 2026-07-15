package vn.oes.pages.Modal.PositionModal;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AddPositionModal extends BasePage {
    WaitUtils waitUtils;

    public AddPositionModal(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    By modalContainer = By.id("add_position");
    By titlePosition = By.cssSelector("div[id='add_position'] h4[class='modal-title']");
    By addPositionName = By.id("addName");
    By addPositionCode = By.id("addCode");
    By savePositionButton = By.cssSelector("#add_position #save_button_parent");


    public String getTitlePosition() {
        return waitUtils.waitForVisible(titlePosition).getText();
    }

    public void addPositionName(String positionName) {
        sendKeys(addPositionName, positionName);
    }
    public void addPositionCode(String positionCode) {
        sendKeys(addPositionCode, positionCode);
    }


    public void handleAddPosition(String positionName, String positionCode) {
        addPositionName(positionName);
        addPositionCode(positionCode);
        waitUtils.waitForClickable(savePositionButton).click();
        waitUtils.waitForSeconds(2);
    }

    public boolean isModalDisplayed() {
        try {
            return waitUtils.waitForVisible(modalContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNameFieldRequired() {
        return isFieldRequired(addPositionName);
    }

    public boolean isCodeFieldRequired() {
        return isFieldRequired(addPositionCode);
    }

    public String getNameValidationMessage() {
        return getValidationMessage(addPositionName);
    }

    public String getCodeValidationMessage() {
        return getValidationMessage(addPositionCode);
    }

    public boolean isNameFieldValid() {
        return isFieldValid(addPositionName);
    }

    public boolean isCodeFieldValid() {
        return isFieldValid(addPositionCode);
    }

}

