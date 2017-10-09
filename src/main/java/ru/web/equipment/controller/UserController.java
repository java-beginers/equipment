package ru.web.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.web.equipment.entity.User;
import ru.web.equipment.entity.UserRole;
import ru.web.equipment.repository.UserRepository;

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
    public String newEquipment(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", UserRole.values());
        return "editUser";
    }



    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
