package vn.oes.pages.CourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.utils.HandleFileUploadUtils;
import vn.oes.utils.WaitUtils;
import org.openqa.selenium.support.ui.Select;

public class AddLessonPage extends BasePage {

    WaitUtils waitUtils;

    public AddLessonPage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    private final By addChapter = By.id("add_option_box");
    private final By inputChapterName = By.cssSelector("[name='chapter_name']");
    private final By btnSaveChapter = By.xpath("//button[normalize-space()='Lưu lại']");

    // Tìm thẻ div chứa cụm từ '_section' (chỉ định các khối form) MÀ KHÔNG CÓ 'display: none'
    private final String ACTIVE_FORM_XPATH = "//div[contains(@id, '_section') and not(contains(@style, 'display: none'))]";


    private final By inputCommonName = By.xpath(ACTIVE_FORM_XPATH + "//input[@name='name' or @name='title' or @name='topic']");
    private final By inputCommonDuration = By.xpath(ACTIVE_FORM_XPATH + "//input[@name='duration']");  // Thời lượng
    private final By inputCommonDescription = By.xpath(ACTIVE_FORM_XPATH + "//textarea[@name='description' or @name='agenda']");

    private final By btnCommonSave = By.xpath(ACTIVE_FORM_XPATH + "//button[@type='submit' and contains(normalize-space(), 'Lưu lại')]");
    private final By materialSource = By.xpath("//button[contains(normalize-space(),'Nguồn học liệu')]");
    private final By lessonContent = By.xpath("//button[contains(normalize-space(),'Nội dung bài học')]");

    private final By importFileInput = By.xpath(ACTIVE_FORM_XPATH + "//div[contains(@class, 'filepond--drop-label')]");

    public void addChapter(String chapterName) {
        waitUtils.waitForClickable(addChapter).click();
        sendKeys(inputChapterName, chapterName);
        waitUtils.waitForClickable(btnSaveChapter).click();
    }


    public void openCategorySelectionForChapter(String chapterName, String type) {

        By chapterArrow = By.xpath("//div[contains(@class, 'single_role_blocks') and .//label[contains(normalize-space(), '" + chapterName + "')]]//div[contains(@class, 'arrow')]");

        WebElement arrowEl = waitUtils.waitForVisible(chapterArrow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", arrowEl);
        waitUtils.waitForSeconds(1);

        if (arrowEl.getAttribute("class").contains("collapsed")) {
            arrowEl.click();
            waitUtils.waitForSeconds(1);
        }
        By dynamicAddLessonBtn = By.xpath("//div[contains(@class, 'single_role_blocks') and .//label[contains(normalize-space(), '" + chapterName + "')]]//button[contains(@class, 'add_option_box')]");

        WebElement addBtn = waitUtils.waitForClickable(dynamicAddLessonBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        waitUtils.waitForSeconds(1);

        By dynamicBtnContent = By.xpath("//div[contains(@class, 'single_role_blocks') and .//label[contains(normalize-space(), '" + chapterName + "')]]//button[contains(@class, 'show_content')]");
        By dynamicBtnActivity = By.xpath("//div[contains(@class, 'single_role_blocks') and .//label[contains(normalize-space(), '" + chapterName + "')]]//button[contains(@class, 'show_activity')]");

        if (type.equalsIgnoreCase("content")) {
            waitUtils.waitForClickable(dynamicBtnContent).click();
        } else if (type.equalsIgnoreCase("activity")) {
            waitUtils.waitForClickable(dynamicBtnActivity).click();
        } else {
            throw new IllegalArgumentException("Type chỉ hỗ trợ 'content' hoặc 'activity'");
        }
    }

    public void selectLessonType(String typeName) {

        waitUtils.waitForSeconds(1);

        By optionBtn = null;
        String normalizedType = typeName.toLowerCase().trim();


        if (normalizedType.contains("video url") || normalizedType.equals("url")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='URL']");
        } else if (normalizedType.equals("video")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='Self']");
        } else if (normalizedType.equals("word")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='Word']");
        } else if (normalizedType.equals("powerpoint") || normalizedType.equals("ppt")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='PowerPoint']");
        } else if (normalizedType.equals("pdf")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='PDF']");
        } else if (normalizedType.equals("scorm")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='SCORM']");
        } else if (normalizedType.equals("excel")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='Excel']");
        } else if (normalizedType.contains("drive")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='GoogleDrive']");
        } else if (normalizedType.equals("youtube")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='Youtube']");
        } else if (normalizedType.equals("vimeo")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='Vimeo']");
        } else if (normalizedType.contains("iframe")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='Iframe']");
        } else if (normalizedType.contains("ai")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='AISlide']");
        }


        else if (normalizedType.equals("bài tập") || normalizedType.equals("assignment")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[@data-category='assignment']");
        } else if (normalizedType.equals("kỳ thi") || normalizedType.equals("quiz")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_quiz_section_inside')]");
        } else if (normalizedType.equals("khảo sát") || normalizedType.equals("survey")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_survey_section_inside')]");
        } else if (normalizedType.equals("quizzes")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_quizzes_section_inside')]");
        } else if (normalizedType.equals("wordcloud")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_wordcloud_section_inside')]");
        } else if (normalizedType.contains("khảo sát nâng cao")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_advancesurvey_section_inside')]");
        } else if (normalizedType.equals("zoom")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_zoom_section_inside')]");
        } else if (normalizedType.equals("meet")) {
            optionBtn = By.xpath("//div[contains(@class, 'modal-body')]//button[contains(@class, 'show_meet_section_inside')]");
        }

        else {
            throw new IllegalArgumentException("Không tìm thấy định dạng/hoạt động nào có tên: " + typeName);
        }

        WebElement itemElement = waitUtils.waitForVisible(optionBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", itemElement);

        waitUtils.waitForSeconds(1);
    }

    public void selectActionCard(String option){
        String normalizedOption = option.toLowerCase().trim();
        if(normalizedOption.contains("nguồn học liệu")){
            waitUtils.waitForClickable(materialSource).click();
        }
        else if(normalizedOption.contains("nội dung bài học")){
            waitUtils.waitForClickable(lessonContent).click();
        }
    }

    /**
     * Điền các thông tin cơ bản nhất có mặt ở hầu hết các form
     */
    public void fillBasicLessonInfo(String name, String duration, String description) {
        waitUtils.waitForSeconds(1);

        if (name != null && !name.isEmpty()) {
            waitUtils.waitForVisible(inputCommonName).clear();
            driver.findElement(inputCommonName).sendKeys(name);
        }

        if (duration != null && !duration.isEmpty()) {
            driver.findElement(inputCommonDuration).clear();
            driver.findElement(inputCommonDuration).sendKeys(duration);
        }

        if (description != null && !description.isEmpty()) {
            driver.findElement(inputCommonDescription).clear();
            driver.findElement(inputCommonDescription).sendKeys(description);
        }
    }

    public void fillBasicLessonInfo(String name, String duration, String description, String resourceFileName){
        String filePath = HandleFileUploadUtils.getTestResourcePath(resourceFileName);
        fillBasicLessonInfo(name, duration, description);

        if(resourceFileName != null && !resourceFileName.isEmpty()){

            waitUtils.waitForClickable(importFileInput).click();
            waitUtils.waitForSeconds(1);

            HandleFileUploadUtils.uploadWithRobot(filePath);
            waitForFilePondUploadComplete();
        }
    }


    public void selectDropdownInActiveForm(String selectName, String optionText) {
        if (optionText != null && !optionText.isEmpty()) {
            By dropdown = By.xpath(ACTIVE_FORM_XPATH + "//select[@name='" + selectName + "']/following-sibling::div[contains(@class, 'nice-select')]");

            WebElement dropdownElement = waitUtils.waitForVisible(dropdown);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dropdownElement);
            waitUtils.waitForSeconds(1);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownElement);

            By option = By.xpath(ACTIVE_FORM_XPATH + "//select[@name='" + selectName + "']/following-sibling::div[contains(@class, 'nice-select')]//ul//li[normalize-space(.)='" + optionText + "']");
            WebElement optionElement = waitUtils.waitForClickable(option);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
        }
    }

    public void fillMeetingSpecificInfo(String startTime, String password, String timeZoneValue) {
        // Nhập Thời gian bắt đầu
        if (startTime != null && !startTime.isEmpty()) {
            By inputStartTime = By.xpath(ACTIVE_FORM_XPATH + "//input[@name='start_time']");
            driver.findElement(inputStartTime).sendKeys(startTime);
        }

        // Nhập Mật khẩu (nếu có)
        if (password != null && !password.isEmpty()) {
            By inputPassword = By.xpath(ACTIVE_FORM_XPATH + "//input[@name='password']");
            if (!driver.findElements(inputPassword).isEmpty()) {
                driver.findElement(inputPassword).sendKeys(password);
            }
        }

        // Chọn Múi giờ (Sử dụng Select chuẩn của Selenium)
        if (timeZoneValue != null && !timeZoneValue.isEmpty()) {
            By selectTimeZone = By.xpath(ACTIVE_FORM_XPATH + "//select[@name='timezone']");
            WebElement tzElement = waitUtils.waitForVisible(selectTimeZone);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", tzElement);

            // Dùng class Select để thao tác trực tiếp với thẻ <select> HTML
            Select tzDropdown = new Select(tzElement);
            tzDropdown.selectByValue(timeZoneValue);
        }
        waitUtils.waitForSeconds(2);
    }

    public void toggleMeetingCheckbox(String inputName, boolean turnOn) {
        By checkbox = By.xpath(ACTIVE_FORM_XPATH + "//input[@name='" + inputName + "']");
        WebElement chkElement = driver.findElement(checkbox);

        if (chkElement.isSelected() != turnOn) {

            By checkmark = By.xpath(ACTIVE_FORM_XPATH + "//input[@name='" + inputName + "']/following-sibling::span[contains(@class, 'checkmark')]");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(checkmark));
        }
    }


    public void clickSaveLesson() {
        WebElement saveBtn = waitUtils.waitForClickable(btnCommonSave);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveBtn);
        saveBtn.click();
    }

}
