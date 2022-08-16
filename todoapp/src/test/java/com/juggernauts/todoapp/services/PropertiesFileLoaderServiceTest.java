package com.juggernauts.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;


public class PropertiesFileLoaderServiceTest {
    private PropertiesFileLoaderService propertiesFileLoaderService;

    @Before
    public void init() {
        propertiesFileLoaderService = new PropertiesFileLoaderService();
    }

    @Test
    public void givenValidFilePath_whenGetProperties_thenReturnProperties() {
        Properties props = propertiesFileLoaderService.getProperties("src/test/resources/test.properties");
        assertEquals("daisy", props.getProperty("cat.daisy.name", ""));
        assertEquals("benedict", props.getProperty("cat.benedict.name", ""));
        assertEquals("francis", props.getProperty("cat.francis.name", ""));
    }

    @Test
    public void givenInvalidFilePath_whenGetProperties_thenReturnEmptyProperties() {
        Properties props = propertiesFileLoaderService.getProperties("invalid/path/sad");
        assertEquals(new Properties(), props);
    }
}
