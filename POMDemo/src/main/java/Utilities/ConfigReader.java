package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        try {
            // Provide the path to your .properties file
            String filePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
            FileInputStream fis = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fis); // Loads key-value pairs
        } catch (Exception e) {
            System.out.println("Properties error: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key); // Retrieves value by key
    }
}
