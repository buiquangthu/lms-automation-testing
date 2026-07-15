package vn.oes.tests;

import vn.oes.base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import vn.oes.pages.Dashboard.DashboardPage;
import vn.oes.utils.WaitUtils;

@Slf4j
public class DashboardTest extends BaseTest {
    WaitUtils waitUtils = new WaitUtils(driver);

    @Test
    public void gotoStudent() throws InterruptedException {
        LoginAdmin();
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickStudent();
    }
}
