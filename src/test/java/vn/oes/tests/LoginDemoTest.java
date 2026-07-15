package vn.oes.tests;

import org.testng.Assert;
import vn.oes.base.BaseTest;
import org.testng.annotations.Test;
import vn.oes.config.ConfigReader;
import vn.oes.pages.Auth.LoginPage;
import vn.oes.pages.UserRole;
import vn.oes.utils.LoggerUtils;

public class LoginDemoTest extends BaseTest {

    String baseUrl = ConfigReader.get("baseUrl");
    String username = ConfigReader.get("username");
    String password = ConfigReader.get("password");
    String invalidUsername = "test2@gmail.com";

    @Test
    public void LoginSuccessfull() throws InterruptedException {
        driver.get(baseUrl + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.changeLanguage(LoginPage.Language.ENGLISH);
        loginPage.login(username, password);
        assert loginPage.isLoginSuccessful() : "Login failed";
    }


    @Test(dataProvider = "loginData", dataProviderClass = vn.oes.utils.HandleReadFileUtils.class)
    public void checkInfoUser(String email, String password, String expectedUsername) throws InterruptedException {
        String normalizedEmail = email == null ? "" : email.trim();
        String normalizedExpectedUsername = expectedUsername == null ? "" : expectedUsername.trim();
        String normalizedActualUsername = "";

        try {
            driver.get(baseUrl + "/login");
            LoginPage loginPage = new LoginPage(driver);
            UserRole userRole = new UserRole(driver);
            loginPage.loginTtdttx(email, password);

            String actualUsername = userRole.getUserName();
            normalizedActualUsername = actualUsername == null ? "" : actualUsername.trim();

            Assert.assertEquals(normalizedActualUsername, normalizedExpectedUsername,
                    "Username hiển thị không đúng!");

            LoggerUtils.log(String.format("PASS | email=%s | expected=%s | actual=%s",
                    normalizedEmail, normalizedExpectedUsername, normalizedActualUsername));
        } catch (AssertionError | Exception e) {
            String reason = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
            LoggerUtils.log(String.format("FAIL | email=%s | expected=%s | actual=%s | reason=%s",
                    normalizedEmail, normalizedExpectedUsername, normalizedActualUsername, reason));
            throw e;
        }
    }

    //    @Test(dataProvider = "TitleQuizz", dataProviderClass = vn.oes.utils.HandleReadFileUtils.class)
//    @Test
//    public void checkQuizz() throws InterruptedException {
//        String expectedQuizz = "21/12/2025 - Tiếng Trung Quốc 1";
//        driver.get(baseUrl + "/login");
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.changeLanguage(LoginPage.Language.ENGLISH);
//        loginPage.login("Tester2712", "Demo@2025");
//        UserRole userRole = new UserRole(driver);
//        Thread.sleep(500);
//        String titleQuizz = userRole.getQuizzesTitle(expectedQuizz);
//
////            LoggerUtils.log("Checking quizz: " + email + " | Expect: " + expectedQuizz);
//        Assert.assertEquals(titleQuizz.trim(), expectedQuizz.trim(),
//                "Title quizz hiển thị không đúng!");
//    }
}
