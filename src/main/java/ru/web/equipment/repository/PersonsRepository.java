package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Person;

/**
 * Репозиторий для категорий
 */
public interface PersonsRepository extends CrudRepository<Person, Long> {

}
