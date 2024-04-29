package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtility {

    private static final String TestDataPath = "src/test/resources/TestData/";

    public static String getJsonData(String jsonFileName , String field) throws FileNotFoundException {
        FileReader reader = new FileReader(TestDataPath + jsonFileName + ".json" );
        JsonElement jsonElement = JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(field).getAsString();
    }


    public static String getPropertiesValue(String fileName , String filed) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TestDataPath + fileName + ".properties"));
        return properties.getProperty(filed);
    }
}
