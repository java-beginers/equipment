package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Vendor;
import ru.web.equipment.repository.CategoriesRepository;
import ru.web.equipment.repository.VendorsRepository;

/**
 * Контроллер для производителей оборудования.
 */
@Controller()
@RequestMapping("/vendors")
public class VendorsController {
    private static final Logger log = LoggerFactory.getLogger(VendorsController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/vendors/list";

    @Autowired
    private VendorsRepository vendorsRepository;

    @GetMapping("list")
    public String getVendorsList(Model model) {
        Iterable<Vendor> vendors = vendorsRepository.findAll();
        model.addAttribute("vendors", vendors);
        return "vendorsList";
    }


    @GetMapping("new")
    public String newVendor(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "editVendor";
    }


    @PostMapping("save")
    public String saveNewVendor(@ModelAttribute Vendor vendor) {

        if (vendor != null) {
            vendorsRepository.save(vendor);
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("edit/{vendorId}")
    public String editVendor(Model model, @PathVariable long vendorId) {

        if (vendorId > 0) {
            Vendor vendor = vendorsRepository.findOne(vendorId);
            if (vendor != null) {
                // Такой объект есть
                model.addAttribute("vendor", vendor);
                return "editVendor";
            }
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("delete/{vendorId}")
    public String deleteVendor(@PathVariable long vendorId) {

        if (vendorId > 0) {
            Vendor vendor = vendorsRepository.findOne(vendorId);
            if (vendor != null) {
                // Такой объект есть
                vendorsRepository.delete(vendor);
            }
        }
        return REDIRECT_TO_LIST;
    }
}