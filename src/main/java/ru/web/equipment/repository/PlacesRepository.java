package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Place;
import ru.web.equipment.entity.Vendor;

/**
 * Репозиторий для отделов
 */
public interface PlacesRepository extends CrudRepository<Place, Long> {

}
