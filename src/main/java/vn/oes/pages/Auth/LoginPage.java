package vn.oes.pages.Auth;

import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.config.ConfigReader;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

import static java.lang.Thread.sleep;
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LoginPage extends BasePage {

    WaitUtils waitUtils;

    By inputUsername = By.id("input-username");
    By inputPassword = By.id("password-input");
    By btnLogin = By.id("login-alt");
    By btnLoginttdtx = By.cssSelector("button[class='btn btn-lg btn-primary w-100']");
    By errorMessage = By.cssSelector(".alert-danger");
    By langSelecter = By.cssSelector(".lang-selected");
    By langOptionVietnamese = By.cssSelector("a[href='"+ ConfigReader.get("baseUrl") +"/change-language/vi']");
    By langOptionEnglish = By.cssSelector("a[href='"+ConfigReader.get("baseUrl")+"/change-language/en']");

    public LoginPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }


    public void login(String username, String password) throws InterruptedException {
        sendKeys(inputUsername, username);
        sendKeys(inputPassword, password);
        click(btnLogin);
    }

    public void loginTtdttx(String username, String password) throws InterruptedException {
        sendKeys(inputUsername, username);
        sendKeys(inputPassword, password);
        click(btnLoginttdtx);
    }


    public boolean isLoginSuccessful() {

        try {
            waitUtils.waitForUrlContains("dashboard");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void changeLanguage(Language language) {
        click(langSelecter);
        switch (language) {
            case VIETNAMESE -> click(langOptionVietnamese);
            case ENGLISH -> click(langOptionEnglish);
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }

    public enum Language {
        VIETNAMESE,
        ENGLISH
        
    }

}