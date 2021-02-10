package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {
    private LoadProperties() {
    }

    public static void loadProperties(Properties properties, String name) {
        try (FileInputStream fileInputStream = new FileInputStream(name)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
