package vn.oes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentTestData {
    private String testId;
    private String description;

    private String studentName;
    private String userName;
    private String email;
    private String studentCode;
    private String password;
    private String department;
    private String position;
    private String searchKeyword;

    private String targetEmail;
    private String newDepartment;
    private String newPosition;
    private String action;

    @JsonProperty("isSuccessExpected")
    private boolean isSuccessExpected;

    private String expectedMessage;
}
