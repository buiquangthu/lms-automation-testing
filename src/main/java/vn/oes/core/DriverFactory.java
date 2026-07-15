package vn.oes.core;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vn.oes.config.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver initDriver() {
        if (driverThreadLocal.get() == null) {
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("headless");
//            options.addArguments("window-size=1920,1080");

            options.addArguments("--incognito"); //ẩn danh
            options.addArguments("--allow-running-insecure-content");
            options.setAcceptInsecureCerts(true);
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    // Lấy driver hiện tại của thread
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Dùng cho test có chức năng tải file
    public static WebDriver initDownloadDriver(String downloadPath) {
        if (driverThreadLocal.get() == null) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadPath); // chi định thư mục tải xuống
            prefs.put("download.prompt_for_download", false); // không hiển thị hộp thoại xác nhận tải xuống
            prefs.put("profile.default_content_settings.popups", 0);
            prefs.put("plugins.always_open_pdf_externally", true);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--incognito");
            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driverThreadLocal.set(driver);
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
