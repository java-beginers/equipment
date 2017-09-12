package ru.web.equipment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Спринговый REST контроллер для перенаправления на главную страницу
 */
@Controller
public class RootController {
    private static final String LOGIN_ERROR = "loginError";


    @RequestMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @RequestMapping("/loginError")
    public String getErrorLoginPage(Model model) {
        model.addAttribute(LOGIN_ERROR, true);
        return "login";
    }

}
