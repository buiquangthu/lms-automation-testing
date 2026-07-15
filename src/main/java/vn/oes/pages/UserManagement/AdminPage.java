package vn.oes.pages.UserManagement;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.*;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AdminPage extends BasePage {
    WaitUtils waitUtils = new WaitUtils(driver);

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    By addAdminButton = By.xpath("//a[@id='add_instructor_btn']");
    By searchInput = By.xpath("//div[@id='lms_table_filter']//label");
    By displayedResults = By.xpath("//div[@class='nice-select dataTable_select']");
    By dropdownMenu2 = By.xpath("//button[@id='dropdownMenu2']");
    By backPageButton = By.xpath("//a[@id='lms_table_previous']");
    By nextPageButton = By.xpath("//a[@id='lms_table_next']");
    By copyButton = By.xpath("//button[@title='Sao chép']");
    By exportExcelButton = By.xpath("//button[@title='Excel']");
    By exportCSVButton = By.xpath("//button[@title='CSV']");
    By exportPDFButton = By.xpath("//button[@title='PDF']");
    By printButton = By.xpath("//button[@title='In ra']");

    public void inputSearch(String searchText) {
        sendKeys(searchInput, searchText);
    }

    public void clickAddAdminButton() {
        click(addAdminButton);
        waitUtils.waitForSeconds(2);
    }

    public void clickDisplayedResults (String results) {
        waitUtils.waitForClickable(displayedResults).click();

        By resultOption = By.xpath("//li[normalize-space()='" + results + "']");
        waitUtils.waitForVisible(resultOption);
        driver.findElement(resultOption).click();
    }

    public void clickDropdownAction(){
        waitUtils.waitForClickable(dropdownMenu2).click();
        waitUtils.waitForSeconds(1);
    }

    public void selectEditAdmin(){
        clickDropdownAction();
        By editOption = By.xpath("//*[@id=\"lms_table\"]/tbody/tr[1]/td[7]/div/div/button[1]");
        waitUtils.waitForVisible(editOption).click();
        waitUtils.waitForSeconds(1);
    }

    public void selectDeleteAdmin() {
        clickDropdownAction();
        By deleteOption = By.xpath("//*[@id=\"lms_table\"]/tbody/tr[1]/td[7]/div/div/button[2]");
        waitUtils.waitForVisible(deleteOption).click();
        waitUtils.waitForSeconds(1);
    }

    public void clickBackPageButton() {
        waitUtils.waitForClickable(backPageButton).click();
        waitUtils.waitForSeconds(2);
    }
    public void clickNextPageButton() {
        waitUtils.waitForClickable(nextPageButton).click();
        waitUtils.waitForSeconds(2);
    }

    public void clickStatusActiveByEmail(String email) {
        try {
            By statusActive = By.xpath("//tr[td[normalize-space()='" + email + "']]//label[contains(@class,'switch_toggle')]");

            WebElement checkbox = waitUtils.waitForVisible(statusActive);
//            System.out.println(checkbox.isSelected());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Không tìm thấy checkbox cho email: " + email);
        }

    }

    public void clickCopyButton() {
        waitUtils.waitForClickable(copyButton).click();
        waitUtils.waitForSeconds(2);
    }
    public void clickExportExcelButton() {
        waitUtils.waitForClickable(exportExcelButton).click();
        waitUtils.waitForSeconds(2);
    }
    public void clickExportCSVButton() {
        waitUtils.waitForClickable(exportCSVButton).click();
        waitUtils.waitForSeconds(2);
    }
    public void clickExportPDFButton() {
        waitUtils.waitForClickable(exportPDFButton).click();
        waitUtils.waitForSeconds(2);
    }
    public void clickPrintButton() {
        waitUtils.waitForClickable(printButton).click();
        waitUtils.waitForSeconds(2);
    }


}
