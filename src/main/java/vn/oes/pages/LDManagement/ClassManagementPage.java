package vn.oes.pages.LDManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class ClassManagementPage extends BasePage{

    WaitUtils waitUtils;

    public ClassManagementPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }
    private final By title =By.xpath("//h1[normalize-space()='Quản lý lớp học']");
    private final By btnAddClass = By.xpath("//ul[@class='d-flex']");



}
