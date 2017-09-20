package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Equipment;
import ru.web.equipment.repository.CategoriesRepository;
import ru.web.equipment.repository.EquipmentsRepository;

/**
 * Контроллер для категорий оборудования.
 */
@Controller()
@RequestMapping("/equipments")
public class EquipmentsController {
    private static final Logger log = LoggerFactory.getLogger(EquipmentsController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/equipments/list";

    @Autowired
    private EquipmentsRepository equipmentsRepository;

    @GetMapping("list")
    public String getEquipmentsList(Model model) {
        Iterable<Equipment> equipments = equipmentsRepository.findAll();
        model.addAttribute("equipments", equipments);
        return "equipmentsList";
    }


    @GetMapping("new")
    public String newEquipment(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "editEquipment";
    }


    @PostMapping("save")
    public String saveNewEquipment(@ModelAttribute Equipment equipment) {

        if (equipment != null) {
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
}
