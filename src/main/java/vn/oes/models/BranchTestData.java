package vn.oes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BranchTestData {

    private String testId;
    private String description;
    private String departmentName;
    private String departmentCode;
    private String parentDepartment;
    private String targetDepartmentName;
    private String targetSubDepartmentName;
    private String newDepartmentName;
    private String newDepartmentCode;
    private String action;
    private String fileName;


    @JsonProperty("isSuccessExpected")
    private boolean isSuccessExpected;
    private String expectedMessage;

}
