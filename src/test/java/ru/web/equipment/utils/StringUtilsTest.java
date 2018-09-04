package ru.web.equipment.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {


    @Test
    public void testGetTrue() {
        assertEquals("да", StringUtils.getStringFromBoolean(true));
    }

    @Test
    public void testGetFalse() {
        assertEquals("нет", StringUtils.getStringFromBoolean(false));
    }

    @Test
    public void testIsBlank() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("   "));
        assertTrue(StringUtils.isBlank("   \n "));
        assertFalse(StringUtils.isBlank("n"));
    }

    @Test
    public void testIsNotBlank() {
        assertTrue(StringUtils.isNotBlank("1"));
    }

    @Test
    public void testEquals() {
        assertTrue(StringUtils.equals(null, null));
        assertTrue(StringUtils.equals("", ""));
        assertTrue(StringUtils.equals("testString", "testString"));
        assertFalse(StringUtils.equals("", null));
        assertFalse(StringUtils.equals("test", null));
        assertFalse(StringUtils.equals("test", ""));
        assertFalse(StringUtils.equals("test", "string"));
    }
}