package constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Reader {
    public static String getEnvironmentConfig(String propertiesLocation,String propertyName) {
        return getPropertyValue(propertiesLocation, propertyName);
    }

    public static String getPropertyValue(String location, String propertyName) {
        String propertyValue = null;
        Properties prop = new Properties();
        FileInputStream file;
        {
            try {
                file = new FileInputStream(location);
                prop.load(file);
                propertyValue = prop.getProperty(propertyName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return propertyValue;

        }
    }
}
