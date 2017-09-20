package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Equipment;

/**
 * Репозиторий для оборудования
 */
public interface EquipmentsRepository extends CrudRepository<Equipment, Long> {

}
