package utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static String getTestData(String key) throws IOException {
        return getJsonData().getString(key);
    }

    public static JSONObject getJsonData() throws IOException {
        File filename = new File("resources//TestData//testdata.json");
        String json = FileUtils.readFileToString(filename, "UTF-8");
        return new JSONObject(json);
    }

    public static JSONArray getJsonArray(String key) throws IOException {
        JSONObject jsonObject = getJsonData();
        return jsonObject.getJSONArray(key);
    }

    public static Object getJsonArrayData(String key, int index) throws IOException {
        JSONArray languages = getJsonArray(key);
        return languages.get(index);
    }
}
