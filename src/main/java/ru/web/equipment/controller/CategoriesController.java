package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.repository.CategoriesRepository;

/**
 * Контроллер для категорий оборудования.
 */
@Controller()
@RequestMapping("/categories")
public class CategoriesController {
    private static final Logger log = LoggerFactory.getLogger(CategoriesController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/categories/list";

    @Autowired
    private CategoriesRepository categoriesRepository;

    @GetMapping("list")
    public String getCategoriesList(Model model) {
        Iterable<Category> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        return "categoriesList";
    }


    @GetMapping("new")
    public String newCategory(Model model) {
        model.addAttribute("category", new Category());
        return "editCategory";
    }


    @PostMapping("save")
    public String saveNewCategory(@ModelAttribute Category category) {

        if (category != null) {
            categoriesRepository.save(category);
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("edit/{categoryId}")
    public String editCategory(Model model, @PathVariable long categoryId) {

        if (categoryId > 0) {
            Category category = categoriesRepository.findOne(categoryId);
            if (category != null) {
                // Такой объект есть
                model.addAttribute("category", category);
                return "editCategory";
            }
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("delete/{categoryId}")
    public String deleteCategory(@PathVariable long categoryId) {

        if (categoryId > 0) {
            Category category = categoriesRepository.findOne(categoryId);
            if (category != null) {
                // Такой объект есть
                categoriesRepository.delete(category);
            }
        }
        return REDIRECT_TO_LIST;
    }
}
