package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Equipment;
import ru.web.equipment.entity.Person;
import ru.web.equipment.entity.Vendor;
import ru.web.equipment.repository.CategoriesRepository;
import ru.web.equipment.repository.EquipmentsRepository;
import ru.web.equipment.repository.PersonsRepository;
import ru.web.equipment.repository.VendorsRepository;

/**
 * Контроллер для категорий оборудования.
 */
@Controller()
@RequestMapping("/equipment")
public class EquipmentController {
    private static final Logger log = LoggerFactory.getLogger(EquipmentController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/equipment/list";
    private EquipmentsRepository equipmentsRepository;
    private CategoriesRepository categoriesRepository;
    private VendorsRepository vendorsRepository;
    private PersonsRepository personsRepository;

    @GetMapping("list")
    public String getEquipmentsList(Model model) {
        Iterable<Equipment> equipment = equipmentsRepository.findAll();
        model.addAttribute("equipment", equipment);
        return "equipmentsList";
    }


    @GetMapping("new")
    public String newEquipment(Model model) {
        Iterable<Category> categories = categoriesRepository.findAll();
        Iterable<Vendor> vendors = vendorsRepository.findAll();
        Iterable<Person> persons = personsRepository.findAll();
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("allCategories", categories);
        model.addAttribute("allVendors", vendors);
        model.addAttribute("allPersons", persons);
        return "editEquipment";
    }


    @PostMapping("save")
    public String saveNewEquipment(@ModelAttribute Equipment equipment) {

        if (equipment != null) {
            try {
                equipment.setCategory(fetchCategory(equipment.getCategoryCode()));
                equipment.setVendor(fetchVendor(equipment.getVendorCode()));
                equipment.setPerson(fetchPerson(equipment.getPersonCode()));
                equipmentsRepository.save(equipment);
            } catch (Exception e) {
                log.error("Ошибка при сохранении оборудования.", e);
            }
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("edit/{equipmentId}")
    public String editEquipment(Model model, @PathVariable long equipmentId) {

        if (equipmentId > 0) {
            try {
                Equipment equipment = equipmentsRepository.findOne(equipmentId);
                if (equipment != null) {
                    Iterable<Category> categories = categoriesRepository.findAll();
                    Iterable<Vendor> vendors = vendorsRepository.findAll();
                    Iterable<Person> persons = personsRepository.findAll();
                    model.addAttribute("allCategories", categories);
                    model.addAttribute("allVendors", vendors);
                    model.addAttribute("allPersons", persons);
                    model.addAttribute("equipment", equipment);
                    return "editEquipment";
                }
            } catch (Exception e) {
                log.error("ошика при попытке построить форму редактирования для оборудования с ID {}", equipmentId, e);
            }
        } else {
            log.error("Странное значение идентификатора оборудования {}", equipmentId);
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("delete/{equipmentId}")
    public String deleteEquipment(@PathVariable long equipmentId) {

        if (equipmentId > 0) {
            try {
                Equipment equipment = equipmentsRepository.findOne(equipmentId);
                if (equipment != null) {
                    equipmentsRepository.delete(equipment);
                } else {
                    log.warn("Не удалось найти оборудование с ID {} для удаления.", equipmentId);
                }
            } catch (Exception e) {
                log.error("Ошибка при удалении оборудования с ID {}", equipmentId, e);
            }
        } else {
            log.error("Странный идентификатор оборудования {}", equipmentId);
        }
        return REDIRECT_TO_LIST;
    }


    private Category fetchCategory(long categoryCode) {
        return categoryCode == 0 ? null : categoriesRepository.findOne(categoryCode);
    }


    private Vendor fetchVendor(long vendorCode) {
        return vendorCode == 0 ? null : vendorsRepository.findOne(vendorCode);
    }

    private Person fetchPerson(long personCode) {
        return personCode == 0 ? null : personsRepository.findOne(personCode);
    }

    @Autowired
    public void setEquipmentsRepository(EquipmentsRepository equipmentsRepository) {
        this.equipmentsRepository = equipmentsRepository;
    }

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Autowired
    public void setVendorsRepository(VendorsRepository vendorsRepository) {
        this.vendorsRepository = vendorsRepository;
    }

    @Autowired
    public void setPersonsRepository(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }
}
