package ru.web.equipment.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Класс для тестирования персоны.
 */
public class PersonTest {

    @Test
    public void testGetFullName() {
        // Есть Фамилия, Имя, Отчество
        Person person = new Person();
        person.setFname("Иванов");
        person.setMname("Иван");
        person.setLname("Иванович");
        assertEquals("Иванов Иван Иванович", person.getFullName());
    }

    @Test
    public void testGetFullNameWithoutLName() {
        // Есть фамилия и имя, но отсутствует отчество.
        Person person = new Person();
        person.setFname("Иванов");
        person.setMname("Иван");
        assertEquals("Иванов Иван", person.getFullName());
    }

    @Test
    public void testGetShortName() {
        // Есть Фамилия, Имя, Отчество
        Person person = new Person();
        person.setFname("Иванов");
        person.setMname("  Иван  ");
        person.setLname("   Иванович");
        assertEquals("Иванов И. И.", person.getShortName());
    }

    @Test
    public void testGetShortNameWithoutLName() {
        // Есть фамилия и имя, но отсутствует отчество.
        Person person = new Person();
        person.setFname("Иванов");
        person.setMname("Иван");
        assertEquals("Иванов И.", person.getShortName());
    }
}