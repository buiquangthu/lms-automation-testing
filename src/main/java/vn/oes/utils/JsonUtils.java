package vn.oes.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode readJsonFile(String filePath) {
        try {
            InputStream inputStream = JsonUtils.class
                    .getClassLoader()
                    .getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new RuntimeException("Cannot find file: " + filePath);
            }


            JsonNode jsonNode = mapper.readTree(inputStream); //Parse InputStream thành JsonNode
            return jsonNode;

        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + filePath, e);
        }
    }


    public static <T> T convertJsonNode(JsonNode node, Class<T> clazz) { // method co the convert JsonNode thanh doi tuong cua class bat ki
        try {
            T result = mapper.treeToValue(node, clazz);
//            log.trace("Converted JsonNode to {}", clazz.getSimpleName());
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON node to " + clazz.getName(), e);
        }
    }


    public static String toJsonString(Object object) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }
}