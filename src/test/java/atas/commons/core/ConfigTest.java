package atas.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Level;

import org.junit.jupiter.api.Test;

public class ConfigTest {
    private final Config defaultConfig = new Config();

    @Test
    public void toStringTest_defaultObject_stringReturned() {
        String defaultConfigAsString = "Current log level : INFO\n"
                + "Preference file Location : preferences.json";

        assertEquals(defaultConfigAsString, new Config().toString());
    }

    @Test
    public void equalsTest() {
        assertNotNull(defaultConfig);
        assertTrue(defaultConfig.equals(defaultConfig));
        assertFalse(defaultConfig.equals(null));
        Config anotherConfig = new Config();
        anotherConfig.setLogLevel(Level.WARNING);
        assertFalse(defaultConfig.equals(anotherConfig));
    }

    @Test
    public void getLogLevelTest() {
        Config anotherConfig = new Config();
        assertEquals(defaultConfig.getLogLevel(), anotherConfig.getLogLevel());
    }

    @Test
    public void getUserPrefFilePathTest() {
        Config anotherConfig = new Config();
        assertEquals(defaultConfig.getUserPrefsFilePath(), anotherConfig.getUserPrefsFilePath());
    }

    @Test
    public void hashCodeTest() {
        Config anotherConfig = new Config();
        assertEquals(defaultConfig.hashCode(), anotherConfig.hashCode());
    }
}
