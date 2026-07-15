package vn.oes.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtils {
    private static final String LOG_FILE = "logs/report-login.txt";

    // Ghi 1 dòng log (append)
    public static synchronized void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing log: " + e.getMessage());
        }
    }

    // Xoá nội dung file log
    public static synchronized void clearLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE))) {
            writer.write(""); // Ghi đè nội dung file với chuỗi rỗng
        } catch (IOException e) {
            System.err.println("Error clearing log: " + e.getMessage());
        }
    }

    // Tuỳ chọn: log tiêu đề nếu là CSV
    public static synchronized void logHeaderIfEmpty(String headerLine) {
        File logFile = new File(LOG_FILE);
        if (!logFile.exists() || logFile.length() == 0) {
            log(headerLine);
        }
    }
}
