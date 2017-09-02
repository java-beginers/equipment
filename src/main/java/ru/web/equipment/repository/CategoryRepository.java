package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Category;

/**
 * Репозиторий для категорий
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    // TODO: 03.12.16 Добавить необходимые методы
}
