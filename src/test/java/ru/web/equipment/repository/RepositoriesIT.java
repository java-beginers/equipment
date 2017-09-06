package ru.web.equipment.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Vendor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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

}