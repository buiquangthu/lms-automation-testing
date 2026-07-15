package vn.oes.pages.Modal.UsersModal;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EditStudentModal extends BasePage {
    public EditStudentModal(WebDriver driver) {
        super(driver);
    }

    WaitUtils waitUtils = new WaitUtils(driver);

    private final By addName = By.xpath("//input[@id='studentName']");
    private final By addUsername = By.xpath("//input[@id='studentUsername']");
    private final By addEmail = By.xpath("//input[@id='studentEmail']");
    private final By birthDay = By.xpath("//input[@id='studentDob']");
    private final By addPhone = By.xpath("//input[@id='studentPhone']");
    private final By about = By.xpath("//input[@id='studentAbout']");
    private final By employeeId = By.id("editEmployeeId");
    private final By addPassword = By.xpath("//input[@id='password']");
    private final By addConfirmPassword = By.xpath("//input[@id='password_confirmation']");
    private final By documentFile = By.xpath("//label[@for='document_file_edit']");
    private final By updateButton = By.xpath("//div[@role='dialog']//div//div//div//form//div//div//button[@type='submit'][normalize-space()='Update Student']");

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

    public void selectPosition(String position) {
        By dropdown = By.xpath("//*[@id=\"editStudentModal\"]/div/div/div[2]/form/div[4]/div[1]/div/div/span");
        waitUtils.waitForClickable(dropdown).click();
        waitUtils.waitForSeconds(1);
        selectDropdownOptionByText(position);
    }


    public void selectOrgChart(String orgChart) {
        // mo dropdown org chart
        By dropdown = By.xpath("//*[@id=\"editStudentModal\"]/div/div/div[2]/form/div[4]/div[2]/div/div/span");
        waitUtils.waitForClickable(dropdown).click();
        waitUtils.waitForSeconds(1);

        selectDropdownOptionByText(orgChart);
    }

    public void sendDocumentFile(String filePath) {
        WebElement fileInput = driver.findElement(documentFile);
        fileInput.sendKeys(filePath);
    }

    public void clickUpdateButton() {
        click(updateButton);
    }

    // Tìm và click vào option khớp text bên trong dropdown đang mở
    public void selectDropdownOptionByText(String optionText) {
        By option = By.xpath("//div[contains(@class,'nice-select') and contains(@class,'open')]//li[contains(@class,'option') and normalize-space(text())='" + optionText + "']");
        waitUtils.waitForVisible(option).click();
    }


    public void updateUser(String fullname,
                           String username,
                           String email,
                           String dateOfBirth,
                           String phone,
                           String aboutText,
                           String employeeId,
                           String password,
                           String confirmPassword,
                           String position,
                           String orgChart
    ) {
        if(fullname != null && !fullname.isEmpty()) {
            AddName(fullname);
        }
        if(username != null && !username.isEmpty()) {
            AddUsername(username);
        }
        if(email != null && !email.isEmpty()) {
            AddEmail(email);
        }
        if(dateOfBirth != null && !dateOfBirth.isEmpty()) {
            BirthDay(dateOfBirth);
        }
        if(phone != null && !phone.isEmpty()) {
            AddPhone(phone);
        }
        if(aboutText != null && !aboutText.isEmpty()) {
            About(aboutText);
        }
        if(employeeId != null && !employeeId.isEmpty()) {
            EmployeeId(employeeId);
        }
        if(password != null && !password.isEmpty()) {
            AddPassword(password);
        }
        if(confirmPassword != null && !confirmPassword.isEmpty()) {
            AddConfirmPassword(confirmPassword);
        }
        if(position != null && !position.isEmpty()) {
            selectPosition(position);
        }
        if(orgChart != null && !orgChart.isEmpty()) {
            selectOrgChart(orgChart);
        }

        click(updateButton);
    }
}
