# LMS Automation Testing

Bộ test tự động cho hệ thống LMS WeLearning, được xây dựng bằng **Selenium WebDriver** + **TestNG** + **Maven**.

## 🛠️ Công nghệ sử dụng

- Java 11+
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager

## 📁 Cấu trúc dự án

```
LMS_Autotesting/
├── src/
│   └── test/
│       ├── java/           # Source code test
│       └── resources/
│           ├── config.properties
│           └── config.properties.example # Template cấu hình mẫu
├── testSuites/             # File TestNG XML suite
├── pom.xml                 # Maven dependencies
└── .gitignore
