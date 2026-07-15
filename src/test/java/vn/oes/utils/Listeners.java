package vn.oes.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);

        Object testInstance = result.getInstance();
        WebDriver driver = null;

        try {
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            driver = (WebDriver) driverField.get(testInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println("Unable to access WebDriver from test instance: " + e.getMessage());
        }

        if (driver == null) {
            return;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            String methodName = result.getMethod().getMethodName();
            File destDir = new File("src/test/resources/screenshots");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            File destFile = new File(destDir, methodName + "_" + timestamp + ".png");
            Files.copy(srcFile.toPath(), destFile.toPath());
            System.out.println("Saved screenshot: " + destFile.getAbsolutePath());
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
        }
    }
}
