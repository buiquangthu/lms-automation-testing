package vn.oes.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import vn.oes.config.ConfigReader;
import vn.oes.core.DriverFactory;
import vn.oes.pages.Auth.LoginPage;
import vn.oes.utils.LoggerUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Slf4j
public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setUp(Method method){
        driver = DriverFactory.initDriver();
        if(method.getName().equals("checkInfoUser")){
            clearLogFileIfNeeded();
        }
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver();
    }


    protected void LoginAdmin() throws InterruptedException {

        driver.get(ConfigReader.get("baseUrl") + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.changeLanguage(LoginPage.Language.VIETNAMESE);
        loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );

        assert loginPage.isLoginSuccessful() : "Login failed";
    }

    protected void LoginAs(String username, String password) throws InterruptedException {
        driver.get(ConfigReader.get("baseUrl") + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.changeLanguage(LoginPage.Language.VIETNAMESE);
        loginPage.login(username, password);

        assert loginPage.isLoginSuccessful() : "Login failed for user: " + username;
    }

    
    public String getScreenshot(String testcaseName, WebDriver driver) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        String fileName = testcaseName + "_" + timestamp + ".png";
        String screenshotPath = screenshotDir + fileName;

        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileHandler.copy(src, dest);
            log.info("Screenshot saved: {}", screenshotPath);
        } catch (IOException e) {
            log.error("Lỗi khi lưu screenshot: {}", e.getMessage());
            throw new RuntimeException("Không thể lưu screenshot: " + screenshotPath, e);
        } catch (Exception e) {
            log.error("Lỗi khi chụp screenshot: {}", e.getMessage());
            throw new RuntimeException("Không thể chụp screenshot cho test: " + testcaseName, e);
        }

        return screenshotPath;
    }

    protected String getToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By toastTitle = By.xpath("//div[@class='toast-message']");
        wait.until(ExpectedConditions.presenceOfElementLocated(toastTitle));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastTitle));
        return driver.findElement(toastTitle).getText();
    }


    protected File waitForFileDownloaded(String dir, String ext, int timeoutSeconds) throws InterruptedException {
        File folder = new File(dir);
        File[] files;
        int waited = 0;
        while (waited < timeoutSeconds) {
            files = folder.listFiles((d, name) -> name.toLowerCase().endsWith(ext));
            if (files != null && files.length > 0) {
                return files[0];
            }
            Thread.sleep(1000);
            waited++;
        }
        throw new RuntimeException("File không được tải về sau " + timeoutSeconds + " giây.");
    }

    private static boolean isLogCleared = false;

    private void clearLogFileIfNeeded() {
        if (!isLogCleared) {
            LoggerUtils.clearLog();
            LoggerUtils.logHeaderIfEmpty("RESULT | EMAIL | EXPECTED | ACTUAL | REASON");
            isLogCleared = true;
        }
    }

}

