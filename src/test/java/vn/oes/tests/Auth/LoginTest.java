package vn.oes.tests.Auth;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import vn.oes.base.BaseTest;
import org.testng.annotations.Test;
import vn.oes.config.ConfigReader;
import vn.oes.models.LoginTestData;
import vn.oes.pages.Auth.LoginPage;

@Slf4j
public class LoginTest extends BaseTest {

    String baseUrl = ConfigReader.get("baseUrl");

    @Test(dataProvider = "getLoginDataFromJson", dataProviderClass = vn.oes.config.TestDataReader.class)
    public void LoginWithDataFromJson(LoginTestData testData) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl + "/login");
        loginPage.login(testData.getUsername(), testData.getPassword());
        verifyLoginResult(testData, loginPage);
    }

    private void verifyLoginResult(LoginTestData testData, LoginPage loginPage) {
        LoginTestData.ExpectedResult expectedResult = testData.getExpectedResult();
        if (expectedResult == null) {
            return;
        }

        String redirectTo = expectedResult.getRedirectTo();
        String expectedMessage = expectedResult.getMessage();

        if (redirectTo != null && !redirectTo.isEmpty()) {

            Assert.assertTrue(
                    driver.getCurrentUrl().toLowerCase().contains(redirectTo.toLowerCase()),
                    String.format("Test [%s]: Expected URL chứa '%s' nhưng URL thực tế là '%s'",
                            testData.getTestId(), redirectTo, driver.getCurrentUrl())
            );
        }

        if (expectedMessage != null && !expectedMessage.isEmpty()) {
            if (redirectTo != null && !redirectTo.isEmpty()) {

                log.info("Test [{}]: Kiểm tra message thành công: '{}'", testData.getTestId(), expectedMessage);
            } else {

                String actualMessage = loginPage.getErrorMessage();
                Assert.assertEquals(
                        actualMessage, expectedMessage,
                        String.format("Test [%s]: Expected message '%s' nhưng actual message là '%s'",
                                testData.getTestId(), expectedMessage, actualMessage)
                );
                log.info("Test [{}]: Error message khớp: '{}'", testData.getTestId(), expectedMessage);
            }
        }
    }
}
