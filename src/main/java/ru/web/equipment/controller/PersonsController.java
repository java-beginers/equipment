package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Person;
import ru.web.equipment.repository.CategoriesRepository;
import ru.web.equipment.repository.PersonsRepository;

/**
 * Контроллер для категорий оборудования.
 */
@Controller()
@RequestMapping("/persons")
public class PersonsController {
    private static final Logger log = LoggerFactory.getLogger(PersonsController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/persons/list";

    @Autowired
    private PersonsRepository personsRepository;

    @GetMapping("list")
    public String getPersonsList(Model model) {
        Iterable<Person> persons = personsRepository.findAll();
        model.addAttribute("persons", persons);
        return "personsList";
    }


    @GetMapping("new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "editPerson";
    }


    @PostMapping("save")
    public String saveNewPerson(@ModelAttribute Person person) {

        if (person != null) {
            personsRepository.save(person);
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("edit/{personId}")
    public String editPerson(Model model, @PathVariable long personId) {

        if (personId > 0) {
            Person person = personsRepository.findOne(personId);
            if (person != null) {
                // Такой объект есть
                model.addAttribute("person", person);
                return "editPerson";
            }
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("delete/{personId}")
    public String deletePerson(@PathVariable long personId) {

        if (personId > 0) {
            Person person = personsRepository.findOne(personId);
            if (person != null) {
                // Такой объект есть
                personsRepository.delete(person);
            }
        }
        return REDIRECT_TO_LIST;
    }
}
