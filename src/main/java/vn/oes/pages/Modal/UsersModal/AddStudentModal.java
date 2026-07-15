package vn.oes.pages.Modal.UsersModal;

import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AddStudentModal extends BasePage {

    WaitUtils waitUtils = new WaitUtils(driver);
    public AddStudentModal(WebDriver driver) {
        super(driver);
    }

    private final By addName = By.xpath("//input[@id='addName']");
    private final By addUsername = By.xpath("//input[@id='addUsername']");
    private final By addEmail = By.xpath("//input[@id='addEmail']");
    private final By birthDay = By.xpath("//input[@id='birthDay']");
    private final By addPhone = By.xpath("//input[@id='addPhone']");
    private final By gender = By.xpath("//span[normalize-space()='Select Gender']");
    private final By about = By.xpath("//input[@id='addAbout']");
    private final By employeeId = By.xpath("//input[@id='addEmployeeId']");
    private final By addPassword = By.xpath("//input[@id='addPassword']");
    private final By addConfirmPassword = By.xpath("//input[@id='addCpassword']");
    private final By documentFile = By.xpath("//label[@for='document_file']");
    private final By selectPosition = By.xpath("//div[@id='add_student']//span[@class='current'][normalize-space()='Select Position']");
    private final By selectOrgChart = By.xpath("//div[@id='add_student']//span[@class='current'][normalize-space()='Select Org Chart']");
    private final By saveButton = By.xpath("//div[@id='add_student']//button[@id='save_button_parent']");

    public void AddName(String name) {
        sendKeys(addName, name);
    }
    public void AddUsername(String username) {
        sendKeys(addUsername, username);
    }

    public void AddEmail(String email) {
        sendKeys(addEmail, email);
    }
    public void BirthDay(String birthday) {
        sendKeys(birthDay, birthday);
    }
    public void AddPhone(String phone) {
        sendKeys(addPhone, phone);
    }
    public void About(String aboutText) {
        sendKeys(about, aboutText);
    }
    public void EmployeeId(String employeeIdText) {
        sendKeys(employeeId, employeeIdText);
    }
    public void AddPassword(String password) {
        sendKeys(addPassword, password);
    }
    public void AddConfirmPassword(String confirmPassword) {
        sendKeys(addConfirmPassword, confirmPassword);
    }
    public void selectGender(String genderOption){
        click(gender);
        By SelectGenderOption = By.xpath("//div[@class='nice-select primary_select open']//li[@class='option selected focus'][normalize-space()='" + genderOption + "']");
        click(SelectGenderOption);
    }

    public void selectPosition(String position) {
        click(selectPosition);
        By positionOption = By.xpath("//div[@class='nice-select primary_select mb-25 open']//li[@class='option'][contains(text(),'" + position + "')]");
        click(positionOption);
    }


    public void selectOrgChart(String orgChart) {
        click(selectOrgChart);
        By orgChartOption = By.xpath("//div[@class='nice-select primary_select mb-25 open']//li[@class='option'][normalize-space()='" + orgChart + "']");
        click(orgChartOption);
    }
    public void sendDocumentFile(String filePath) {
        WebElement fileInput = driver.findElement(documentFile);
        fileInput.sendKeys(filePath);
    }

    public void clickSaveButton() {
        click(saveButton);
    }

    public void addStudentRequirement(String name, String username, String email , String password, String confirmPassword, String position, String orgChart) {
        AddName(name);
        AddUsername(username);
        AddEmail(email);
        AddPassword(password);
        AddConfirmPassword(confirmPassword);

        waitUtils.waitForVisible(selectPosition);
        selectPosition(position);
        waitUtils.waitForInvisibility(By.xpath("//div[@class='loadingoverlay_element']//*[name()='svg']"));

        waitUtils.waitForVisible(selectOrgChart);
        selectOrgChart(orgChart);
        waitUtils.waitForInvisibility(By.xpath("//div[@class='loadingoverlay_element']//*[name()='svg']"));

        clickSaveButton();
    }
}
//div[@class='modal-backdrop fade show']
//div[@class='loadingoverlay_element']//*[name()='svg']