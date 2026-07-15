package vn.oes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

import java.util.List;

public class UserRole extends BasePage {

    private final WaitUtils waitUtils;

    public UserRole(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

//    WaitUtils waitUtils = new WaitUtils(driver);

    By menuProfile = By.xpath("//div[@class='dashboard-right-top-menu-profile']");
    By myProfileOption = By.cssSelector("a[href='https://ttdttx-dnu.welearning.vn/my-profile']");
    By nameUser = By.xpath("//input[@name='name']");
    By myQuizzes = By.cssSelector("a[href='https://ttdttx-dnu.welearning.vn/my-quizzes']");
    By getAllQuizzes = By.cssSelector("li[data-status='all']");
    By closeDialogWelcome = By.cssSelector("#tg-dialog-close-btn");

    public String getUserName(){
        // Chờ page load xong (menuProfile xuất hiện = DOM đã render đầy đủ)
        waitUtils.waitForVisible(menuProfile);
        // Lúc này DOM đã sẵn sàng → kiểm tra dialog ngay lập tức, không cần chờ
        dismissWelcomeDialogIfPresent();
        waitUtils.waitForVisible(menuProfile).click();
        waitUtils.waitForVisible(myProfileOption).click();
        return waitUtils.waitForVisible(nameUser).getAttribute("value");
    }

    private void dismissWelcomeDialogIfPresent() {
        List<WebElement> closeButtons = driver.findElements(closeDialogWelcome);
        if (closeButtons.isEmpty() || !closeButtons.get(0).isDisplayed()) {
            return; // Không có dialog → tiếp tục bình thường
        }
        try {
            closeButtons.get(0).click();
            waitUtils.waitForInvisibility(closeDialogWelcome);
        } catch (Exception ignored) {}
    }
//
//    public String getQuizzesTitle(String nameQuizzes){
//        waitUtils.waitForVisible(myQuizzes).click();
//        waitUtils.waitForSeconds(3);
//        waitUtils.waitForVisible(getAllQuizzes).click();
//        List<WebElement> quizzes = driver.findElements(By.cssSelector("#quizListBox"));
//        waitUtils.waitForSeconds(3);
//        for (WebElement quiz : quizzes) {
//            String quizTitle = quiz.findElement(By.cssSelector(".course-item-title")).getText();
//            if (quizTitle.equals(nameQuizzes)) {
//                return quizTitle;
//            }
//        }
//        return null;
//    }
}
