package vn.oes.pages.UserManagement;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.pages.Modal.PositionModal.AddPositionModal;
import vn.oes.pages.Modal.PositionModal.DeletePositionModal;
import vn.oes.pages.Modal.PositionModal.EditPositionModal;
import vn.oes.pages.Modal.PositionModal.ImportPositionModal;
import vn.oes.utils.WaitUtils;
import vn.oes.utils.XPathUtils;

public class PositionPage extends BasePage {

    WaitUtils waitUtils;
    AddPositionModal addPositionModal;
    ImportPositionModal importPositionModal;

    public PositionPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }


    By pageTitle = By.cssSelector("div[class='container-fluid'] div h1");
    By addPositionButton = By.id("add_position_btn");
    By importPositionButton = By.id("import_position_button");
    By selectDataTable = By.xpath("//div[@class='nice-select dataTable_select']");
    By searchInput = By.xpath("//div[@id='lms_table_filter']//label");
    By showAllOption = By.cssSelector("div[id='main-content'] li:nth-child(4)");
    By name = By.cssSelector("th[aria-label^='Tên:']");
    By code = By.cssSelector("th[aria-label^='Mã:']");
    By action = By.cssSelector("th[aria-label^='Hành động:']");


    public String getPageTitle() {
        return waitUtils.waitForVisible(pageTitle).getText();
    }

    public String getName(){
        return waitUtils.waitForVisible(name).getText();
    }

    public String getCode(){
        return waitUtils.waitForVisible(code).getText();
    }
    public String getAction(){
        return waitUtils.waitForVisible(action).getText();
    }

    public void searchPosition(String keyword) {
        waitUtils.waitForVisible(searchInput).click();
        waitUtils.waitForVisible(searchInput).findElement(By.tagName("input")).sendKeys(keyword);
    }

    public boolean isPositionExist(String positionName) {
        try {
            By positionCell = By.xpath("//td[normalize-space()='" + positionName + "']");
            waitUtils.waitForVisible(positionCell);
            waitUtils.waitForSeconds(2);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void addPosition(){
        waitUtils.waitForClickable(addPositionButton).click();
    }

    public void addPosition(String positionName, String positionCode) {
        waitUtils.waitForClickable(addPositionButton).click();
        addPositionModal = new AddPositionModal(driver);
        addPositionModal.handleAddPosition(positionName, positionCode);
    }

    public void importPosition(String filePath) {
        importPositionModal = new ImportPositionModal(driver);
        waitUtils.waitForClickable(importPositionButton).click();
        importPositionModal.handleImportFileInput(filePath);
        waitUtils.waitForSeconds(2);
    }

    public void showfullDataTable() {
        waitUtils.waitForClickable(selectDataTable).click();
        waitUtils.waitForClickable(showAllOption).click();
    }

    public void editPostionByName(String positionName, String newPositionName, String newPositionCode) {
        showfullDataTable();
        By buttonInRow = By.xpath(
                "//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(positionName) + "]]" +
                        "//button[contains(@class,'btn-secondary')]"
        );
        waitUtils.waitForVisible(buttonInRow).click();
        EditPositionModal editPositionModal = new EditPositionModal(driver);
        editPositionModal.editPosition(newPositionName, newPositionCode);
    }


    public void deletePositionByName(String positionName) {
        showfullDataTable();
        By buttonInRow = By.xpath(
                "//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(positionName) + "]]" +
                        "//button[contains(@class,'btn-secondary')]"
        );
        waitUtils.waitForVisible(buttonInRow).click();
        DeletePositionModal deletePositionModal = new DeletePositionModal(driver);
        deletePositionModal.handleDeletePosition();
    }

    public void cancelDeletePositionByName(String positionName) {
        showfullDataTable();
        By buttonInRow = By.xpath(
                "//tr[td[normalize-space(.)=" + XPathUtils.toXPathLiteral(positionName) + "]]" +
                        "//button[contains(@class,'btn-secondary')]"
        );
        waitUtils.waitForVisible(buttonInRow).click();
        DeletePositionModal deletePositionModal = new DeletePositionModal(driver);
        By selectDelete = By.cssSelector("div[class='dropdown-menu dropdown-menu-right show'] a[class='dropdown-item ']");
        waitUtils.waitForClickable(selectDelete).click();
        deletePositionModal.clickCancelButton();
    }

}
