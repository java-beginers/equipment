package ru.web.equipment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Утилитарный класс для различных вспомогательных методов.
 */
public class TestUtils {

    /**
     * Преобразует строку в дату.
     * @param dateString строка с датой вида 22.09.2017
     * @return Дата
     * @throws Exception Если что-то пойдет не так.
     */
    public static Date getDateFromString(String dateString) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.parse(dateString);
    }
}
