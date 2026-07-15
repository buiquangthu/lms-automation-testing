package vn.oes.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

@Slf4j
public class Listeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed: {}", result.getMethod().getMethodName());

        Object testInstance = result.getInstance();
        WebDriver driver = getDriverFromInstance(testInstance);

        if (driver != null) {
            BaseTest baseTest = new BaseTest();
            String screenshotPath = baseTest.getScreenshot(result.getMethod().getMethodName(), driver);
            log.info("Screenshot saved: {}", screenshotPath);
        } else {
            log.warn("Không thể chụp screenshot: WebDriver is null");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("⏭ Test Skipped: {}", result.getMethod().getMethodName());
    }

    /**
     * Lấy WebDriver từ test instance bằng reflection.
     * Tìm field "driver" trong class hiện tại hoặc class cha (BaseTest).
     */
    private WebDriver getDriverFromInstance(Object testInstance) {
        Class<?> clazz = testInstance.getClass();
        while (clazz != null) {
            try {
                Field driverField = clazz.getDeclaredField("driver");
                driverField.setAccessible(true);
                return (WebDriver) driverField.get(testInstance);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass(); // Tìm tiếp ở class cha
            } catch (IllegalAccessException e) {
                log.error("Không thể truy cập field 'driver': {}", e.getMessage());
                return null;
            }
        }
        log.error("Không tìm thấy field 'driver' trong test instance: {}", testInstance.getClass().getName());
        return null;
    }
}
