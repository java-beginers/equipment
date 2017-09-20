package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Equipment;
import ru.web.equipment.entity.Vendor;
import ru.web.equipment.repository.CategoriesRepository;
import ru.web.equipment.repository.EquipmentsRepository;
import ru.web.equipment.repository.VendorsRepository;

/**
 * Контроллер для категорий оборудования.
 */
@Controller()
@RequestMapping("/equipment")
public class EquipmentController {
    private static final Logger log = LoggerFactory.getLogger(EquipmentController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/equipment/list";

    @Autowired
    private EquipmentsRepository equipmentsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private VendorsRepository vendorsRepository;

    @GetMapping("list")
    public String getEquipmentsList(Model model) {
        Iterable<Equipment> equipments = equipmentsRepository.findAll();
        model.addAttribute("equipments", equipments);
        return "equipmentsList";
    }


    @GetMapping("new")
    public String newEquipment(Model model) {
        Iterable<Category> categories = categoriesRepository.findAll();
        Iterable<Vendor> vendors = vendorsRepository.findAll();
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("allCategories", categories);
        model.addAttribute("allVendors", vendors);
        return "editEquipment";
    }


    @PostMapping("save")
    public String saveNewEquipment(@ModelAttribute Equipment equipment) {

        if (equipment != null) {
            equipment.setCategory(fetchCategory(equipment.getCategoryCode()));
            equipment.setVendor(fetchVendor(equipment.getVendorCode()));

            // TODO: 20.09.17 Проверить данные на корректность.

            equipmentsRepository.save(equipment);
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("edit/{equipmentId}")
    public String editEquipment(Model model, @PathVariable long equipmentId) {

        if (equipmentId > 0) {
            Equipment equipment = equipmentsRepository.findOne(equipmentId);
            if (equipment != null) {
                // Такой объект есть
                model.addAttribute("equipment", equipment);
                return "editEquipment";
            }
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("delete/{equipmentId}")
    public String deleteEquipment(@PathVariable long equipmentId) {

        if (equipmentId > 0) {
            Equipment equipment = equipmentsRepository.findOne(equipmentId);
            if (equipment != null) {
                // Такой объект есть
                equipmentsRepository.delete(equipment);
            }
        }
        return REDIRECT_TO_LIST;
    }


    private Category fetchCategory(long categoryCode) {
        return categoryCode == 0 ? null : categoriesRepository.findOne(categoryCode);
    }


    private Vendor fetchVendor(long vendorCode) {
        return vendorCode == 0 ? null : vendorsRepository.findOne(vendorCode);
    }
}
