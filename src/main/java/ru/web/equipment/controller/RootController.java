package ru.web.equipment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Спринговый REST контроллер для перенаправления на главную страницу
 */
@Controller
public class RootController {

    @RequestMapping("/login.html")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/error-login.html")
    public String getErrorLoginPage(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
