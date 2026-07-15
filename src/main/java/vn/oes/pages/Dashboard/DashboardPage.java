package vn.oes.pages.Dashboard;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;


@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class DashboardPage extends BasePage {

    WaitUtils waitUtils = new WaitUtils(driver);
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    By titleDashboard = By.xpath("//div[@class='main-title']");
    By student = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div[1]/div[1]/a/div");
    By subAdmin = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div[1]/div[2]/a/div");
    By course = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div[2]/div[1]/a/div");
    By learningPath = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div[2]/div[2]/a/div");
    By Survey = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div[3]/div[1]/a/div");
    By quizzes = By.xpath("//*[@id=\"main-content\"]/div[2]/div[2]/div[2]/div[3]/div[2]/a/div");
    By selectRadiobuttonByDay7 = By.xpath("//label[@for='userLoginChartByDays7']");
    By selectRadiobuttonByDay14 = By.xpath("//label[@for='userLoginChartByDays14']");
    By selectRadiobuttonByDay30 = By.xpath("//label[@for='userLoginChartByDays30']");
    By selectRadiobuttonByDayOthers = By.xpath("//label[@for='userLoginChartByDaysCustom']");
    By selectRadiobuttonByTime7 = By.xpath("//label[@for='userLoginChartByTime7']");
    By selectRadiobuttonByTime14 = By.xpath("//label[@for='userLoginChartByTime14']");
    By selectRadiobuttonByTime30 = By.xpath("//label[@for='userLoginChartByTime30']");
    By selectRadiobuttonByTimeOthers = By.xpath("//label[@for='userLoginChartByTimeCustom']");


    public String getTitleDashboard() {
        return getText(titleDashboard);
    }

    public void clickStudent() {
        click(student);
        waitUtils.waitForUrlContains("allStudent");
        waitUtils.waitForSeconds(1);
    }

    public void clickSubAdmin() {
        click(subAdmin);
        waitUtils.waitForUrlContains("org-instructor-policy");
        waitUtils.waitForSeconds(1);
    }

    public void clickCourse() {
        click(course);
        waitUtils.waitForUrlContains("courses");
        waitUtils.waitForSeconds(1);
    }
    public void clickLearningPath() {
        click(learningPath);
        waitUtils.waitForUrlContains("path");
        waitUtils.waitForSeconds(1);
    }
    public void clickSurvey() {
        click(Survey);
        waitUtils.waitForUrlContains("survey/list");
        waitUtils.waitForSeconds(1);
    }
    public void clickQuizzes() {
        click(quizzes);
        waitUtils.waitForUrlContains("set-quiz");
        waitUtils.waitForSeconds(1);
    }

    public void selectRadiobuttonByday7(){
        if(driver.findElement(selectRadiobuttonByDay7).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByDay7).click();
            waitUtils.waitForSeconds(3);
        }
    }

    public void selectRadiobuttonByday14(){
        if(driver.findElement(selectRadiobuttonByDay14).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByDay14).click();
            waitUtils.waitForSeconds(3);
        }
    }
    public void selectRadiobuttonByday30(){
        if(driver.findElement(selectRadiobuttonByDay30).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByDay30).click();
            waitUtils.waitForSeconds(3);
        }
    }
    public void selectRadiobuttonBydayOthers(){
        if(driver.findElement(selectRadiobuttonByDayOthers).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByDayOthers).click();
            waitUtils.waitForSeconds(3);
        }
    }
    public void selectRadiobuttonByTime7(){
        if(driver.findElement(selectRadiobuttonByTime7).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByTime7).click();
            waitUtils.waitForSeconds(3);
        }
    }
    public void selectRadiobuttonByTime14(){
        if(driver.findElement(selectRadiobuttonByTime14).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByTime14).click();
            waitUtils.waitForSeconds(3);
        }
    }
    public void selectRadiobuttonByTime30(){
        if(driver.findElement(selectRadiobuttonByTime30).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByTime30).click();
            waitUtils.waitForSeconds(3);
        }
    }
    public void selectRadiobuttonByTimeOthers(){
        if(driver.findElement(selectRadiobuttonByTimeOthers).isSelected()){
            waitUtils.waitForClickable(selectRadiobuttonByTimeOthers).click();
            waitUtils.waitForSeconds(3);
        }
    }

}
