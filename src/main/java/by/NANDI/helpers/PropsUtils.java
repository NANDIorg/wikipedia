package by.NANDI.helpers;

import java.io.FileInputStream;
import java.util.Properties;

public class PropsUtils {
    /**
     * Gets the properties from file
     * @param filename name of the file
     * @return Properties object
     */
    public static Properties getProps(String filename) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(filename));
            return props;
        } catch (Exception e) {
            Logger.error("Properties file " + filename + " could not be parsed!");
            Logger.error(e.getMessage());
            return null;
        }
    }
}
