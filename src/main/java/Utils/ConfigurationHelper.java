package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHelper {

    private static final Properties props = new Properties();

    public static void getPropertiesFromConfigFile() {
        try {
            props.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new ProcessingException("Error opening config file");
        }
    }

    public static String getProperty(String nameProperty) {

        return props.getProperty(nameProperty);
    }
}
