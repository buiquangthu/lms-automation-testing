package vn.oes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CourseTestData {

    private String testId;
    private String description;


    private String titleVi;
    private String requirementsVi;
    private String outcomesVi;
    private String aboutVi;


    private String titleEn;
    private String requirementsEn;
    private String outcomesEn;
    private String aboutEn;


    private String type;               // Khóa học / Kỳ thi (Label text)
    private String requiredType;       // Bắt buộc / Tuỳ chọn (Label text)
    private String drip;               // Mở dần bài học: Có / Không

    private String category;           // Danh mục (VD: "Tiếng Nhật")
    private String modeOfDelivery;     // Hình thức đào tạo (VD: "Trực tuyến", "Trực tiếp")
    private String level;              // Mức độ (VD: "Beginner", "Pro")
    private String language;           // Ngôn ngữ (VD: "Tiếng Việt", "English")

    private String instructor;        // Giảng viên
    private String assistantInstructor; // Trợ giảng (Multi-select)


    private String duration;           // Thời lượng (Phút)
    private String accessLimit;        // Giới hạn truy cập theo ngày
    private String thumbnailPath;      // Đường dẫn tuyệt đối file ảnh thu nhỏ


    @JsonProperty("isSuccessExpected")
    private boolean isSuccessExpected;

    private String expectedMessage;
}
