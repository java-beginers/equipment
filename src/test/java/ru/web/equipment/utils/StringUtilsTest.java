package ru.web.equipment.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {


    @Test
    public void testGetTrue() {
        assertEquals("да", StringUtils.getStringFromBoolean(true));
    }


    @Test
    public void testGetFalse() {
        assertEquals("нет", StringUtils.getStringFromBoolean(false));
    }
}