package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.web.equipment.entity.Person;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;
import ru.web.equipment.repository.UserRepository;

import java.util.Date;

/**
 * Контроллер для администрирования зарегистрированных пользователей.
 */
@Controller()
@RequestMapping("/admin/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String REDIRECT_TO_LIST = "redirect:/admin/users/list";
    private UserRepository userRepository;


    @GetMapping("list")
    public String getUsersList(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "usersList";
    }


    @GetMapping("new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", UserRole.values());
        return "editUser";
    }

    @PostMapping("save")
    public String saveNewUser(@ModelAttribute User user) {
        user.setPwdchangedate(new Date());

        if (user != null) {
            userRepository.save(user);
        }
        return REDIRECT_TO_LIST;
    }

    @GetMapping("edit/{userId}")
    public String editUser(Model model, @PathVariable long userId) {

        if (userId > 0) {
            User user = userRepository.findOne(userId);
            if (user != null) {
                // Такой пользователь есть
                model.addAttribute("user", user);
                model.addAttribute("allRoles", UserRole.values());
                return "editUser";
            }
        }
        return REDIRECT_TO_LIST;
    }

    @GetMapping("delete/{userId}")
    public String deleteUser(@PathVariable long userId) {

        if (userId > 0) {
            User user = userRepository.findOne(userId);
            if (user != null) {
                // Такой объект есть
                userRepository.delete(user);
            }
        }
        return REDIRECT_TO_LIST;
    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
