package vn.oes.pages.UserManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

import javax.swing.*;

public class UsersPage extends BasePage {


    public UsersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    WaitUtils wait = new WaitUtils(driver);
    Actions actions = new Actions(driver);

    @FindBy(xpath = "//input[@id='searchBranch']")
    private WebElement searchOrgChat;

    @FindBy(xpath = "//input[@class=' primary_input_field height_px_35']")
    private WebElement searchStudentList;

    private By FilterByPosition = By.xpath("//span[normalize-space()='Filter by Position']");

    @FindBy(xpath = "//button[@id='dropdownMenuActions']")
    private WebElement dropdownMenuActions;
    @FindBy(xpath = "//a[@id='add_student_btn']")
    private WebElement addStudentButton;
    @FindBy(xpath = "//a[@id='import_student_btn']")
    private WebElement importStudentButton;
    @FindBy(xpath = "//a[normalize-space()='Export student list']")
    private WebElement exportStudentListButton;


    @FindBy(xpath = "//button[@id='dropdownMenu2']")
    private WebElement dropdownMenuActions2;
    @FindBy(xpath = "//a[@id='editStudent']")
    private WebElement editStudentButton;
    @FindBy(xpath = "//a[@id='deleteStudent']")
    private WebElement deleteStudentButton;
    @FindBy(xpath = "//a[@id='moveTo']")
    private WebElement changeDepartmentButton;
    @FindBy(xpath = "//a[@id='changeStatus']")
    private WebElement changeStatusButton;
    @FindBy(xpath = "//a[@id='learningprocess']")
    private WebElement learningProcessButton;
    @FindBy(xpath = "//a[@id='quizResult']")
    private WebElement quizResultButton;
    @FindBy(xpath = "//a[@id='studentGroup']")
    private WebElement studentGroupButton;


    public void searchOrgChat(String orgChatName) {
        searchOrgChat.clear();
        searchOrgChat.sendKeys(orgChatName);
    }

    public void searchStudentList(String studentName) {
        searchStudentList.clear();
        searchStudentList.sendKeys(studentName);
    }

    public void clickAddStudentButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenuActions']"));
        actions.doubleClick(dropdownMenuActions).perform();
        wait.waitForClickable(By.xpath("//a[@id='add_student_btn']"));
        wait.waitForSeconds(1);
        addStudentButton.click();
    }

    public void  clickImportStudentButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenuActions']"));
        actions.doubleClick(dropdownMenuActions).perform();
        wait.waitForClickable(By.xpath("//a[@id='import_student_btn']"));
        wait.waitForSeconds(1);
        importStudentButton.click();
    }
    public void clickExportStudentListButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenuActions']"));
        actions.doubleClick(dropdownMenuActions).perform();
        wait.waitForSeconds(1);
        exportStudentListButton.click();
    }

    public void clickEditStudentButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForClickable(By.xpath("//a[@id='editStudent']"));
        wait.waitForSeconds(1);
        editStudentButton.click();
    }

    public void clickDeleteStudentButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForSeconds(1);
        deleteStudentButton.click();
    }
    public void clickChangeDepartmentButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForSeconds(1);
        changeDepartmentButton.click();
    }
    public void clickChangeStatusButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForSeconds(1);
        changeStatusButton.click();
    }
    public void clickLearningProcessButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForVisible(By.xpath("//a[@id='learningprocess']"));
        wait.waitForSeconds(1);
        learningProcessButton.click();
    }
    public void clickQuizResultButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForVisible(By.xpath("//a[@id='quizResult']"));
        wait.waitForSeconds(1);
        quizResultButton.click();
    }
    public void clickStudentGroupButton() {
        wait.waitForClickable(By.xpath("//button[@id='dropdownMenu2']"));
        actions.doubleClick(dropdownMenuActions2).perform();
        wait.waitForSeconds(1);
        studentGroupButton.click();
    }

    public void selectFilterByPosition(String optionText) {
        WaitUtils waitUtils = new WaitUtils(driver);
        // cho menu hien thi
        waitUtils.waitForClickable(FilterByPosition).click();
        // chon option trong menu
        By optionLocator = By.xpath("//div[@class='nice-select primary_select studentPositionSelect width_200 open']//li[@class='option'][contains(text(),'" + optionText + "')]");
        waitUtils.waitForVisible(optionLocator);
        driver.findElement(optionLocator).click();
    }

    public void selectUserRadioButtonByName(String userName) {
        By radio = By.xpath("//tr[td[normalize-space()='" + userName + "']]//span[@class='checkmark']");
        if(!driver.findElement(radio).isSelected()) {
            wait.waitForClickable(radio).click();
        }
    }

    public void slectAllUserRadioButton() {
        By radio = By.xpath("//label[@for='allusers']//span[@class='checkmark']");
        if (!driver.findElement(radio).isSelected()) {
            wait.waitForClickable(radio).click();
        }
    }
}
