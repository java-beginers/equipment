package ru.web.equipment.utils;

import org.apache.commons.lang3.BooleanUtils;

public class StringUtils {

    public static String getStringFromBoolean(boolean val) {
       return val ? "да" : "нет";
    }
}
