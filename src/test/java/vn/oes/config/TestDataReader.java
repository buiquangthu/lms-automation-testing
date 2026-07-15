package vn.oes.config;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import vn.oes.models.*;
import vn.oes.utils.JsonUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class TestDataReader {

    private static final String LOGIN_DATA_FILE = "testdata/dataJson/login_data.json";
    private static final String POSITION_DATA_FILE = "testdata/dataJson/position_data.json";
    private static final String DEPARTMENT_DATA_FILE = "testdata/dataJson/department_data.json";
    private static final String STUDENT_DATA_FILE = "testdata/dataJson/student_data.json";
    private static final String COURSE_DATA_FILE = "testdata/dataJson/course_data.json";


    public static List<LoginTestData> getLoginTestData() {
        List<LoginTestData> testDataList = new ArrayList<>();

        try {
            JsonNode rootNode = JsonUtils.readJsonFile(LOGIN_DATA_FILE);
            JsonNode testCases = rootNode.get("loginTestCases"); // Lấy array "loginTestCases"

            if (testCases == null || !testCases.isArray()) {
                return testDataList;
            }

            for (JsonNode testCase : testCases) {
                LoginTestData data = JsonUtils.convertJsonNode(testCase, LoginTestData.class);
                testDataList.add(data);
//                log.debug("Loaded test data: {}", data.getTestId());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load login test data", e);
        }

        return testDataList;
    }

    public static LoginTestData getTestDataById(String testId) {

        return getLoginTestData().stream()
                .filter(data -> data.getTestId().equals(testId))
                .findFirst()
                .orElseThrow(() -> {
                    return new RuntimeException("Test data not found: " + testId);
                });
    }

    @DataProvider(name = "getLoginDataFromJson")
    public static Object[][] dataProviderLogin() {
        List<LoginTestData> testDataList = getLoginTestData();

        Object[][] data = new Object[testDataList.size()][1];

        for (int i = 0; i < testDataList.size(); i++) {
            data[i][0] = testDataList.get(i);
        }

        return data;
    }


    /**
     * Hàm dùng chung để đọc bất kỳ mảng dữ liệu nào trong file position_data.json
     * @param nodeName Tên mảng trong JSON (VD: "addPositionCases")
     * @return List chứa các Object PositionTestData
     */
    private static List<PositionTestData> getPositionTestDataByNode(String nodeName) {
        List<PositionTestData> testDataList = new ArrayList<>();
        try {
            JsonNode rootNode = JsonUtils.readJsonFile(POSITION_DATA_FILE);
            JsonNode testCases = rootNode.get(nodeName);

            if (testCases == null || !testCases.isArray()) {
                System.out.println("Warning: Không tìm thấy node '" + nodeName + "' trong file JSON!");
                return testDataList;
            }
            for (JsonNode testCase : testCases) {
                PositionTestData data = JsonUtils.convertJsonNode(testCase, PositionTestData.class);
                testDataList.add(data);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load position test data for node: " + nodeName, e);
        }
        return testDataList;
    }


    // --- CÁC DATAPROVIDER CHO POSITION TEST ---
    @DataProvider(name = "getPositionSearchDataFromJson")
    public static Object[][] dataProviderSearchPosition() {
        // Tên node "searchPositionCases" sẽ khớp với file JSON
        List<PositionTestData> list = getPositionTestDataByNode("searchPositionCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getAddPositionData")
    public static Object[][] dataProviderAddPosition() {
        List<PositionTestData> list = getPositionTestDataByNode("addPositionCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getEditPositionData")
    public static Object[][] dataProviderEditPosition() {
        List<PositionTestData> list = getPositionTestDataByNode("editPositionCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getDeletePositionData")
    public static Object[][] dataProviderDeletePosition() {
        List<PositionTestData> list = getPositionTestDataByNode("deletePositionCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getImportPositionData")
    public static Object[][] dataProviderImportPosition() {
        List<PositionTestData> list = getPositionTestDataByNode("importPositionCases");
        return convertListToDataProviderArray(list);
    }





    /**
     * Hàm dùng chung để đọc bất kỳ mảng dữ liệu nào trong file department_data.json
     * @param nodeName Tên mảng trong JSON (VD: "addDepartmentCases")
     * @return List chứa các Object BranchTestData
     */
    private static List<BranchTestData> getDepartmentTestDataByNode(String nodeName) {
        List<BranchTestData> testDataList = new ArrayList<>();
        try {
            JsonNode rootNode = JsonUtils.readJsonFile(DEPARTMENT_DATA_FILE);
            JsonNode testCases = rootNode.get(nodeName);

            if (testCases == null || !testCases.isArray()) {
                System.out.println("Warning: Không tìm thấy node '" + nodeName + "' trong file JSON!");
                return testDataList;
            }
            for (JsonNode testCase : testCases) {
                BranchTestData data = JsonUtils.convertJsonNode(testCase, BranchTestData.class);
                testDataList.add(data);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load department test data for node: " + nodeName, e);
        }
        return testDataList;
    }

    // --- CÁC DATAPROVIDER CHO BRANCH/DEPARTMENT TEST ---
    @DataProvider(name = "getDepartmentSearchData")
    public static Object[][] dataProviderSearchDepartment() {
        List<BranchTestData> list = getDepartmentTestDataByNode("searchDepartmentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getAddDepartmentData")
    public static Object[][] dataProviderAddDepartment() {
        List<BranchTestData> list = getDepartmentTestDataByNode("addDepartmentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getEditDepartmentData")
    public static Object[][] dataProviderEditDepartment() {
        List<BranchTestData> list = getDepartmentTestDataByNode("editDepartmentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getDeleteDepartmentData")
    public static Object[][] dataProviderDeleteDepartment() {
        List<BranchTestData> list = getDepartmentTestDataByNode("deleteDepartmentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getImportDepartmentData")
    public static Object[][] dataProviderImportDepartment() {
        List<BranchTestData> list = getDepartmentTestDataByNode("importDepartmentCases");
        return convertListToDataProviderArray(list);
    }


    private static List<StudentTestData> getStudentTestDataByNode(String nodeName) {
        List<StudentTestData> testDataList = new ArrayList<>();
        try {
            JsonNode rootNode = JsonUtils.readJsonFile(STUDENT_DATA_FILE);
            JsonNode testCases = rootNode.get(nodeName);

            if (testCases == null || !testCases.isArray()) {
                System.out.println("Warning: Không tìm thấy node '" + nodeName + "' trong file JSON!");
                return testDataList;
            }
            for (JsonNode testCase : testCases) {
                StudentTestData data = JsonUtils.convertJsonNode(testCase, StudentTestData.class);
                testDataList.add(data);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load student test data for node: " + nodeName, e);
        }
        return testDataList;
    }

    @DataProvider(name = "getStudentSearchData")
    public static Object[][] dataProviderSearchStudent() {
        List<StudentTestData> list = getStudentTestDataByNode("searchStudentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getAddStudentData")
    public static Object[][] dataProviderAddStudent() {
        List<StudentTestData> list = getStudentTestDataByNode("addStudentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getEditStudentData")
    public static Object[][] dataProviderEditStudent() {
        List<StudentTestData> list = getStudentTestDataByNode("editStudentCases");
        return convertListToDataProviderArray(list);
    }

    @DataProvider(name = "getDeleteStudentData")
    public static Object[][] dataProviderDeleteStudent() {
        List<StudentTestData> list = getStudentTestDataByNode("deleteStudentCases");
        return convertListToDataProviderArray(list);
    }

    private static List<CourseTestData> getCourseTestDataByNode(String nodeName) {
        List<CourseTestData> testDataList = new ArrayList<>();
        try {
            JsonNode rootNode = JsonUtils.readJsonFile(COURSE_DATA_FILE);
            JsonNode testCases = rootNode.get(nodeName);

            if (testCases == null || !testCases.isArray()) {
                System.out.println("Warning: Không tìm thấy node '" + nodeName + "' trong file JSON!");
                return testDataList;
            }
            for (JsonNode testCase : testCases) {
                CourseTestData data = JsonUtils.convertJsonNode(testCase, CourseTestData.class);
                testDataList.add(data);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load course test data for node: " + nodeName, e);
        }
        return testDataList;
    }

    @DataProvider(name = "getAddCourseData")
    public static Object[][] dataProviderAddCourse() {
        List<CourseTestData> list = getCourseTestDataByNode("addCourseCases");
        return convertListToDataProviderArray(list);
    }





    /**
     * Hàm dùng chung để convert List thành Object[][] cho DataProvider
     * Giúp code không bị lặp lại vòng lặp for ở mỗi DataProvider
     */
    private static Object[][] convertListToDataProviderArray(List<?> list) {
        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }
        return data;
    }
}
