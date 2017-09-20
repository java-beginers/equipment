package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Category;

/**
 * Репозиторий для типов оборудования
 */
public interface CategoriesRepository extends CrudRepository<Category, Long> {

}
