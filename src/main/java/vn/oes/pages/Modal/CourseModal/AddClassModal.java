package vn.oes.pages.Modal.CourseModal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.WaitUtils;

public class AddClassModal extends BasePage {

    private WaitUtils waitUtils;

    public AddClassModal(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    // ── Locators ──────────────────────────────────────────────────────────────

    /** Tiêu đề (required) */
    private final By titleInput = By.id("addTitle");

    /** Địa điểm học trực tiếp */
    private final By offlineVenueInput = By.cssSelector("input[name='offline_venue']");

    /** Ngày bắt đầu (required) – datepicker, format dd-MM-yyyy */
    private final By startDateInput = By.id("join_date");

    /** Giờ bắt đầu (required) – timepicker, format hh:mm AM/PM */
    private final By startTimeInput = By.id("join_time");

    /** Ngày kết thúc (required) – datepicker, format dd-MM-yyyy */
    private final By endDateInput = By.id("end_date");

    /** Giờ kết thúc (required) – timepicker, format hh:mm AM/PM */
    private final By endTimeInput = By.id("end_time");

    /** Radio "Cho phép đăng ký = Có" (value="1") */
    private final By allowRegistrationYes = By.id("change_default_settings");

    /** Radio "Cho phép đăng ký = Không" (value="0") */
    private final By allowRegistrationNo = By.id("change_default_settings2");

    /** Giới thiệu */
    private final By aboutInput = By.id("addAbout");

    /** Input file ảnh (file upload) */
    private final By imageFileInput = By.id("document_file");

    /** Nút Lưu lại */
    private final By saveButton = By.id("save_button_parent");

    // ── Atomic actions ────────────────────────────────────────────────────────

    public void enterTitle(String title) {
        waitUtils.waitForVisible(titleInput);
        sendKeys(titleInput, title);
    }

    public void enterOfflineVenue(String venue) {
        sendKeys(offlineVenueInput, venue);
    }

    /**
     * Nhập ngày bắt đầu.
     * @param date định dạng dd-MM-yyyy (vd: 01-05-2026)
     */
    public void enterStartDate(String date) {
        WebElement input = waitUtils.waitForVisible(startDateInput);
        input.clear();
        input.sendKeys(date);
    }

    /**
     * Nhập giờ bắt đầu.
     * @param time định dạng hh:mm AM/PM (vd: 08:00 AM)
     */
    public void enterStartTime(String time) {
        WebElement input = waitUtils.waitForVisible(startTimeInput);
        input.clear();
        input.sendKeys(time);
    }

    /**
     * Nhập ngày kết thúc.
     * @param date định dạng dd-MM-yyyy (vd: 31-05-2026)
     */
    public void enterEndDate(String date) {
        WebElement input = waitUtils.waitForVisible(endDateInput);
        input.clear();
        input.sendKeys(date);
    }

    /**
     * Nhập giờ kết thúc.
     * @param time định dạng hh:mm AM/PM (vd: 05:00 PM)
     */
    public void enterEndTime(String time) {
        WebElement input = waitUtils.waitForVisible(endTimeInput);
        input.clear();
        input.sendKeys(time);
    }

    /**
     * Chọn trạng thái "Cho phép đăng ký".
     * @param allow true → Có, false → Không
     */
    public void selectAllowRegistration(boolean allow) {
        if (allow) {
            click(allowRegistrationYes);
        } else {
            click(allowRegistrationNo);
        }
    }

    public void enterAbout(String about) {
        sendKeys(aboutInput, about);
    }

    /**
     * Upload hình ảnh lớp học.
     * @param absoluteFilePath đường dẫn tuyệt đối tới file ảnh trên máy tính
     */
    public void uploadImage(String absoluteFilePath) {
        WebElement fileInput = driver.findElement(imageFileInput);
        fileInput.sendKeys(absoluteFilePath);
    }

    public void clickSave() {
        click(saveButton);
        waitUtils.waitForSeconds(2);
    }

    // ── Compound actions ──────────────────────────────────────────────────────

    /**
     * Thêm lớp học với các trường bắt buộc.
     *
     * @param title         Tiêu đề lớp học
     * @param startDate     Ngày bắt đầu (dd-MM-yyyy)
     * @param startTime     Giờ bắt đầu  (hh:mm AM/PM)
     * @param endDate       Ngày kết thúc (dd-MM-yyyy)
     * @param endTime       Giờ kết thúc  (hh:mm AM/PM)
     * @param allowRegister Cho phép đăng ký (true/false)
     */
    public void handleAddClass(String title,
                               String startDate, String startTime,
                               String endDate,   String endTime,
                               boolean allowRegister) {
        enterTitle(title);
        enterStartDate(startDate);
        enterStartTime(startTime);
        enterEndDate(endDate);
        enterEndTime(endTime);
        selectAllowRegistration(allowRegister);
        clickSave();
    }

    /**
     * Thêm lớp học với đầy đủ thông tin (bao gồm cả tuỳ chọn).
     *
     * @param title         Tiêu đề lớp học
     * @param offlineVenue  Địa điểm học trực tiếp (nullable)
     * @param startDate     Ngày bắt đầu (dd-MM-yyyy)
     * @param startTime     Giờ bắt đầu  (hh:mm AM/PM)
     * @param endDate       Ngày kết thúc (dd-MM-yyyy)
     * @param endTime       Giờ kết thúc  (hh:mm AM/PM)
     * @param allowRegister Cho phép đăng ký (true/false)
     * @param about         Giới thiệu (nullable)
     * @param imagePath     Đường dẫn tuyệt đối file ảnh (nullable)
     */
    public void handleAddClassFull(String title,
                                   String offlineVenue,
                                   String startDate, String startTime,
                                   String endDate,   String endTime,
                                   boolean allowRegister,
                                   String about,
                                   String imagePath) {
        enterTitle(title);

        if (offlineVenue != null && !offlineVenue.isEmpty()) {
            enterOfflineVenue(offlineVenue);
        }

        enterStartDate(startDate);
        enterStartTime(startTime);
        enterEndDate(endDate);
        enterEndTime(endTime);
        selectAllowRegistration(allowRegister);

        if (about != null && !about.isEmpty()) {
            enterAbout(about);
        }

        if (imagePath != null && !imagePath.isEmpty()) {
            uploadImage(imagePath);
        }

        clickSave();
    }

    // ── Validation helpers ────────────────────────────────────────────────────

    public String getTitleValidationMessage() {
        return getValidationMessage(titleInput);
    }

    public String getStartDateValidationMessage() {
        return getValidationMessage(startDateInput);
    }

    public String getStartTimeValidationMessage() {
        return getValidationMessage(startTimeInput);
    }

    public String getEndDateValidationMessage() {
        return getValidationMessage(endDateInput);
    }

    public String getEndTimeValidationMessage() {
        return getValidationMessage(endTimeInput);
    }

    public boolean isTitleFieldRequired() {
        return isFieldRequired(titleInput);
    }
}
