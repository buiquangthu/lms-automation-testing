package vn.oes.tests.Ticket;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import vn.oes.base.BaseTest;
import vn.oes.config.ConfigReader;
import vn.oes.pages.Component.SidebarMenu;

public class AddTicketTest extends BaseTest {

    SidebarMenu sidebarMenu;

    @BeforeMethod
    public void setupTicket() throws InterruptedException {
        sidebarMenu = new SidebarMenu(driver);
        LoginAs(ConfigReader.get("username"), ConfigReader.get("password"));
        sidebarMenu.gotoTicket();
    }

    @Test
    public void testAddTicket() {

    }

}
