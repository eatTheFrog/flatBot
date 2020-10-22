package ru.eatthefrog.hatterBot;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

public class PropertiesProvider {
    public String getProperty(String fileName, String propertyName) {
        Properties prop = new Properties();
        try {
            //load a properties file from class path, inside static method
            prop.load(Application.class.getClassLoader().getResourceAsStream(fileName));

            //get the property value and print it out
            return prop.getProperty(propertyName);

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}