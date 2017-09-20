package ru.web.equipment.repository;

import org.springframework.data.repository.CrudRepository;
import ru.web.equipment.entity.Vendor;

/**
 * Репозиторий для производителей
 */
public interface VendorsRepository extends CrudRepository<Vendor, Long> {

}
