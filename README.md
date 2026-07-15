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
│           ├── config.properties         # ⚠️ Không commit (đã có trong .gitignore)
│           └── config.properties.example # Template cấu hình mẫu
├── testSuites/             # File TestNG XML suite
├── pom.xml                 # Maven dependencies
└── .gitignore
```

## ⚙️ Cài đặt & Chạy

### 1. Clone repo

```bash
git clone https://github.com/buiquangthu/lms-automation-testing.git
cd lms-automation-testing
```

### 2. Cấu hình môi trường

```bash
# Copy file template
cp src/test/resources/config.properties.example src/test/resources/config.properties
```

Mở file `config.properties` và điền thông tin môi trường test của bạn:

```properties
baseUrl=https://your-lms-url.example.com
username=your_test_username
password=your_test_password
```

### 3. Chạy test

```bash
# Chạy toàn bộ test suite
mvn test

# Chạy với file suite cụ thể
mvn test -DsuiteXmlFile=testSuites/your_suite.xml
```

## 📌 Lưu ý

- **Không bao giờ** commit file `config.properties` lên Git — file này chứa thông tin nhạy cảm.
- Sử dụng tài khoản test chuyên biệt, không dùng tài khoản admin thật.
