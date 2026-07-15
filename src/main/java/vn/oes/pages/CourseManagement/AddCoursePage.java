package vn.oes.pages.CourseManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vn.oes.core.BasePage;
import vn.oes.models.CourseTestData;
import vn.oes.utils.WaitUtils;

public class AddCoursePage extends BasePage {

    WaitUtils waitUtils;

    public AddCoursePage(WebDriver driver) {
        super(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    private final By title = By.xpath("//h1[normalize-space()='Thêm']");


    private final By tabEnglish = By.xpath("//a[@href='#elementen']");
    private final By tabVietnamese = By.xpath("//a[@href='#elementvi']");


    private final By inputTitleVi = By.name("title[vi]");
    private final By inputTitleEn = By.name("title[en]");
    private final By inputDuration = By.name("duration");
    private final By inputPassRate = By.name("pass_rate");
    private final By inputLeaderboardPoint = By.name("org_leaderboard_point");
    private final By inputPrice = By.name("price");
    private final By inputDiscountPrice = By.name("discount_price");
    private final By inputVideoUrl = By.name("trailer_link");
    private final By inputAccessLimit = By.name("access_limit");
    private final By inputMetaKey = By.name("meta_keywords");
    private final By inputMetaDesc = By.name("meta_description");


    private final By uploadVideoFile = By.xpath("//input[@type='file' and contains(@class, 'filepond--browser')]");
    private final By uploadThumbnail = By.id("document_file_thumb_2");


    private final By btnSave = By.id("save_button_parent");

    public void selectNiceOption(String selectName, String optionText) {
        if (optionText != null && !optionText.isEmpty()) {
            By dropdown = By.xpath("//select[@name='" + selectName + "']/following-sibling::div[contains(@class, 'nice-select')]");

            WebElement dropdownElement = waitUtils.waitForVisible(dropdown);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dropdownElement);

            waitUtils.waitForSeconds(1);

            waitUtils.waitForClickable(dropdown).click();

            By option = By.xpath("//select[@name='" + selectName + "']/following-sibling::div[contains(@class, 'nice-select')]//ul//li[normalize-space(.)='" + optionText + "']");
            waitUtils.waitForClickable(option).click();
        }
    }
    public void selectAssistantInstructor(String instructorName) {
        if (instructorName != null && !instructorName.isEmpty()) {
            By multiDropdownBtn = By.xpath("//select[@name='assistant_instructors[]']/following-sibling::div[contains(@class, 'ms-options-wrap')]/button");
            waitUtils.waitForClickable(multiDropdownBtn).click();


            By optionLabel = By.xpath("//select[@name='assistant_instructors[]']/following-sibling::div[contains(@class, 'ms-options-wrap')]//ul//li//label[contains(normalize-space(.), '" + instructorName + "')]");
            waitUtils.waitForClickable(optionLabel).click();

            driver.findElement(multiDropdownBtn).click();
        }
    }


    public void selectRadioButton(String radioName, String labelText) {
        if (labelText != null && !labelText.isEmpty()) {
            By radioLabel = By.xpath("//input[@name='" + radioName + "']/parent::label[contains(normalize-space(.), '" + labelText + "')]");

            WebElement radioElement = waitUtils.waitForVisible(radioLabel);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", radioElement);
            waitUtils.waitForSeconds(1); // Đợi 1 chút cho hiệu ứng cuộn mượt hoàn tất

            waitUtils.waitForClickable(radioLabel).click();
        }
    }


    public void toggleCheckboxStatus(String checkboxId) {
        By toggleSlider = By.xpath("//input[@id='" + checkboxId + "']/following-sibling::i");
        WebElement sliderElement = driver.findElement(toggleSlider);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", sliderElement);
        waitUtils.waitForSeconds(1);
        waitUtils.waitForClickable(toggleSlider).click();
    }


    public void enterSummernoteText(String textareaName, String text) {
        if (text != null && !text.isEmpty()) {
            By noteEditable = By.xpath("//textarea[@name='" + textareaName + "']/following-sibling::div[contains(@class, 'note-editor')]//div[contains(@class, 'note-editable')]");

            WebElement editor = waitUtils.waitForVisible(noteEditable);
            editor.clear();
            editor.sendKeys(text);
        }
    }

    public void enterVietnameseContent(String title, String requirements, String outcomes, String about) {
        waitUtils.waitForClickable(tabVietnamese).click();
        sendKeys(inputTitleVi, title);
        enterSummernoteText("requirements[vi]", requirements);
        enterSummernoteText("outcomes[vi]", outcomes);
        enterSummernoteText("about[vi]", about);
    }

    public void enterEnglishContent(String title, String requirements, String outcomes, String about) {
        waitUtils.waitForClickable(tabEnglish).click();
        sendKeys(inputTitleEn, title);
        enterSummernoteText("requirements[en]", requirements);
        enterSummernoteText("outcomes[en]", outcomes);
        enterSummernoteText("about[en]", about);
    }

    public void selectCourseSettings(String type, String requiredType, String drip, String category, String modeOfDelivery, String level, String language) {
        selectRadioButton("type", type);
        selectRadioButton("required_type", requiredType);
        selectRadioButton("drip", drip); // Mở dần bài học: "Có" hoặc "Không"

        selectNiceOption("category", category);
        selectNiceOption("mode_of_delivery", modeOfDelivery);
        selectNiceOption("level", level);
        selectNiceOption("language", language);
    }

    public void uploadThumbnailImage(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            driver.findElement(uploadThumbnail).sendKeys(filePath);
        }
    }

    public void clickSave() {
        waitUtils.waitForClickable(btnSave).click();
        waitUtils.waitForSeconds(3);
    }



    public void handleAddCourse(CourseTestData data) {
        if (data.getTitleVi() != null && !data.getTitleVi().isEmpty()) {
            enterVietnameseContent(
                    data.getTitleVi(),
                    data.getRequirementsVi(),
                    data.getOutcomesVi(),
                    data.getAboutVi()
            );
        }
        if (data.getTitleEn() != null && !data.getTitleEn().isEmpty()) {
            enterEnglishContent(
                    data.getTitleEn(),
                    data.getRequirementsEn(),
                    data.getOutcomesEn(),
                    data.getAboutEn()
            );
        }
        selectCourseSettings(
                data.getType(),             // Khóa học hay Kỳ thi
                data.getRequiredType(),     // Bắt buộc hay Tùy chọn
                data.getDrip(),             // Mở dần bài học
                data.getCategory(),         // Danh mục
                data.getModeOfDelivery(),   // Hình thức: "Trực tiếp" / "Trực tuyến"
                data.getLevel(),            // Cấp độ
                data.getLanguage()          // Ngôn ngữ
        );
        if(data.getInstructor() != null && !data.getInstructor().isEmpty()){
            selectNiceOption("assign_instructor", data.getInstructor());
        }

        if (data.getAssistantInstructor() != null && !data.getAssistantInstructor().isEmpty()) {
            selectAssistantInstructor(data.getAssistantInstructor());
        }

        if (data.getDuration() != null && !data.getDuration().isEmpty()) {
            sendKeys(inputDuration, data.getDuration());
        }

        if (data.getAccessLimit() != null && !data.getAccessLimit().isEmpty()) {
            sendKeys(inputAccessLimit, data.getAccessLimit());
        }

        if (data.getThumbnailPath() != null && !data.getThumbnailPath().isEmpty()) {
            uploadThumbnailImage(data.getThumbnailPath());
        }

        clickSave();
    }
}
