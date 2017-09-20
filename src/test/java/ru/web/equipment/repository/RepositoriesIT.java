package ru.web.equipment.repository;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Equipment;
import ru.web.equipment.entity.Vendor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Интеграционный тест для проверки репозиториев.
 * В нем будет по одному методу для каждого репозитория.
 * Поскольку используется тестовая база данных, рекомендуется начать тест конерктно взятого репозитория
 * с удаления всех записей. Это поможет ликвидировать последствия упавших ранее тестов, когда запись была добавлена,
 * но не удалена.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoriesIT {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private VendorsRepository vendorsRepository;
    @Autowired
    private EquipmentsRepository equipmentsRepository;

    @Test
    public void testCategories() {
        final String categoryName = "тестовая категория";
        final String categoryDescription = "Описание тестовой категории";

        // Создаем новую категорию
        Category testRecord = new Category();
        testRecord.setName(categoryName);
        testRecord.setDescription(categoryDescription);

        // Удаляем все записи категорий перед тестом.
        categoriesRepository.deleteAll();
        // Сохраняем одну только сто созданную
        categoriesRepository.save(testRecord);
        // Получаем все категории
        Iterable<Category> allRecords = categoriesRepository.findAll();
        assertNotNull(allRecords);
        //Бежим по списку
        for (Category record : allRecords) {
            // Проверяем поля
            assertEquals(categoryName, record.getName());
            assertEquals(categoryDescription, record.getDescription());
            // Удаляем категорию.
            categoriesRepository.delete(record);
        }
    }


    @Test
    public void testVendors() {
        final String vendorName = "тестовый производитель";
        final String vendorSite = "http://test.vendor.com";

        // Создаем новую категорию
        Vendor testRecord = new Vendor();
        testRecord.setName(vendorName);
        testRecord.setSite(vendorSite);

        // Удаляем все записи категорий перед тестом.
        vendorsRepository.deleteAll();
        // Сохраняем одну только сто созданную
        vendorsRepository.save(testRecord);
        // Получаем все категории
        Iterable<Vendor> allRecords = vendorsRepository.findAll();
        assertNotNull(allRecords);
        //Бежим по списку
        for (Vendor record : allRecords) {
            // Проверяем поля
            assertEquals(vendorName, record.getName());
            assertEquals(vendorSite, record.getSite());
            // Удаляем категорию.
            vendorsRepository.delete(record);
        }
    }

    private boolean categoryEquals(Category one, Category two) {
        if (one.getId() != two.getId()) {
            return false;
        }
        if (!StringUtils.equals(one.getName(), two.getName())) {
            return false;
        }
        if (!StringUtils.equals(one.getDescription(), two.getDescription())) {
            return false;
        }
        return true;
    }

    private boolean vendorEquals(Vendor one, Vendor two) {
        if (one.getId() != two.getId()) {
            return false;
        }
        if (!StringUtils.equals(one.getName(), two.getName())) {
            return false;
        }
        if (!StringUtils.equals(one.getSite(), two.getSite())) {
            return false;
        }
        return true;
    }

    @Test
    public void testEquipments() {
        // Создаем экземпляр класса тип оборудования
        Category cat = new Category();
        cat.setName("test category");
        categoriesRepository.save(cat);

        // Создаем экземпляр класса производитель
        Vendor vnd = new Vendor();
        vnd.setName("test vendor");
        vendorsRepository.save(vnd);

        final String equipmentModel = "testModel";
        final String equipmentDescription = "testDescription";
        final String equipmentSerial = "testSerial";
        final String equipmentInventory = "testInventory";
        final Boolean equipmentDamaged = true;

        // Создаем новую запись
        Equipment testRecord = new Equipment();
        testRecord.setCategory(cat);
        testRecord.setVendor(vnd);
        testRecord.setModel(equipmentModel);
        testRecord.setDescription(equipmentDescription);
        testRecord.setSerial(equipmentSerial);
        testRecord.setInventory(equipmentInventory);
        testRecord.setDamaged(equipmentDamaged);

        // Удаляем все записи категорий перед тестом.
        equipmentsRepository.deleteAll();
        // Сохраняем одну только сто созданную
        equipmentsRepository.save(testRecord);
        // Получаем все категории
        Iterable<Equipment> allRecords = equipmentsRepository.findAll();
        assertNotNull(allRecords);
        //Бежим по списку
        for (Equipment record : allRecords) {
            // Проверяем поля
            assertTrue(categoryEquals(cat, record.getCategory()));
            assertTrue(vendorEquals(vnd, record.getVendor()));
            assertEquals(equipmentModel, record.getModel());
            assertEquals(equipmentDescription, record.getDescription());
            assertEquals(equipmentSerial, record.getSerial());
            assertEquals(equipmentInventory, record.getInventory());
            assertEquals(equipmentDamaged, record.getDamaged());
            // Удаляем категорию.
            equipmentsRepository.delete(record);
        }
    }

}