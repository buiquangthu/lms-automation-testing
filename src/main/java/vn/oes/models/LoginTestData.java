package vn.oes.models;

import lombok.Data;

@Data
public class LoginTestData {
    private String testId;
    private String description;
    private String username;
    private String password;
    private boolean rememberMe;
    private ExpectedResult expectedResult;



    @Data
    public static class ExpectedResult{
        private String redirectTo;
        private String message;
    }
}
