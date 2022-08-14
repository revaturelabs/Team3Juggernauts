package com.juggernauts.todoapp.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.stereotype.Service;

/**
 * A service for loading properties from a file path
 * @author Jacob
 */
@Service
public class PropertiesFileLoaderService {
    /**
     * Gets the properties from a file path
     * @param filePath The path to the properties file
     * @return The properties in the file or an empty properties object if file not found
     */
    public Properties getProperties(String filePath) {
        try (InputStream input = Files.newInputStream(Paths.get(filePath))) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            return new Properties();
        }
    }
}
