package vn.oes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PositionTestData {

    private String testId;
    private String description;
    private String positionName;
    private String positionCode;
    private String fileName;
    private String targetPositionName;
    private String newPositionName;
    private String newPositionCode;
    private String action;

    @JsonProperty("isSuccessExpected")
    private boolean isSuccessExpected;
    private String expectedMessage;

}
