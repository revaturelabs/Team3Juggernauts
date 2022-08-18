package com.juggernauts.todoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.file.Paths;
import java.util.Properties;

import com.juggernauts.todoapp.configurations.interceptors.services.PropertiesFileLoaderService;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.apache.catalina.webresources.ClasspathURLStreamHandler;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ContextConfiguration(classes = {PropertiesFileLoaderService.class})
@ActiveProfiles({"josh"})
@ExtendWith(SpringExtension.class)
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

    /**
     * Method under test: {@link PropertiesFileLoaderService#getProperties(String)}
     */
    @org.junit.jupiter.api.Test
    void testGetProperties() {
        assertTrue(propertiesFileLoaderService.getProperties("/directory/foo.txt").isEmpty());
        assertTrue(propertiesFileLoaderService.getProperties(new ClassPathResource("Path")).isEmpty());
        assertEquals(2, propertiesFileLoaderService.getProperties(new ClassPathResource("/")).size());
    }

    /**
     * Method under test: {@link PropertiesFileLoaderService#getProperties(ClassPathResource)}
     */
    @org.junit.jupiter.api.Test
    @Disabled("TODO: Complete this test")
    void testGetProperties2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.juggernauts.todoapp.services.PropertiesFileLoaderService.getProperties(PropertiesFileLoaderService.java:43)
        //   In order to prevent getProperties(ClassPathResource)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getProperties(ClassPathResource).
        //   See https://diff.blue/R013 to resolve this issue.

        propertiesFileLoaderService.getProperties((ClassPathResource) null);
    }

    /**
     * Method under test: {@link PropertiesFileLoaderService#getProperties(ClassPathResource)}
     */
    @org.junit.jupiter.api.Test
    @Disabled("TODO: Complete this test")
    void testGetProperties3() throws MalformedURLException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalStateException: Illegal access: this web application instance has been stopped already. Could not load [could not open {}]. The following stack trace is thrown for debugging purposes as well as to attempt to terminate the thread which caused the illegal access.
        //       at org.apache.catalina.loader.WebappClassLoaderBase.checkStateForResourceLoading(WebappClassLoaderBase.java:1432)
        //       at org.apache.catalina.loader.WebappClassLoaderBase.getResource(WebappClassLoaderBase.java:1057)
        //       at org.springframework.core.io.ClassPathResource.getInputStream(ClassPathResource.java:193)
        //       at com.juggernauts.todoapp.services.PropertiesFileLoaderService.getProperties(PropertiesFileLoaderService.java:43)
        //   In order to prevent getProperties(ClassPathResource)
        //   from throwing IllegalStateException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getProperties(ClassPathResource).
        //   See https://diff.blue/R013 to resolve this issue.

        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler((String) any())).thenReturn(new ClasspathURLStreamHandler());
        propertiesFileLoaderService.getProperties(new ClassPathResource("could not open {}",
                new URLClassLoader(new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                        new ParallelWebappClassLoader(), urlStreamHandlerFactory)));
    }

    @Test
    public void givenInvalidFilePath_whenGetProperties_thenReturnEmptyProperties() {
        Properties props = propertiesFileLoaderService.getProperties("invalid/path");
        assertEquals(new Properties(), props);
    }

    @Test
    public void givenValidClassPathResource_whenGetProperties_thenReturnProperties() {
        ClassPathResource resource = new ClassPathResource("test.properties");
        Properties props = propertiesFileLoaderService.getProperties(resource);
        assertEquals("daisy", props.getProperty("cat.daisy.name", ""));
        assertEquals("benedict", props.getProperty("cat.benedict.name", ""));
        assertEquals("francis", props.getProperty("cat.francis.name", ""));
    }

    @Test
    public void givenInvalidClassPathResource_whenGetProperties_thenReturnEmptyProperties() {
        ClassPathResource resource = new ClassPathResource("invalid.properties");
        Properties props = propertiesFileLoaderService.getProperties(resource);
        assertEquals(new Properties(), props);
    }
}
