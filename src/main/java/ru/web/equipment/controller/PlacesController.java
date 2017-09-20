package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Category;
import ru.web.equipment.entity.Place;
import ru.web.equipment.repository.CategoriesRepository;
import ru.web.equipment.repository.PlacesRepository;

/**
 * Контроллер для отделов.
 */
@Controller()
@RequestMapping("/places")
public class PlacesController {
    private static final Logger log = LoggerFactory.getLogger(PlacesController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/places/list";

    @Autowired
    private PlacesRepository placesRepository;

    @GetMapping("list")
    public String getPlacesList(Model model) {
        Iterable<Place> places = placesRepository.findAll();
        model.addAttribute("places", places);
        return "placesList";
    }


    @GetMapping("new")
    public String newPlace(Model model) {
        model.addAttribute("place", new Place());
        return "editPlace";
    }


    @PostMapping("save")
    public String saveNewPlace(@ModelAttribute Place place) {

        if (place != null) {
            placesRepository.save(place);
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("edit/{placeId}")
    public String editPlace(Model model, @PathVariable long placeId) {

        if (placeId > 0) {
            Place place = placesRepository.findOne(placeId);
            if (place != null) {
                // Такой объект есть
                model.addAttribute("place", place);
                return "editPlace";
            }
        }
        return REDIRECT_TO_LIST;
    }


    @GetMapping("delete/{placeId}")
    public String deletePlace(@PathVariable long placeId) {

        if (placeId > 0) {
            Place place = placesRepository.findOne(placeId);
            if (place != null) {
                // Такой объект есть
                placesRepository.delete(place);
            }
        }
        return REDIRECT_TO_LIST;
    }
}
