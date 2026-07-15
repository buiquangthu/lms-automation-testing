package vn.oes.utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.nio.file.Paths;

public class HandleFileUploadUtils {

    public static String getTestResourcePath(String name) {
        try {
            String resourcePath = "testdata/dataImport/" + name;
            URL url = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(resourcePath);
            if (url == null) throw new IllegalArgumentException("Resource not found: " + name);
            return Paths.get(url.toURI()).toAbsolutePath().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uploadWithRobot(String filePath) {
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        try {
            Robot robot = new Robot();
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException("Lỗi khi upload file bằng Robot", e);
        }
    }
}
